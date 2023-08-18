package com.senai.laziot.iot;

import com.senai.laziot.action.ActionService;
import com.senai.laziot.customObjects.Dto.ActionDeviceDTO;
import com.senai.laziot.event.EventoService;
import com.senai.laziot.mqtt.MqttService;
import com.senai.laziot.redis.JedisService;
import com.senai.laziot.user.UsuarioService;
import com.senai.laziot.customObjects.utils.TimeConverter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IotTriggerService {
    private static Logger logger = LogManager.getLogger(IotTriggerService.class);

    @Autowired
    private ActionService actionService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MqttService mqttService;

    @Autowired
    private JedisService jedisService;

    @Autowired
    private TimeConverter timeConverter;

    public void processIotTrigger(JSONObject jsonObjectFromCallback) throws MqttException {
        String uniqueDeviceCode = jsonObjectFromCallback.get("dispositivo").toString();

        if(!uniqueDeviceCode.isEmpty()){
            List<ActionDeviceDTO> actionDTOList = actionService.findIdByUniqueDeviceCode(uniqueDeviceCode);
            if(!actionDTOList.isEmpty()){
                String hashUniqueCode = usuarioService.getHashUniqueCodeByDeviceCode(uniqueDeviceCode);

                for(ActionDeviceDTO actionDeviceDTO : actionDTOList) {
                    int milisDoubleAction = 0;

                    if(!actionDeviceDTO.getDelay().isEmpty()){
                        milisDoubleAction = timeConverter.convertStrTimeToInt(actionDeviceDTO.getDelay());
                    }

                    saveAndTriggerEvent(actionDeviceDTO, hashUniqueCode);

                    if(actionDeviceDTO.isDoubleAction()){
                        triggerDoubleAction(actionDeviceDTO, hashUniqueCode, milisDoubleAction);
                    }
                }
            }
        }
    }

    private void saveAndTriggerEvent(ActionDeviceDTO actionDeviceDTO, String hashUniqueCode) throws MqttException {
        Long idUser     = usuarioService.getIdByDeviceCode(hashUniqueCode);
        Long idNewEvent = eventoService.saveNewEvent(idUser, actionDeviceDTO.getId());

        String payloadJson = new JSONObject()
                .put("eventId", idNewEvent)
                .put("triggerPin", actionDeviceDTO.getTriggerIOPin())
                .toString();

        System.out.println(payloadJson);

        mqttService.sendMessage("iot/" + hashUniqueCode + "/" + actionDeviceDTO.getUniqueDeviceCode(), payloadJson);
        jedisService.saveDeviceEventPendency(idNewEvent, actionDeviceDTO.getUniqueDeviceCode());
    }


    private void triggerDoubleAction(ActionDeviceDTO actionDeviceDTO, String hashUniqueCode, int milisDoubleAction){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            saveAndTriggerEvent(actionDeviceDTO, hashUniqueCode);
                            System.out.println("Passo 2 :"+milisDoubleAction+"<|Evento:>"+actionDeviceDTO.getId());
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    }
                },
                milisDoubleAction
        );

        System.out.println("Passo 3 :"+milisDoubleAction+"<|Evento:>"+actionDeviceDTO.getId());
    }
}

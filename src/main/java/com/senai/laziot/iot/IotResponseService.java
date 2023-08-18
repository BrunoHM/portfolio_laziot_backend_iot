package com.senai.laziot.iot;

import com.senai.laziot.event.EventoService;
import com.senai.laziot.redis.JedisService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IotResponseService {

    @Autowired
    private JedisService jedisService;

    @Autowired
    private EventoService eventoService;

    public void processIotResponse(JSONObject jsonObjectFromCallback) {

        String hashUniqueCode   = jsonObjectFromCallback.get("client").toString();
        String idEvent          = jsonObjectFromCallback.get("eventId").toString();
        String uniqueDeviceCode = jsonObjectFromCallback.get("device").toString();

        if(!hashUniqueCode.isEmpty() && !idEvent.isEmpty() && !uniqueDeviceCode.isEmpty()){
            if(eventoService.validateResponseIot(hashUniqueCode, idEvent, uniqueDeviceCode)) {
                if (jedisService.updateDeviceEventPendency(idEvent, uniqueDeviceCode)) {
                    System.out.println("Evento executado com sucesso, idEvento:" + idEvent);
                }
            }
        }
    }
}

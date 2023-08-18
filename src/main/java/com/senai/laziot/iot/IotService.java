package com.senai.laziot.iot;

import com.senai.laziot.enums.MqttReadQueuesEnum;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IotService {

    @Autowired
    private IotTriggerService iotTriggerService;

    @Autowired
    private IotResponseService iotResponseService;

    @Autowired
    private IotDeviceInitializationService DeviceInitializationService;

    public void processMessageFromCallback(JSONObject jsonObjectFromCallback) throws MqttException {
        System.out.println(jsonObjectFromCallback);
        String topicFromIotDevice = jsonObjectFromCallback.get("topico").toString();

        if(topicFromIotDevice.equals(MqttReadQueuesEnum.INITILIZATION.getDescricao())) {
            DeviceInitializationService.processIotInitialization(jsonObjectFromCallback);
        }

        if(topicFromIotDevice.equals(MqttReadQueuesEnum.TRIGGER.getDescricao())) {
            iotTriggerService.processIotTrigger(jsonObjectFromCallback);
        }

        if(topicFromIotDevice.equals(MqttReadQueuesEnum.DEVICEEVENTRESPONSE.getDescricao())) {
            iotResponseService.processIotResponse(jsonObjectFromCallback);
        }

    }

}

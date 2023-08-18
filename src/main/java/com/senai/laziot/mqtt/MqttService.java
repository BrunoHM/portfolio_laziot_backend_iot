package com.senai.laziot.mqtt;

import com.senai.laziot.enums.MqttReadQueuesEnum;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    private IMqttClient publisherClient;

    private MqttConnection mqttConnection;

    @Autowired
    private MqttCallbackImpl mqttCallback;

    public void initializeMqtt() throws MqttException {
        mqttConnection  = new MqttConnection();
        publisherClient = mqttConnection.getMqttClient();
    }

    public boolean connectionMqttUp(){
        if ( !publisherClient.isConnected()) {
            return false;
        }
        return true;
    }

    public void sendMessage(String topic, String message) throws MqttException{
        if (connectionMqttUp()) {
            byte[] payload = message.getBytes();

            MqttMessage msg = new MqttMessage(payload);
            msg.setQos(2);
            msg.setRetained(true);

            publisherClient.publish(topic, msg);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void receiveMqttInitialization() throws MqttException {
        initializeMqtt();

        if(connectionMqttUp()) {
            publisherClient.setCallback(mqttCallback);
            publisherClient.subscribe(MqttReadQueuesEnum.INITILIZATION.getDescricao());
            publisherClient.subscribe(MqttReadQueuesEnum.TRIGGER.getDescricao());
            publisherClient.subscribe(MqttReadQueuesEnum.DEVICEEVENTRESPONSE.getDescricao());
        }
    }


}

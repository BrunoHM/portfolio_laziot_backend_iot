package com.senai.laziot.mqtt;

import com.senai.laziot.iot.IotService;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttCallbackImpl implements MqttCallback {

    @Autowired
    private IotService iotService;

    public void messageArrived(String topic, MqttMessage message){
        try {
            JSONObject jsonObject = new JSONObject(new String(message.getPayload()));
            jsonObject.put("topico", topic);
            iotService.processMessageFromCallback(jsonObject);
        }catch(Exception e){
            System.out.println(e);
        }

        /*
        System.out.println("\nReceived a Message!" +
                "\n\tTime:    " + time +
                "\n\tTopic:   " + topic +
                "\n\tMessage: " + new String(message.getPayload()) +
                "\n\tQoS:     " + message.getQos() + "\n");
        */
    }

    public void connectionLost(Throwable cause) {
        System.out.println("Connection to broker lost!/n" + cause.toString());
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Passo 1> :"+token.toString()+"|"+this.getClass().toString());
    }

}

package com.senai.laziot.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MqttConnection {

    private IMqttClient mqttClient;
    private MqttConnectOptions options;

    public MqttConnection() throws MqttException {
        this.mqttClient = new MqttClient("tcp://localhost:1883", UUID.randomUUID().toString(), new MemoryPersistence());
        this.options = new MqttConnectOptions();
        this.options.setAutomaticReconnect(true);
        this.options.setCleanSession(true);
        this.options.setConnectionTimeout(10);
        this.options.getDebug();
        this.mqttClient.connect(this.options);
        System.out.println("");
    }

    public IMqttClient getMqttClient(){
        return mqttClient;
    }
}

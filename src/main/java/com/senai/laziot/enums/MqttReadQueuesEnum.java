package com.senai.laziot.enums;

public enum MqttReadQueuesEnum {

    INITILIZATION("initialization"),            // Fila que captura a inicialização dos dispositivos iot;
    TRIGGER("trigger"),                         // Fila que captura todos acionamentos dos dispositivos iot emissores;
    DEVICEEVENTRESPONSE("deviceEventResponse"); // Fila que captura todos retornos dos dispositivos após execução de algum evento solicitado;

    MqttReadQueuesEnum(String descricao){
        this.descricao = descricao;
    }
    private String descricao;
    public String getDescricao(){
        return descricao;
    }

}

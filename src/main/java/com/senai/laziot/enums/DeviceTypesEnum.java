package com.senai.laziot.enums;

public enum DeviceTypesEnum {

    RECEPTOR,
    EMISSOR;

    public static DeviceTypesEnum stringToTypeEnum(String stringDeviceType){
        String deviceType = stringDeviceType.toUpperCase();
        if(RECEPTOR.toString().equals(deviceType)){
            return RECEPTOR;
        }
        if(EMISSOR.toString().equals(deviceType)){
            return EMISSOR;
        }
        return null;
    }
}

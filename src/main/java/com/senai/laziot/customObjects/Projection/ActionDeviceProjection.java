package com.senai.laziot.customObjects.Projection;

public interface ActionDeviceProjection {

    Long getId();
    short getTriggerIOPin();
    boolean getDoubleAction();
    String getDelay();
    String getUniqueDeviceCode();

}

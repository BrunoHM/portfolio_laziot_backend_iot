package com.senai.laziot.customObjects.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ActionDeviceDTO {

    private Long id;
    private short triggerIOPin;
    private boolean doubleAction;
    private String delay;
    private String uniqueDeviceCode;

}

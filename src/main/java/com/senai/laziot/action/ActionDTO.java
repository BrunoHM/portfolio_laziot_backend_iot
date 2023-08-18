package com.senai.laziot.action;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ActionDTO {

    private Long id;
    private String description;
    private short triggerIOPin;
    private boolean doubleAction;
    private String delay;
    private Long fkDeviceId;

    public ActionDTO(Long id, short triggerIOPin, boolean doubleAction, String delay, Long fkDeviceId){
        this.id = id;
        this.triggerIOPin = triggerIOPin;
        this.doubleAction = doubleAction;
        this.delay = delay;
        this.fkDeviceId = fkDeviceId;
    }
}

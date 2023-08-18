package com.senai.laziot.action;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "action")
public class ActionEntity {

    public ActionEntity(String description, short triggerIOPin, Long fkDeviceId){
        this.id = 0L;
        this.active = true;
        this.description = description;
        this.triggerIOPin = triggerIOPin;
        this.fkDeviceId = fkDeviceId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "triggerIOPin")
    private short triggerIOPin;

    @Column(name = "doubleAction")
    private boolean doubleAction;

    @Column(name = "delay")
    private String delay;
    
    @Column(name = "active")
    private boolean active;

    @Column(name = "fkDeviceId")
    private Long fkDeviceId;

}

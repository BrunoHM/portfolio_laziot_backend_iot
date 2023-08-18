package com.senai.laziot.device;

import com.senai.laziot.enums.DeviceTypesEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "device")
public class DeviceEntity {

    public DeviceEntity(String uniqueDeviceCode, DeviceTypesEnum typeDevice){
        this.id = 0L;
        this.description = uniqueDeviceCode;
        this.place = "";
        this.uniqueDeviceCode = uniqueDeviceCode;
        this.qtdPinsIO = 1;
        this.type = typeDevice.toString();
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "place")
    private String place;

    @Column(name = "uniqueDeviceCode")
    private String uniqueDeviceCode;

    @Column(name = "qtdPinsIO")
    private Short qtdPinsIO;

    @Column(name = "type")
    private String type;

    @Column(name = "active")
    private boolean active;
}
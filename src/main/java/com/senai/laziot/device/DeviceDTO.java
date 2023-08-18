package com.senai.laziot.device;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class DeviceDTO {

    public DeviceDTO(String uniqueDeviceCode, String typeDevice){
        this.id = 0L;
        this.description = "";
        this.place = "";
        this.uniqueDeviceCode = uniqueDeviceCode;
        this.qtdPinsIO = 0;
        this.type = typeDevice;
        this.active = true;
    }

    private Long id;
    private String description;
    private String place;
    private String uniqueDeviceCode;
    private Short qtdPinsIO;
    private String type;
    private boolean active;

}

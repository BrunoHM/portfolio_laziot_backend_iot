package com.senai.laziot.userDevices;

import lombok.*;

import java.io.Serializable;

@Builder @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class UserDevicePK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUser;
    private Long idDevice;
}

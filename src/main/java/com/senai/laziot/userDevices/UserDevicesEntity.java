package com.senai.laziot.userDevices;

import com.senai.laziot.device.DeviceEntity;
import com.senai.laziot.user.UsuarioEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "userDevices")
@IdClass(UserDevicePK.class)
public class UserDevicesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UsuarioEntity idUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private DeviceEntity idDevice;

}

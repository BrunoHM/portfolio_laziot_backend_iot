package com.senai.laziot.userDevices;

import org.springframework.stereotype.Component;

@Component
public interface UserDevicesService {

    boolean save(UserDevicesEntity userDevicesEntity);

}

package com.senai.laziot.device;

import org.springframework.stereotype.Component;

@Component
public class DeviceTranslator {

    public DeviceDTO deviceEntityToDeviceDTO(DeviceEntity deviceEntity){
        DeviceDTO deviceDTO = new DeviceDTO(deviceEntity.getId(),
                deviceEntity.getDescription(), deviceEntity.getPlace(),
                deviceEntity.getUniqueDeviceCode(), deviceEntity.getQtdPinsIO(),
                deviceEntity.getType() ,deviceEntity.isActive());
        return deviceDTO;
    }

}

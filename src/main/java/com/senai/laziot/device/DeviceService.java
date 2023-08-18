package com.senai.laziot.device;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceService {

    DeviceEntity saveNewDevice(DeviceEntity newDeviceEntity);
    boolean findDeviceByUniqueDeviceCode(String uniqueDeviceCode);
    List<DeviceDTO> findAllDevices();
    List<Object[]> findAllChildRelatedDevices(String UniqueDeviceCodeTrigger);

}

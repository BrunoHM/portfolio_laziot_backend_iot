package com.senai.laziot.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceTranslator deviceTranslator;

    @Override
    @Transactional
    public DeviceEntity saveNewDevice(DeviceEntity newDeviceEntity) {
        try {
            DeviceEntity deviceEntity = deviceRepository.save(newDeviceEntity);
            return deviceEntity;
        } catch(Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    @Transactional
    public boolean findDeviceByUniqueDeviceCode(String uniqueDeviceCode) {
        if(deviceRepository.findDeviceByUniqueDeviceCode(uniqueDeviceCode) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<DeviceDTO> findAllDevices() {
        return deviceRepository.findAllDevicesWhereActiveAndHasDeviceUniqueCode()
                .stream()
                .map(deviceTranslator::deviceEntityToDeviceDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Object[]> findAllChildRelatedDevices(String UniqueDeviceCodeTrigger) {
        try {
            return deviceRepository.findAllChildRelatedDevices(UniqueDeviceCodeTrigger);
        }catch(Exception e){
            return null;
        }
    }


}

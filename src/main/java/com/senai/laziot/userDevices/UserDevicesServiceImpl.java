package com.senai.laziot.userDevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDevicesServiceImpl implements UserDevicesService {

    @Autowired
    private UserDevicesRepository userDevicesRepository;

    @Override
    public boolean save(UserDevicesEntity userDevicesEntity) {
        try {
            userDevicesRepository.save(userDevicesEntity);
            return true;
        }catch (Exception exception) {
            throw exception;
        }
    }
}

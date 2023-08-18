package com.senai.laziot.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping(path = "/devices")
    public List<DeviceDTO> findAllDevices(){
        return deviceService.findAllDevices();
    }

}

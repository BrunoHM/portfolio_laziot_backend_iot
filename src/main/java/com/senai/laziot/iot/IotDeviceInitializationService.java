package com.senai.laziot.iot;

import com.senai.laziot.device.DeviceEntity;
import com.senai.laziot.device.DeviceService;
import com.senai.laziot.enums.DeviceTypesEnum;
import com.senai.laziot.user.UsuarioEntity;
import com.senai.laziot.user.UsuarioService;
import com.senai.laziot.userDevices.UserDevicesEntity;
import com.senai.laziot.userDevices.UserDevicesService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IotDeviceInitializationService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserDevicesService userDevicesService;

    public void processIotInitialization(JSONObject jsonObjectFromCallback) {
        String uniqueDeviceCode = jsonObjectFromCallback.get("dispositivo").toString();
        String typeDevice       = jsonObjectFromCallback.get("tipo").toString();
        String userToken        = jsonObjectFromCallback.get("token").toString();

        if(!uniqueDeviceCode.isEmpty() && !typeDevice.isEmpty() ){
            if(!deviceService.findDeviceByUniqueDeviceCode(uniqueDeviceCode)){
                DeviceEntity newDeviceEntity = new DeviceEntity(uniqueDeviceCode, DeviceTypesEnum.stringToTypeEnum(typeDevice));
                newDeviceEntity = deviceService.saveNewDevice(newDeviceEntity);
                if(newDeviceEntity.getId() != null){
                    UsuarioEntity userEntity = usuarioService.getUserFromToken(userToken);
                    UserDevicesEntity userDevicesEntity = new UserDevicesEntity(userEntity, newDeviceEntity);
                    userDevicesService.save(userDevicesEntity);
                }
            }
        }
    }

}

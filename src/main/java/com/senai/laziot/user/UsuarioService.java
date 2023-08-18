package com.senai.laziot.user;

import org.springframework.stereotype.Component;

@Component
public interface UsuarioService {

    String getHashUniqueCodeByDeviceCode(String uniqueDeviceCode);
    Long getIdByDeviceCode(String hashUniqueCode);
    UsuarioEntity getUserFromToken(String hashUniqueCode);

}


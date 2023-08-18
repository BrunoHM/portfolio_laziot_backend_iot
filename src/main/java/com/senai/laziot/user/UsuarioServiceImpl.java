package com.senai.laziot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String getHashUniqueCodeByDeviceCode(String uniqueDeviceCode){
        String returnStr = "";
        try {
            returnStr = usuarioRepository.getHashUniqueCodeByDeviceCode(uniqueDeviceCode);
        } catch(Exception e) {
            System.out.println(e);
        }
        return returnStr;
    }

    public Long getIdByDeviceCode(String hashUniqueCode){
        Long returnStr = 0L;
        try {
            returnStr = usuarioRepository.getIdByDeviceCode(hashUniqueCode);
        } catch(Exception e) {
            System.out.println(e);
        }
        return returnStr;
    }

    @Override
    public UsuarioEntity getUserFromToken(String hashUniqueCode) {
        try {
            UsuarioEntity usuario = usuarioRepository.getUsuarioEntityByHashUniqueCode(hashUniqueCode);
            return usuario;
        }catch(Exception exception){
            throw exception;
        }
    }

}

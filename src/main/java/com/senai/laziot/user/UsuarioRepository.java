package com.senai.laziot.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Override
    <S extends UsuarioEntity> S save(S entity);

    UsuarioEntity getUsuarioEntityByHashUniqueCode(String hashUniqueCode);

    @Query(value = "select hashUniqueCode " +
            "from user " +
            "where id = " +
                "(select user_id " +
                    "from userDevices " +
                    "where device_id = " +
                "(select id from device where uniqueDeviceCode = :uniqueDeviceCode));", nativeQuery = true)
    String getHashUniqueCodeByDeviceCode(@Param("uniqueDeviceCode") String uniqueDeviceCode);

    @Query(value = "select id " +
            "from user " +
            "where hashUniqueCode = :hashUniqueCode ;", nativeQuery = true)
    Long getIdByDeviceCode(@Param("hashUniqueCode") String hashUniqueCode);

}

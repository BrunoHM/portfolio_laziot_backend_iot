package com.senai.laziot.userDevices;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDevicesRepository extends JpaRepository<UserDevicesEntity, Id> {

    @Override
    <S extends UserDevicesEntity> S save(S entity);
}

package com.senai.laziot.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    @Query(value = "SELECT IFNULL(" +
            "(SELECT if(id > 0, true, false) as exist " +
            "from device " +
            "where uniqueDeviceCode = :uniqueDeviceCode)" +
            ",false);", nativeQuery = true)
    int findDeviceByUniqueDeviceCode(@Param("uniqueDeviceCode") String uniqueDeviceCode);


    @Query(value = "SELECT * " +
            "FROM device " +
            "WHERE active = 1 " +
            "AND uniqueDeviceCode <> \"\" ;", nativeQuery = true)
    List<DeviceEntity> findAllDevicesWhereActiveAndHasDeviceUniqueCode();


    @Query(value = "select a.id, a.uniqueDeviceCode\n" +
            "from device a\n" +
            "inner join equivalentDevice b ON a.id = b.idDispositivoFilho\n" +
            "inner join device c ON c.id = b.idDispositivoMae\n" +
            "where c.uniqueDeviceCode = :UniqueDeviceCodeTrigger and a.type = \"receptor\" and a.active = 1;", nativeQuery = true)
    List<Object[]> findAllChildRelatedDevices(@Param("UniqueDeviceCodeTrigger") String UniqueDeviceCodeTrigger);
}

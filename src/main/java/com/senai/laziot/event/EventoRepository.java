package com.senai.laziot.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {

    @Override
    <S extends EventoEntity> S save(S s);

    @Transactional
    @Modifying
    @Query(value = "update event " +
            "set executed = 1 " +
            "where id = :idEvent ;", nativeQuery = true)
    void updateEventToExecuted(@Param("idEvent") String idEvent);

    @Query(value = "select if(a.id > 0, true, false) result" +
            "from event a" +
            "LEFT JOIN action b ON b.id = a.fkIdAction" +
            "LEFT JOIN device c ON c.id = b.fkDeviceId" +
            "LEFT JOIN userDevices d ON d.device_id = c.id" +
            "LEFT JOIN user e ON e.id = d.user_id" +
            "where c.uniqueDeviceCode = :uniqueDeviceCode" +
            "AND a.id = :idEvent" +
            "AND e.hashUniqueCode = :hashUniqueCode;", nativeQuery = true)
    boolean validateResponseIot(@Param("hashUniqueCode") String hashUniqueCode,
            @Param("idEvent") String idEvent,
            @Param("uniqueDeviceCode") String uniqueDeviceCode);
}

package com.senai.laziot.action;

import com.senai.laziot.customObjects.Projection.ActionDeviceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActionRepository extends JpaRepository<ActionEntity, Long> {


    @Override
    <S extends ActionEntity> S save(S s);

    @Query(value = "SELECT a.id AS id, a.triggerIOPin as triggerIOPin, a.doubleAction as doubleAction, a.delay as delay, b.uniqueDeviceCode as uniqueDeviceCode " +
            "FROM action a " +
            "LEFT JOIN device b ON a.fkDeviceId = b.id "+
            "WHERE a.active = 1 " +
            "AND fkDeviceId in " +
            "(SELECT idDispositivoFilho FROM equivalentDevice WHERE idDispositivoMae = "+
            "(SELECT id FROM device WHERE uniqueDeviceCode = :uniqueDeviceCode));", nativeQuery = true)
    List<ActionDeviceProjection> findIdByUniqueDeviceCode(@Param("uniqueDeviceCode") String uniqueDeviceCode);



    @Override
    Optional<ActionEntity> findById(Long Long);
}

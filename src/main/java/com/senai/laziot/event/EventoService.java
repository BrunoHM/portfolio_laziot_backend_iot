package com.senai.laziot.event;

import org.springframework.stereotype.Component;

@Component
public interface EventoService {

    Long saveNewEvent(Long idUser, Long idActionNewEvent);

    boolean updateEventToExecuted(String idEvent);

    boolean validateResponseIot(String hashUniqueCode, String idEvent, String uniqueDeviceCode);
}

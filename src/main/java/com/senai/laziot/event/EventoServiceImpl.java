package com.senai.laziot.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoServiceImpl implements EventoService {

    private static Logger logger = LogManager.getLogger(EventoServiceImpl.class);

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Long saveNewEvent(Long idUser, Long idActionNewEven) {
        try {
            EventoEntity eventoEntity = new EventoEntity(idUser, idActionNewEven);
            return eventoRepository.save(eventoEntity).getId();
        }catch(Exception e) {
            logger.error("saveNewEvent:"+e.getMessage());
            return 0L;
        }
    }

    @Override
    public boolean updateEventToExecuted(String idEvent) {
        try {
            eventoRepository.updateEventToExecuted(idEvent);
            return true;
        }catch(Exception e) {
            logger.error("updateEventToExecuted:"+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean validateResponseIot(String hashUniqueCode, String idEvent, String uniqueDeviceCode){
        try {
            return eventoRepository.validateResponseIot(hashUniqueCode, idEvent, uniqueDeviceCode);
        }catch(Exception e){
            logger.error("validateResponseIot:"+e.getMessage());
            return false;
        }
    }


}

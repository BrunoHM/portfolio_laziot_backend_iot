package com.senai.laziot.action;

import com.senai.laziot.customObjects.Dto.ActionDeviceDTO;
import com.senai.laziot.customObjects.Projection.ActionDeviceProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private ActionTranslator actionTranslator;

    @Transactional
    public void saveNewAction(ActionDTO newAction){
        try{
            ActionEntity actionEntity = new ActionEntity(newAction.getDescription(), newAction.getTriggerIOPin(), newAction.getFkDeviceId());
            actionRepository.save(actionEntity);
        }catch(Exception e){
            e.fillInStackTrace();
        }
    }

    @Override
    public List<ActionDeviceDTO> findIdByUniqueDeviceCode(String uniqueCodeDevice) {
        List<ActionDeviceDTO> actionDeviceDTO = new ArrayList<>();
        try {
            List<ActionDeviceProjection> actionDeviceProjectionList = actionRepository.findIdByUniqueDeviceCode(uniqueCodeDevice);
            actionDeviceProjectionList = actionRepository.findIdByUniqueDeviceCode(uniqueCodeDevice);
            actionDeviceProjectionList.forEach(actionDeviceProjection -> {
                actionDeviceDTO.add(actionTranslator.translateActionDeviceProjectionToActionDeviceDTO(actionDeviceProjection));
            });
        } catch(Exception e) {
            System.out.println(e);
        }
        return actionDeviceDTO;
    }

}

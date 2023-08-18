package com.senai.laziot.action;

import com.senai.laziot.customObjects.Dto.ActionDeviceDTO;
import com.senai.laziot.customObjects.Projection.ActionDeviceProjection;
import org.springframework.stereotype.Component;

@Component
public class ActionTranslator {

    public ActionDeviceDTO translateActionDeviceProjectionToActionDeviceDTO(ActionDeviceProjection actionDeviceProjection){
        return new ActionDeviceDTO(actionDeviceProjection.getId(), actionDeviceProjection.getTriggerIOPin(),
                actionDeviceProjection.getDoubleAction(), actionDeviceProjection.getDelay(), actionDeviceProjection.getUniqueDeviceCode());
    }
}

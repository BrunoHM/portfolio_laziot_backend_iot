package com.senai.laziot.action;

import com.senai.laziot.customObjects.Dto.ActionDeviceDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ActionService {

    void saveNewAction(ActionDTO actionDTO);
    List<ActionDeviceDTO> findIdByUniqueDeviceCode(String uniqueCodeDevice);
}

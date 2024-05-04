package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.enums.UserType;
import com.upl.nibss.hcmlib.model.ConfirmationChecklist;

import java.util.List;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
public interface IConfirmationChecklistService {

    List<ConfirmationChecklist> getAll() throws Exception;

    List<ConfirmationChecklist> getAllActivated() throws Exception;

    List<ConfirmationChecklist> getAllByUserType(UserType userType) throws Exception;

    List<ConfirmationChecklist> getAllByUserTypeAndActivated(UserType userType) throws Exception;

    ConfirmationChecklist get(Long id) throws Exception;

    ConfirmationChecklist getByUserTypeAndId(UserType userType, Long id) throws Exception;

    ConfirmationChecklist save(ConfirmationChecklist checklist) throws Exception;

    Boolean toogle(Long id) throws Exception;

    int countOfSameRequirementName(String requirementName) throws Exception;

    int countOfSameRequirementNameNotId(String requirementName, Long id) throws Exception;

}

package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.UserType;
import com.upl.nibss.hcmlib.model.ConfirmationChecklist;
import com.upl.nibss.hcmlib.repo.ConfirmationChecklistRepo;
import com.upl.nibss.hcmlib.service.interfaces.IConfirmationChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
@Service
public class ConfirmationChecklistService implements IConfirmationChecklistService{

    @Autowired
    private ConfirmationChecklistRepo checklistRepo;

    @Override
    public List<ConfirmationChecklist> getAll() throws Exception{
        return checklistRepo.findAll();
    }

    @Override
    public List<ConfirmationChecklist> getAllActivated() throws Exception {
        return checklistRepo.findAllActivated();
    }

    @Override
    public List<ConfirmationChecklist> getAllByUserType(UserType userType) throws Exception {
        return checklistRepo.findAllByUserType(userType);
    }

    @Override
    public List<ConfirmationChecklist> getAllByUserTypeAndActivated(UserType userType) throws Exception {
        return checklistRepo.findAllByUserTypeAndActivated(userType);
    }

    @Override
    public ConfirmationChecklist get(Long id) throws Exception {
        return checklistRepo.findOne(id);
    }

    @Override
    public ConfirmationChecklist getByUserTypeAndId(UserType userType, Long id) throws Exception {
        return checklistRepo.findByUserTypeAndId(userType, id);
    }

    @Override
    public ConfirmationChecklist save(ConfirmationChecklist checklist) throws Exception{
        return checklistRepo.save(checklist);
    }

    @Override
    public Boolean toogle(Long id) throws Exception {

        ConfirmationChecklist confirmationChecklist = checklistRepo.findOne(id);
        confirmationChecklist.setActivated(!confirmationChecklist.isActivated());
        confirmationChecklist = checklistRepo.save(confirmationChecklist);

        if (confirmationChecklist != null){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public int countOfSameRequirementName(String requirementName) throws Exception{
        return checklistRepo.countOfSameRequirementName(requirementName);
    }

    @Override
    public int countOfSameRequirementNameNotId(String requirementName, Long id) throws Exception {
        return checklistRepo.countOfSameRequirementNameNotId(requirementName,id);
    }
}

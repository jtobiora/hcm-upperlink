package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.model.ConfirmationRecord;
import com.upl.nibss.hcmlib.repo.ConfirmationRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 05/12/2017.
 */
@Service
public class ConfirmationRecordService {

    @Autowired
    private ConfirmationRecordRepo confirmationRecordRepo;


    public List<ConfirmationRecord> getAll() throws Exception{
        return confirmationRecordRepo.findAll();
    }

    public List<ConfirmationRecord> getAllByEmployee(Long employeeId) throws Exception{
        return confirmationRecordRepo.findAllByEmployee(employeeId);
    }

    public ConfirmationRecord get(Long id) throws Exception{
        return confirmationRecordRepo.findOne(id);
    }

    public ConfirmationRecord save(ConfirmationRecord confirmationRecord) throws Exception{
        return confirmationRecordRepo.save(confirmationRecord);
    }

    public List<ConfirmationRecord> save(List<ConfirmationRecord> confirmationRecords) throws Exception{
        return confirmationRecordRepo.save(confirmationRecords);
    }

    public Document getDocument(Long id) throws Exception{
        return confirmationRecordRepo.findDocumentById(id);
    }

    public List<ConfirmationRecord> getByEmployee(List<Long> ids, Long employeeId) throws Exception {
        return confirmationRecordRepo.findAllByIdsAAndEmployee(ids, employeeId);
    }

    public boolean confirmationRecWithSimilarEmplAndChecklistAlreadyExist(Long employeeId, Long checklistId) throws Exception{

        Integer integer = confirmationRecordRepo.countOfConfirmationRecWithSimilarEmplAndChecklist(employeeId,checklistId);
        if (integer != null && integer.intValue() > 0){
            return true;
        }

        return false;
    }

}

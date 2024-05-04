package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.RotationRecord;
import com.upl.nibss.hcmlib.model.RotationRecordHistory;
import com.upl.nibss.hcmlib.repo.RotationRecordHistoryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotationRecordHistoryService{

    private static final Logger logger = LoggerFactory.getLogger(RotationRecordHistoryService.class);

    @Autowired
    private RotationRecordHistoryRepo rotationRecordHistoryRepo;

    public List<RotationRecordHistory> getAll() throws Exception {
        return rotationRecordHistoryRepo.findAll();
    }

    public RotationRecordHistory get(Long id) throws Exception {
        return rotationRecordHistoryRepo.findOne(id);
    }

    public RotationRecordHistory save(RotationRecordHistory rotationRecordHistory) throws Exception {
        return rotationRecordHistoryRepo.save(rotationRecordHistory);
    }

    public List<RotationRecordHistory> save(List<RotationRecordHistory> rotationRecordHistories) throws Exception {
        return rotationRecordHistoryRepo.save(rotationRecordHistories);
    }

    public void delete(Long id){
        rotationRecordHistoryRepo.delete(id);
    }

    public void delete(RotationRecordHistory rotationRecordHistory) throws Exception{
        rotationRecordHistoryRepo.delete(rotationRecordHistory);
    }

    public void delete(List<RotationRecordHistory> rotationRecordHistories) throws Exception{
        rotationRecordHistoryRepo.delete(rotationRecordHistories);
    }

    public RotationRecordHistory findRotationRecordByEmployeeId(Long employeeId) throws Exception{
        return rotationRecordHistoryRepo.findRotationRecordHistoryByEmployeeId(employeeId);
    }

    public List<RotationRecordHistory> findRotationRecordsByEmployeeId(Long employeeId) throws Exception{
        return rotationRecordHistoryRepo.findRotationRecordHistoriesByEmployeeId(employeeId);
    }
}

package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.LeaveRecordHistory;
import com.upl.nibss.hcmlib.repo.LeaveRecordHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 10/12/2017.
 */
@Service
public class LeaveRecordHistoryService {

    @Autowired
    private LeaveRecordHistoryRepo leaveRecordHistoryRepo;

    public List<LeaveRecordHistory> getAll() throws Exception {
        return leaveRecordHistoryRepo.findAll();
    }

    public List<LeaveRecordHistory> getAllByEmployeeId(Long employeeId) throws Exception {
        return leaveRecordHistoryRepo.findAllByEmployee(employeeId);
    }

    public LeaveRecordHistory get(Long employeeId) throws Exception {
        return leaveRecordHistoryRepo.findOne(employeeId);
    }

    public LeaveRecordHistory save(LeaveRecordHistory leaveRecordHistory) throws Exception{
        return leaveRecordHistoryRepo.save(leaveRecordHistory);
    }

    public List<LeaveRecordHistory> save(List<LeaveRecordHistory> leaveRecordHistories) throws Exception{
        return leaveRecordHistoryRepo.save(leaveRecordHistories);
    }

    public List<LeaveRecordHistory> getAllByEmployeeAndId(Long employeeId, Long id) throws  Exception{
        return leaveRecordHistoryRepo.findAllByEmployeeAndId(employeeId,id);
    }

    public List<LeaveRecordHistory> getAllByYearAndType(Long employeeId, String leaveTypeName, Long year) throws Exception{
        return leaveRecordHistoryRepo.getAllByYearAndType(employeeId, leaveTypeName, year);
    }

    public List<LeaveRecordHistory> getAllByYearAndNotType(Long employeeId, String leaveTypeName, Long year) throws Exception{
        return leaveRecordHistoryRepo.getAllByYearAndNotType(employeeId, leaveTypeName, year);
    }
}

package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.LoanRecordHistory;
import com.upl.nibss.hcmlib.repo.LoanRecordHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 24/11/2017.
 */
@Service
public class LoanRecordHistoryService {

    @Autowired
    private LoanRecordHistoryRepo loanRecordHistoryRepo;

    public List<LoanRecordHistory> getAll() throws Exception {
        return loanRecordHistoryRepo.findAll();
    }

    public List<LoanRecordHistory> getAllByEmployeeId(Long employeeId) throws Exception {
        return loanRecordHistoryRepo.findAllByEmployee(employeeId);
    }

    public LoanRecordHistory get(Long employeeId) throws Exception {
        return loanRecordHistoryRepo.findOne(employeeId);
    }

    public LoanRecordHistory save(LoanRecordHistory loanRecordHistory) throws Exception{
        return loanRecordHistoryRepo.save(loanRecordHistory);
    }

    public List<LoanRecordHistory> save(List<LoanRecordHistory> loanRecordHistories) throws Exception{
        return loanRecordHistoryRepo.save(loanRecordHistories);
    }

    public List<LoanRecordHistory> getByEmployeeAndId(Long employeeId, Long id) throws  Exception{
        return loanRecordHistoryRepo.findAllByEmployeeAndId(employeeId,id);
    }

}
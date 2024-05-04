package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.LoanRecord;
import com.upl.nibss.hcmlib.model.LoanType;
import com.upl.nibss.hcmlib.repo.LoanRecordRepo;
import com.upl.nibss.hcmlib.service.interfaces.ILoanRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Service
public class LoanRecordService implements ILoanRecordService {

    @Autowired
    private LoanRecordRepo loanRecordRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<LoanRecord> getAll() throws Exception {
        return loanRecordRepo.findAll();
    }

    @Override
    public List<LoanRecord> getAllAjax() throws Exception {
       return loanRecordRepo.findAll();
    }

    @Override
    public List<LoanRecord> getAllAjaxPerEmployee(Long employeeId) throws Exception {
        return loanRecordRepo.findAllAjaxPerEmployee(employeeId);
    }

    @Override
    public List<LoanRecord> getAllByEmployeeAndLoanType(Long employeeId, Long loanTypeId) throws Exception {
        return loanRecordRepo.findAllByEmployeeAndLoanType(employeeId, loanTypeId);
    }

    @Override
    public LoanRecord getByEmployee(Long employeeId) throws Exception {
        return loanRecordRepo.findByEmployeeId(employeeId);
    }

    @Override
    public LoanRecord get(Long id) throws Exception {
        return loanRecordRepo.findOne(id);
    }

    @Override
    public LoanRecord getAjax(Long id) throws Exception {
        return loanRecordRepo.findAjax(id);
    }

    @Override
    public int countOfPendingLoanRequest(Long employeeId, Long loanTypeId) throws Exception {
        return loanRecordRepo.countOfPendingLoanRequest(employeeId,loanTypeId);
    }

    @Override
    public LoanRecord save(LoanRecord loanRecord) throws Exception {
        return loanRecordRepo.save(loanRecord);
    }

    @Override
    public void delete(List<Long> ids) throws Exception {
        loanRecordRepo.deleteByIds(ids);
    }

    @Override
    public void deleteByObject(List<LoanRecord> loanRecords) throws Exception {
        List<Long> ids = new ArrayList<>();
        loanRecords.forEach(loanRecord -> ids.add(loanRecord.getId()));
        if (!ids.isEmpty()){
            loanRecordRepo.deleteByIds(ids);
        }
    }

    public List<LoanRecord> getLoanApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return loanRecordRepo.getLoanApprovals(employeeId, approvalStatus.name());
    }

    public List<LoanRecord> getLoanApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return loanRecordRepo.getLoanApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }

    public List<LoanRecord> getReport() throws Exception{
        return jdbcTemplate.query("select l.* from loan_records l UNION ALL " +
                "SELECT lh.* from loan_record_history lh", new BeanPropertyRowMapper<>(LoanRecord.class));
    }

}

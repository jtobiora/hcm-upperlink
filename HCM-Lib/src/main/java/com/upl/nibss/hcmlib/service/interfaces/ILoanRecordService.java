package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.LoanRecord;

import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
public interface ILoanRecordService {

    List<LoanRecord> getAll() throws Exception;

    List<LoanRecord> getAllAjax() throws Exception;

    List<LoanRecord> getAllAjaxPerEmployee(Long employeeId) throws Exception;

    List<LoanRecord> getAllByEmployeeAndLoanType(Long employeeId, Long loanTypeId) throws Exception;

    LoanRecord getByEmployee(Long employeeId) throws Exception;

    LoanRecord get(Long id) throws  Exception;

    LoanRecord getAjax(Long id) throws  Exception;

    int countOfPendingLoanRequest(Long employeeId, Long loanTypeId) throws  Exception;

    LoanRecord save(LoanRecord loanRecord) throws Exception;

    void delete(List<Long> ids) throws Exception;

    void deleteByObject(List<LoanRecord> loanRecords) throws Exception;

    List<LoanRecord> getLoanApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception;

    List<LoanRecord> getLoanApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception;
}

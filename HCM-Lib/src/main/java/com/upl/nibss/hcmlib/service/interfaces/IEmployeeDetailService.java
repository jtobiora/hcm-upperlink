package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.embeddables.FinanceDetails;
import com.upl.nibss.hcmlib.embeddables.PensionInfo;
import com.upl.nibss.hcmlib.embeddables.TaxInfo;
import com.upl.nibss.hcmlib.model.EmployeeDetails;
import com.upl.nibss.hcmlib.model.LoanType;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IEmployeeDetailService {

    List<EmployeeDetails> getAllAjax() throws Exception;

    EmployeeDetails getAjax(Long id) throws Exception;

    EmployeeDetails findByEmployeeId(Long id) throws Exception;

    EmployeeDetails save(EmployeeDetails employeeDetails) throws Exception;

    List<EmployeeDetails> save(List<EmployeeDetails> employeeDetails) throws Exception;

    List<LoanType> getLoanTypeSubsidyEligibleByEmployeeId(Long employeeId) throws Exception;

    List<EmployeeDetails> getEmployeeDetailsByTenorAndLoanType(int numberOfMonths, Long loanTypeId) throws Exception;

    List<EmployeeDetails> getEmployeeDetailsByTenorAndNotLoanType(int numberOfMonths, Long loanTypeId) throws Exception;

    //updateEmployeeDetailNextUseByEmployeeIdAndLoanType
    void updateEmployeeDetailNextUseByEmployeeIdAndLoanTypeId(Long employeeId, Long loanTypeId, int numberOfMonths) throws Exception;

    //getEmployeeDetailByNextUseLessOrGreaterThanToday

    //getEmployeeDetailByLoanTypeAndNextUseLessOrGreaterThanToday

    PensionInfo getPensionInfo(Long employeeId) throws Exception;

    TaxInfo getTaxInfo(Long employeeId) throws Exception;

    FinanceDetails getFinanceDetail(Long employeeId) throws Exception;

}

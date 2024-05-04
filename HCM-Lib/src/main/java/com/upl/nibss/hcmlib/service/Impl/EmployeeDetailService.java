package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.embeddables.FinanceDetails;
import com.upl.nibss.hcmlib.embeddables.PensionInfo;
import com.upl.nibss.hcmlib.embeddables.TaxInfo;
import com.upl.nibss.hcmlib.model.EmployeeDetails;
import com.upl.nibss.hcmlib.model.LoanType;
import com.upl.nibss.hcmlib.repo.EmployeeDetailRepo;
import com.upl.nibss.hcmlib.repo.LoanTypeRepo;
import com.upl.nibss.hcmlib.service.interfaces.IEmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeDetailService implements IEmployeeDetailService {
    @Autowired
    private EmployeeDetailRepo employeeDetailRepo;

    @Autowired
    private LoanTypeRepo loanTypeRepo;

    @Override
    public List<EmployeeDetails> getAllAjax() throws Exception {
        return employeeDetailRepo.findAll();
    }

    @Override
    public EmployeeDetails getAjax(Long id) throws Exception {
        return employeeDetailRepo.findOne(id);
    }

    @Override
    public EmployeeDetails findByEmployeeId(Long id) throws Exception {
        return employeeDetailRepo.findByEmployeeId(id);
    }

    @Override
    public EmployeeDetails save(EmployeeDetails employeeDetails) throws Exception {
        return employeeDetailRepo.save(employeeDetails);
    }

    @Override
    public List<EmployeeDetails> save(List<EmployeeDetails> employeeDetails) throws Exception {
        return employeeDetailRepo.save(employeeDetails);
    }

    @Override
    public List<LoanType> getLoanTypeSubsidyEligibleByEmployeeId(Long employeeId) throws Exception {
        return loanTypeRepo.findAllEligibleLoanTypesSubsidyByEmployeeId(employeeId);
    }

    @Override
    public List<EmployeeDetails> getEmployeeDetailsByTenorAndLoanType(int numberOfMonths, Long loanTypeId) throws Exception {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusMonths(numberOfMonths);
        return employeeDetailRepo.getEmployeeDetailsByLoanTypeAndTenor(localDate.getMonthValue(), localDate.getYear(), loanTypeId);
    }

    @Override
    public List<EmployeeDetails> getEmployeeDetailsByTenorAndNotLoanType(int numberOfMonths, Long loanTypeId) throws Exception {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusMonths(numberOfMonths);
        return employeeDetailRepo.getEmployeeDetailsByTenorAndNotLoanType(localDate.getMonthValue(), localDate.getYear(), loanTypeId);
    }

    @Override
    public void updateEmployeeDetailNextUseByEmployeeIdAndLoanTypeId(Long employeeId, Long loanTypeId, int numberOfMonths) throws Exception {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusMonths(numberOfMonths);
        Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        employeeDetailRepo.updateEmployeeDetailNextUseByEmployeeIdAndLoanTypeId(employeeId,loanTypeId,date);
    }

    @Override
    public PensionInfo getPensionInfo(Long employeeId) throws Exception {
        return employeeDetailRepo.getPensionInfoById(employeeId);
    }

    @Override
    public TaxInfo getTaxInfo(Long employeeId) throws Exception {
        return employeeDetailRepo.getTaxInfoById(employeeId);
    }

    @Override
    public FinanceDetails getFinanceDetail(Long employeeId) throws Exception {
        return employeeDetailRepo.getFinanceInfoById(employeeId);
    }
}

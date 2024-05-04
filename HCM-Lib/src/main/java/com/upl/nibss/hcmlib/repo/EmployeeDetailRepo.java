package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.embeddables.FinanceDetails;
import com.upl.nibss.hcmlib.embeddables.PensionInfo;
import com.upl.nibss.hcmlib.embeddables.TaxInfo;
import com.upl.nibss.hcmlib.model.EmployeeDetails;
import com.upl.nibss.hcmlib.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Transactional
public interface EmployeeDetailRepo extends JpaRepository<EmployeeDetails, Long> {

    @Query("select ed from EmployeeDetails ed where ed.employeeId.id = :id")
    EmployeeDetails findByEmployeeId(@Param("id") Long id);

    @Query(value = "select ed.* from employee_details_loan_types el, employee_details ed, employees e WHERE el.employee_details_id = ed.id and ed.employee_id = e.id and el.loan_type_type_id = :loanTypeId and e.activated = true and MONTH(e.actual_resumption_date) <= :theMonth and YEAR(e.actual_resumption_date) <= :theYear ", nativeQuery = true)
    List<EmployeeDetails> getEmployeeDetailsByLoanTypeAndTenor(@Param("theMonth") int month, @Param("theYear") int year, @Param("loanTypeId") Long loanTypeId);

    @Query(value = "select ed.* from employee_details_loan_types el, employee_details ed, employees e WHERE (el.employee_details_id <> ed.id or el.loan_type_type_id <> :loanTypeId) and ed.employee_id = e.id and e.activated = true and MONTH(e.actual_resumption_date) <= :theMonth and YEAR(e.actual_resumption_date) <= :theYear ", nativeQuery = true)
    List<EmployeeDetails> getEmployeeDetailsByTenorAndNotLoanType(@Param("theMonth") int month, @Param("theYear") int year, @Param("loanTypeId") Long loanTypeId);

    //updateEmployeeDetailNextUseByEmployeeIdAndLoanType
    @Modifying
    @Query(value = "update employee_details_loan_types el set el.next_use = :nextDate WHERE el.loan_type_type_id = :loanTypeId AND el.employee_details_id = (SELECT ed.id FROM employee_details ed WHERE ed.employee_id = :employeeId ) ", nativeQuery = true)
    void updateEmployeeDetailNextUseByEmployeeIdAndLoanTypeId(@Param("employeeId") Long employeeId, @Param("loanTypeId") Long loanTypeId, @Param("nextDate") Date date);

//    getEmployeeDetailByNextUseLessThanOrGreaterThanToday

//    getEmployeeDetailByLoanTypeAndNextUseLessOrGreaterThanToday

    @Query("select ed.pensionInfo from EmployeeDetails ed where ed.employeeId.id = :employeeId")
    PensionInfo getPensionInfoById(@Param("employeeId") Long employeeId);

    @Query("select ed.taxInfo from EmployeeDetails ed where ed.employeeId.id = :employeeId")
    TaxInfo getTaxInfoById(@Param("employeeId") Long employeeId);

    @Query("select ed.financeDetail from EmployeeDetails ed where ed.employeeId.id = :employeeId")
    FinanceDetails getFinanceInfoById(@Param("employeeId") Long employeeId);

}

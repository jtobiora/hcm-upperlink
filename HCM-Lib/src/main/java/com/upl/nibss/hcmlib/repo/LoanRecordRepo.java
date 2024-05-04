package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Transactional
@Repository
public interface LoanRecordRepo extends JpaRepository<LoanRecord, Long> {

//    @Query("select new com.upl.nibss.hcmlib.proxyClass.AjaxLoanRequest(l.id, l.employee, l.loanType, l.bank, l.purposeOfLoan, l.amount, l.paymentPeriod, l.sourceOfRepayment, l.requestAuthorization) from LoanRequest l ")
//    List<AjaxLoanRequest> findAllAjax();
//
    @Query("select l from LoanRecord l where l.id = :id")
    LoanRecord findAjax(@Param("id") Long id);

    @Query("select l from LoanRecord l where l.employee.id = :employeeId")
    List<LoanRecord> findAllAjaxPerEmployee(@Param("employeeId") Long employeeId);

    @Query("select l from LoanRecord l where l.employee.id = :employeeId and l.loanType.typeId = :loanTypeId")
    List<LoanRecord> findAllByEmployeeAndLoanType(@Param("employeeId") Long employeeId, @Param("loanTypeId") Long loanTypeId);

    @Query("select l from LoanRecord l where l.employee.id = :employeeId")
    LoanRecord findByEmployeeId(@Param("employeeId") Long employeeId);

    @Modifying
    @Query("delete from LoanRecord l where l.id in :id")
    int deleteByIds(@Param("id") List<Long> id);

    @Query("select count(l.id) from LoanRecord l where l.employee.id = :employeeId and l.loanType.typeId = :loanTypeId")
    int countOfPendingLoanRequest(@Param("employeeId") Long employeeId, @Param("loanTypeId") Long loanTypeId);

    @Query(value = "select l.* from loan_records l, loan_records_request_authorization la, authorizers a, authorization_roles ar, employees e " +
            "WHERE l.id = la.loan_record_id and la.request_authorization_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) " +
            "or (ar.supervisor_category = 'SUPERVISOR' and e.id = l.employee_id and e.supervisor_id = :employeeId) " +
            "or (ar.supervisor_category = 'SECOND_LEVEL_SUPERVISOR' and e.id = l.employee_id and e.second_level_supervisor_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and l.approval_status = :approvalStatus ", nativeQuery = true)
    List<LoanRecord> getLoanApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select l.* from loan_records l, loan_records_request_authorization la, authorizers a, employees e " +
            "WHERE l.id = la.loan_record_id and la.request_authorization_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<LoanRecord> getLoanApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);
}

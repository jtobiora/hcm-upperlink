package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by toyin.oladele on 25/11/2017.
 */
@Transactional
@Repository
public interface TransferRecordRepo extends JpaRepository<TransferRecord,Long> {

    @Query("select t from TransferRecord t where t.employee.id = :employeeId and t.deleted = false ")
    List<TransferRecord> findAllByEmployeeAndDeleted(@Param("employeeId") Long employeeId);

    @Query("select t from TransferRecord t where t.employee.id = :employeeId and t.deleted = false and t.approvalStatus = :approvalStatus ")
    List<TransferRecord> findAllByEmployeeAndApprovalStatus(@Param("employeeId") Long employeeId, @Param("approvalStatus") ApprovalStatus approvalStatus);

    @Modifying
    @Query("delete from TransferRecord t where t.id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);

    //get RotationByEffectiveDateAndApproved
    @Query("select t from TransferRecord t where t.effectiveDate = :theDate and t.approvalStatus = 'APPROVED'")
    List<TransferRecord> getTransferRecordByEffectiveDateAndAndApprovedApprovalStatus(@Param("theDate") Date date);

    @Query(value = "select t.* from transfer_record t, transfer_request_authorizations ta, authorizers a, authorization_roles ar, employees e " +
            "WHERE t.id = ta.transfer_record_id and ta.authoriser_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) " +
            "or (ar.supervisor_category = 'SUPERVISOR' and e.id = t.employee_id and e.supervisor_id = :employeeId) " +
            "or (ar.supervisor_category = 'SECOND_LEVEL_SUPERVISOR' and e.id = t.employee_id and e.second_level_supervisor_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and t.approval_status = :approvalStatus ", nativeQuery = true)
    List<TransferRecord> getTransferApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select t.* from transfer_record t, transfer_request_authorizations ta, authorizers a, employees e " +
            "WHERE t.id = ta.transfer_record_id and ta.authoriser_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<TransferRecord> getTransferApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

}

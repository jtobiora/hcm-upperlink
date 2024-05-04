package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.RotationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface RotationRecordRepo extends JpaRepository<RotationRecord, Long> {
    @Query("select r from RotationRecord r where r.employee.id = :id and deleted = false")
    RotationRecord findRotationRecordByEmployeeId(@Param("id") Long id);

    @Query("select r from RotationRecord r where r.employee.id = :id and deleted = false")
    List<RotationRecord> findRotationRecordsByEmployeeId(@Param("id") Long id);

    @Query("select r from RotationRecord r where r.employee.id = :id and deleted = false and r.approvalStatus = :approvalStatus ")
    List<RotationRecord> findAllByEmployeeIdAndApprovalStatus(@Param("id") Long employeeId, @Param("approvalStatus") ApprovalStatus approvalStatus);

    @Query("select r from RotationRecord r where r.employee.id = :id and deleted = false and r.approvalStatus = :approvalStatus ")
    RotationRecord findByEmployeeIdAndApprovalStatus(@Param("id") Long employeeId, @Param("approvalStatus") ApprovalStatus approvalStatus);

    @Modifying
    @Query("delete from RotationRecord r where r.id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);

    //get RotationByEffectiveDateAndApproved
    @Query("select r from RotationRecord r where r.effectiveDate = :theDate and r.approvalStatus = 'APPROVED'")
    List<RotationRecord> getRotationRecordByEffectiveDateAndAndApprovedApprovalStatus(@Param("theDate")Date date);

    //get RotationByEndDateAndApproved
    @Query("select r from RotationRecord r where r.endDate = :theDate and r.approvalStatus = 'APPROVED'")
    List<RotationRecord> getRotationRecordByEndDateAndAndApprovedApprovalStatus(@Param("theDate")Date date);

    @Query(value = "select r.* from rotation_record r, rotation_record_request_authorizers ra, authorizers a, authorization_roles ar, employees e, departments d " +
            "WHERE r.id = ra.rotation_record_id and ra.request_authorizers_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) " +
            "or (ar.rotation_category = 'SENDING_SUPERVISOR' and e.id = r.employee_id and e.supervisor_id = :employeeId) " +
            "or (ar.rotation_category = 'SENDING_DEPARTMENT_HEAD' and d.id = r.old_department_id and d.dept_head = :employeeId) " +
            "or (ar.rotation_category = 'RECEIVING_SUPERVISOR' and e.id = r.receiving_supervisor_id and e.supervisor_id = :employeeId) " +
            "or (ar.rotation_category = 'RECEIVING_DEPARTMENT_HEAD' and r.receiving_dh_id = :employeeId) " +
            "or (ar.rotation_category = 'RECEIVING_EXECUTIVE_DEPARTMENT' and r.receiving_ed_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and r.approval_status = :approvalStatus ", nativeQuery = true)
    List<RotationRecord> getRotationApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select r.* from rotation_record r, rotation_record_request_authorizers ra, authorizers a, employees e " +
            "WHERE r.id = ra.rotation_record_id and ra.request_authorizers_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<RotationRecord> getRotationApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

}

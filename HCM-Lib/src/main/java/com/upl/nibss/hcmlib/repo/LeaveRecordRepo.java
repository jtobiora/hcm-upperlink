package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.LeaveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 10/12/2017.
 */
@Transactional
@Repository
public interface LeaveRecordRepo extends JpaRepository<LeaveRecord, Long> {

    @Query("select l from LeaveRecord l where l.employee.id = :employeeId")
    List<LeaveRecord> findAllByEmployee(@Param("employeeId") Long employeeId);

    @Query(value = "select l.* from leave_record l, employees e WHERE l.employee_id = e.id and e.department_id = :deptId",nativeQuery = true)
    List<LeaveRecord> findAllByDepartment(@Param("deptId") Long deptId);

    @Query(value = "select l.* from leave_record l, employees e, leave_type lt WHERE l.employee_id = e.id and l.leave_type_id = lt.id" +
            " and e.department_id = :deptId and lt.name = :leaveTypeName",nativeQuery = true)
    List<LeaveRecord> findAllByDepartmentAAndLeaveType(@Param("deptId") Long deptId, @Param("leaveTypeName") String leaveTypeName);

    @Query(value = "select l.* from leave_record l, employees e, leave_type lt WHERE l.employee_id = e.id and l.leave_type_id = lt.id" +
            " and e.department_id = :deptId and lt.name != :leaveTypeName",nativeQuery = true)
    List<LeaveRecord> findAllByDepartmentAAndNotLeaveType(@Param("deptId") Long deptId, @Param("leaveTypeName") String leaveTypeName);

    @Query("select l from LeaveRecord l where l.employee.id = :employeeId and l.leaveType.name <> :leaveTypeName")
    List<LeaveRecord> findAllByEmployeeAndNotType(@Param("employeeId") Long employeeId, @Param("leaveTypeName") String leaveTypeName);

    @Query("select l from LeaveRecord l where l.employee.id = :employeeId and l.leaveType.name = :leaveTypeName")
    List<LeaveRecord> findAllByEmployeeAndType(@Param("employeeId") Long employeeId, @Param("leaveTypeName") String leaveTypeName);

    @Query("select l from LeaveRecord l where l.leaveType.name <> :leaveTypeName")
    List<LeaveRecord> findAllByNotType(@Param("leaveTypeName") String leaveTypeName);

    @Query("select l from LeaveRecord l where l.leaveType.name = :leaveTypeName")
    List<LeaveRecord> findAllByType(@Param("leaveTypeName") String leaveTypeName);

    @Modifying
    @Query("delete from LeaveRecord l where l.id in :id")
    int deleteByIds(@Param("id") List<Long> id);

    @Query("select count(l.id) from LeaveRecord l where l.employee.id = :employeeId and l.leaveType.id = :leaveTypeId and l.approvalStatus = :approvalStatus")
    int countOfPendingLeaveByEmployee(@Param("employeeId") Long employeeId,
                                      @Param("approvalStatus") ApprovalStatus approvalStatus,
                                      @Param("leaveTypeId") Long leaveTypeId);

    @Query("select l from LeaveRecord l where l.employee.id = :employeeId and l.id = :id")
    LeaveRecord findByEmployeeAndId(@Param("employeeId") Long employeeId, @Param("id") Long id);

    @Query("select l.handoverNote from LeaveRecord l where l.employee.id = :employeeId and l.id = :id")
    Document getHandOverNoteByEmployeeIdAndId(@Param("employeeId") Long employeeId, @Param("id") Long id);

    @Query(value = "select l.* from leave_record l, leave_request_authorizations la, authorizers a, authorization_roles ar, employees e " +
            "WHERE l.id = la.leave_record_id and la.authoriser_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) or (ar.supervisor_category = 'SUPERVISOR' and e.id = l.employee_id and e.supervisor_id = :employeeId) or (ar.supervisor_category = 'SECOND_LEVEL_SUPERVISOR' and e.id = l.employee_id and e.second_level_supervisor_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and l.approval_status = :approvalStatus ", nativeQuery = true)
    List<LeaveRecord> getLeaveApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select l.* from leave_record l, leave_request_authorizations la, authorizers a, employees e " +
            "WHERE l.id = la.leave_record_id and la.authoriser_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<LeaveRecord> getLeaveApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select l.* FROM leave_record l, employees e, employee_leave_days el " +
            "WHERE el.leave_record_id = l.id and l.employee_id = e.id and el.leave_days = curdate() AND l.approval_status = 'APPROVED'", nativeQuery = true)
    List<LeaveRecord> getEmployeesOnLeave();

    @Query(value = "select l.* FROM leave_record l, employees e, employee_leave_days el " +
            "WHERE el.leave_record_id = l.id and l.employee_id = e.id and e.department_id in :departmentIds and el.leave_days = curdate() AND l.approval_status = 'APPROVED'", nativeQuery = true)
    List<LeaveRecord> getEmployeesOnLeaveInDepartments(@Param("departmentIds") List<Long> departmentIds);

    @Query("select l from LeaveRecord l where l.employee.id = :employeeId and l.approvalStatus = 'APPROVED'")
    List<LeaveRecord> getEmployeeApprovedLeaves(@Param("employeeId") Long employeeId);


}

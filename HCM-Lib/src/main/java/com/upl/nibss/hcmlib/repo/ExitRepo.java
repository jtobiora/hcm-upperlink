package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Exit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ExitRepo extends JpaRepository<Exit, Long> {
    @Query("select r from Exit r where r.employee.id = :id and r.deleted = false order by r.createdAt asc")
    Exit findExitByEmployeeId(@Param("id") Long id);

    @Modifying
    @Query("delete from Exit r where r.id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);

    @Query(value = "select ex.* from exit_record ex, exit_record_request_authorizers ea, authorizers a, authorization_roles ar, employees e " +
            "WHERE ex.id = ea.exit_id and ea.request_authorizers_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) or (ar.supervisor_category = 'SUPERVISOR' and e.id = ex.employee_id and e.supervisor_id = :employeeId) or (ar.supervisor_category = 'SECOND_LEVEL_SUPERVISOR' and e.id = ex.employee_id and e.second_level_supervisor_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and ex.approval_status = :approvalStatus ", nativeQuery = true)
    List<Exit> getExitApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select ex.* from exit_record ex, exit_record_request_authorizers ea, authorizers a, employees e " +
            "WHERE ex.id = ea.exit_id and ea.request_authorizers_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<Exit> getExitApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);
}

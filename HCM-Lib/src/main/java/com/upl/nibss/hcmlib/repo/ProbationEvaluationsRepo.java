package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.ProbationEvaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 07/12/2017.
 */
@Transactional
@Repository
public interface ProbationEvaluationsRepo extends JpaRepository<ProbationEvaluations, Long> {

    @Query("select p from ProbationEvaluations p where p.employee.id = :employee_id and p.deleted = false ")
    ProbationEvaluations findByEmployee(@Param("employee_id") Long employee_id);

    @Query("select p from ProbationEvaluations p where p.employee.id = :employee_id and p.supervisor.id = :supervisor_id and p.deleted = false ")
    ProbationEvaluations findByEmployeeAAndSupervisor(@Param("employee_id") Long employee_id, @Param("supervisor_id") Long supervisor_id);

    @Query(value = "select p.* from probation_evaluations p, probation_evaluations_request_authorization pa, authorizers a, authorization_roles ar, employees e " +
            "WHERE p.id = pa.probation_evaluations_id and pa.request_authorization_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) " +
            "or (ar.supervisor_category = 'SUPERVISOR' and e.id = p.employee_id and e.supervisor_id = :employeeId) " +
            "or (ar.supervisor_category = 'SECOND_LEVEL_SUPERVISOR' and e.id = p.employee_id and e.second_level_supervisor_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and p.approval_status = :approvalStatus ", nativeQuery = true)
    List<ProbationEvaluations> getProbationEvaluationApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select p.* from probation_evaluations p, probation_evaluations_request_authorization pa, authorizers a, employees e " +
            "WHERE p.id = pa.probation_evaluations_id and pa.request_authorization_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<ProbationEvaluations> getProbationEvaluationApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);
}

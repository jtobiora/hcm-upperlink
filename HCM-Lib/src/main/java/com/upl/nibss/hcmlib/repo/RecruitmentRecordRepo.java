package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.RecruitmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RecruitmentRecordRepo extends JpaRepository<RecruitmentRecord, Long> {

    @Query("select r from RecruitmentRecord r where r.supervisor.id = :supervisorId and deleted = false ")
    List<RecruitmentRecord> findAllBySupervisorAndDeleted(@Param("supervisorId") Long supervisorId);

    @Query("select r from RecruitmentRecord r where r.supervisor.id = :supervisorId and deleted = false ")
    RecruitmentRecord findBySupervisorAndDeleted(@Param("supervisorId") Long supervisorId);

    @Query("select r from RecruitmentRecord r where r.supervisor.id = :supervisorId and deleted = false and r.approvalStatus = 'PENDING_APPROVAL' ")
    RecruitmentRecord findPendingBySupervisorId(@Param("supervisorId") Long supervisorId);

    @Query("select r from RecruitmentRecord r where r.id = :id and r.supervisor.id = :supervisorId and deleted = false ")
    RecruitmentRecord findByIdAndSupervisorAndDeleted(@Param("id") Long id, @Param("supervisorId") Long supervisorId);

    @Modifying
    @Query("delete from RecruitmentRecord r where r.id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);

    @Query(value = "select r.* from recruitment_record r, recruitment_request_authorizations ra, authorizers a, authorization_roles ar, employees e " +
            "WHERE r.id = ra.recruitment_record_id and ra.authoriser_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) " +
            "or (ar.supervisor_category = 'SUPERVISOR' and r.supervisor_id = :employeeId) " +
            "or (ar.supervisor_category = 'SECOND_LEVEL_SUPERVISOR' and r.supervisor_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and r.approval_status = :approvalStatus ", nativeQuery = true)
    List<RecruitmentRecord> getRecruitmentApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select r.* from recruitment_record r, recruitment_request_authorizations ra, authorizers a, employees e " +
            "WHERE r.id = ra.recruitment_record_id and ra.authoriser_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<RecruitmentRecord> getRecruitmentApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select r.* from recruitment_record r union all select * from recruitment_record_history rh", nativeQuery = true)
    List<RecruitmentRecord> getRecruitmentReport(@Param("employee") String subQuery);

}

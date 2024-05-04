package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.TrainingBookingStatus;
import com.upl.nibss.hcmlib.model.TrainingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by toyin.oladele on 11/12/2017.
 */
@Transactional
@Repository
public interface TrainingRecordRepo extends JpaRepository<TrainingRecord, Long> {

    //getALLByStatus
    @Query("select t from TrainingRecord t where t.status = :status and t.deleted = false")
    List<TrainingRecord> getAllByStatus(@Param("status") TrainingBookingStatus status);

    //getALLByNotStatus
    @Query("select t from TrainingRecord t where t.status <> :status and t.deleted = false")
    List<TrainingRecord> getAllByNotStatus(@Param("status") TrainingBookingStatus status);

    //getAllByDepartment
    @Query("select t from TrainingRecord t where t.department.id = :departmentId and t.deleted = false")
    List<TrainingRecord> getAllByDepartment(@Param("departmentId") Long departmentId);

    //getAllBySupervisor
    @Query("select t from TrainingRecord t where t.supervisor.id = :supervisorId and t.deleted = false")
    List<TrainingRecord> getAllBySupervisor(@Param("supervisorId") Long supervisorId);

    //getAllByEmployee
    @Query(value = "select t.* from training_records t, training_records_trainees tr where t.id = tr.training_record_id and tr.trainees_id = :employeeId and t.deleted = false", nativeQuery = true)
    List<TrainingRecord> getAllByEmployee(@Param("employeeId") Long employeeId);

//    //getAllByEmployee
//    @Query("select t from TrainingRecord t where t.supervisor.id = :supervisorId and t.deleted = false")
//    List<TrainingRecord> getAllBySupervisor(@Param("supervisorId") Long supervisorId);

    @Modifying
    @Query("delete from TrainingRecord t where t.id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);

    @Query("select t from TrainingRecord t where t.training_start_day = :startDate and t.deleted = false and t.status = 'BOOKED'")
    List<TrainingRecord> findAllByStartDayAndBooked(@Param("startDate") Date date);

//    @Query("select t from TrainingRecord t where t.training_start_day = :startDate and t.deleted = false and t.status = 'BOOKED' and t.approvalStatus = 'APPROVED'")
//    List<TrainingRecord> findAllByStartDayAndBookedAndApproved(@Param("startDate") Date date);

    @Query("select t from TrainingRecord t where t.training_start_day >= :theDate and t.training_end_day <= :theDate and t.deleted = false and t.approvalStatus = 'APPROVED' and t.status <> 'ONGOING'")
    List<TrainingRecord> findAllWithinDay(@Param("theDate") Date date);

    @Query("select t from TrainingRecord t where t.training_end_day < :theDate and t.deleted = false and t.approvalStatus = 'APPROVED' and t.status <> 'USED'")
    List<TrainingRecord> findAllUsedTrainingToUpdated(@Param("theDate") Date date);

    @Query("select t from TrainingRecord t where t.training_start_day < :theDate and t.deleted = false and t.approvalStatus <> 'APPROVED' and t.status <> 'EXPIRED'")
    List<TrainingRecord> findAllUnapprovedBeforeDay(@Param("theDate") Date date);

    @Query(value = "select t.* from training_records t, training_request_authorizations ta, authorizers a, authorization_roles ar, employees e " +
            "WHERE t.id = ta.training_record_id and ta.authoriser_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) " +
            "or (ar.supervisor_category = 'SUPERVISOR' and t.supervisor_id = :employeeId) " +
            "or (ar.supervisor_category = 'SECOND_LEVEL_SUPERVISOR' and t.supervisor_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and t.approval_status = :approvalStatus ", nativeQuery = true)
    List<TrainingRecord> getTrainingApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select t.* from training_records t, training_request_authorizations ta, authorizers a, employees e " +
            "WHERE t.id = ta.training_record_id and ta.authoriser_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<TrainingRecord> getTrainingApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

}

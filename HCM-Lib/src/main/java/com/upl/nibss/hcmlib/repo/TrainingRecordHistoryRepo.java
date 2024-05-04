package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.TrainingBookingStatus;
import com.upl.nibss.hcmlib.model.TrainingRecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 11/12/2017.
 */
@Transactional
@Repository
public interface TrainingRecordHistoryRepo extends JpaRepository<TrainingRecordHistory, Long> {

    //getALLByStatus
    @Query("select t from TrainingRecordHistory t where t.status = :status and t.deleted = false")
    List<TrainingRecordHistory> getAllByStatus(@Param("status") TrainingBookingStatus status);

    //getALLByNotStatus
    @Query("select t from TrainingRecordHistory t where t.status <> :status and t.deleted = false")
    List<TrainingRecordHistory> getAllByNotStatus(@Param("status") TrainingBookingStatus status);

    //getAllByDepartment
    @Query("select t from TrainingRecordHistory t where t.department.id = :departmentId and t.deleted = false")
    List<TrainingRecordHistory> getAllByDepartment(@Param("departmentId") Long departmentId);

    //getAllBySupervisor
    @Query("select t from TrainingRecordHistory t where t.supervisor.id = :supervisorId and t.deleted = false")
    List<TrainingRecordHistory> getAllBySupervisor(@Param("supervisorId") Long supervisorId);

//    //getAllByEmployee
//    @Query("select t from TrainingRecordHistory t where t.supervisor.id = :supervisorId and t.deleted = false")
//    List<TrainingRecordHistory> getAllBySupervisor(@Param("supervisorId") Long supervisorId);

    @Query(value = "select t.* from training_record_history t where year(t.training_start_day) = :selectedYear", nativeQuery = true)
    List<TrainingRecordHistory> getAllByYear(@Param("selectedYear") Long year);

    @Query(value = "select t.* from training_record_history t where year(t.training_start_day) = :selectedYear and t.id = :id ", nativeQuery = true)
    TrainingRecordHistory getAllByYearAndId(@Param("selectedYear") Long year, @Param("id") Long id);

    @Query(value = "select t.* from training_record_history t where year(t.training_start_day) = :selectedYear and t.supervisor_id = :supervisorId ", nativeQuery = true)
    List<TrainingRecordHistory> getAllByYearAndSupervisorId(@Param("selectedYear") Long year, @Param("supervisorId") Long supervisorId);

    @Query(value = "select t.* from training_records t, training_records_trainees tr where t.id = tr.training_record_id and tr.trainees_id = :employeeId and year(t.training_start_day) = :selectedYear", nativeQuery = true)
    List<TrainingRecordHistory> getAllByYearAndEmployeeId(@Param("selectedYear") Long year, @Param("employeeId") Long employeeId);

    @Query(value = "select t.* from training_record_history t where year(t.training_start_day) = :selectedYear and t.department_id = :departmentId ", nativeQuery = true)
    List<TrainingRecordHistory> getAllByYearAndDepartmentId(@Param("selectedYear") Long year, @Param("departmentId") Long departmentId);
}

package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.LeaveStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 08/12/2017.
 */
@Transactional
@Repository
public interface LeaveStandardRepo extends JpaRepository<LeaveStandard, Long> {

    //get by leaveType
    @Query("select l from LeaveStandard l where l.activated = true and l.deleted = false order by l.gradeLevel, l.leaveType desc")
    List<LeaveStandard> findAllAndActivated();

    //get by leaveType e.g. ANNUAL LEAVE
    @Query("select l from LeaveStandard l where l.leaveType.id = :leaveTypeId and l.deleted = false ")
    List<LeaveStandard> findAllByType(@Param("leaveTypeId") Long leaveTypeId);

    //other leave
    @Query("select l from LeaveStandard l where l.leaveType.id <> :leaveTypeId and l.deleted = false ")
    List<LeaveStandard> findAllByNotType(@Param("leaveTypeId") Long leaveTypeId);

    //get by leaveType
    @Query("select l from LeaveStandard l where l.leaveType.id = :leaveTypeId and l.activated = true and l.deleted = false ")
    List<LeaveStandard> findAllByTypeAndActivated(@Param("leaveTypeId") Long leaveTypeId);

    //get by leaveType and gradelevel
    @Query("select l from LeaveStandard l where l.leaveType.id = :leaveTypeId and l.gradeLevel.id = :gradelevelId and l.activated = true and l.deleted = false ")
    List<LeaveStandard> findByTypeAndGradeLevelAndActivated(@Param("leaveTypeId") Long leaveTypeId, @Param("gradelevelId") Long gradelevelId);

    //check the existence of duplicate leaveType and gradelevel
    @Query("select count(l.id) from LeaveStandard l where l.leaveType.id = :leaveTypeId and l.gradeLevel.id = :gradelevelId")
    Integer countOfSimilarLeaveTypeAndGradeLevel(@Param("leaveTypeId") Long leaveTypeId, @Param("gradelevelId") Long gradelevelId);

    @Query("select count(l.id) from LeaveStandard l where l.leaveType.id = :leaveTypeId and l.gradeLevel.id = :gradelevelId and l.id <> :id")
    Integer countOfSimilarLeaveTypeAndGradeLevelNotId(@Param("leaveTypeId") Long leaveTypeId, @Param("gradelevelId") Long gradelevelId, @Param("id") Long id);

    @Modifying
    @Query("update LeaveStandard l set l.activated = case l.activated when true then false else true end where l.id = :id")
    int toggle(@Param("id") Long id);

    @Query("select l.leaveDays from LeaveStandard l where l.gradeLevel.id = :gradelevelId and l.leaveType.id = :leaveTypeId and l.activated = true and l.deleted = false ")
    Integer countOfLeaveDaysPerGradeLevelAndLeaveType(@Param("gradelevelId") Long gradelevelId, @Param("leaveTypeId") Long leaveTypeId);

    @Query("select l.leaveDays from LeaveStandard l where l.leaveType.id = :leaveTypeId and l.activated = true and l.deleted = false ")
    Integer countOfLeaveDaysPerLeaveType(@Param("leaveTypeId") Long leaveTypeId);

    @Query(value = "select ls.* from leave_standards ls, employees e WHERE ls.grade_level_id = e.grade and e.id = :employeeId AND ls.deleted = false AND ls.activated = TRUE ", nativeQuery = true)
    List<LeaveStandard> getLeaveStandardByEmployeeId(@Param("employeeId") Long employeeId);

}

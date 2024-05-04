package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.LeaveRecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 10/12/2017.
 */
@Transactional
@Repository
public interface LeaveRecordHistoryRepo extends JpaRepository<LeaveRecordHistory, Long> {

    @Query("select l from LeaveRecordHistory l where l.employee.id = :employeeId")
    List<LeaveRecordHistory> findAllByEmployee(@Param("employeeId") Long employeeId);

    @Query("select l from LeaveRecordHistory l where l.employee.id = :employeeId and l.id = :id")
    List<LeaveRecordHistory> findAllByEmployeeAndId(@Param("employeeId") Long employeeId, @Param("id") Long id);

    @Query(value = "select l.* from employee_leave_days_history e, leave_record_history l, leave_type lt where e.leave_record_history_id = l.id and l.leave_type_id = lt.id " +
            "and l.employee_id = :employeeId and lt.name = :leaveTypeName and year(e.leave_days) = :selectedYear ", nativeQuery = true)
    List<LeaveRecordHistory> getAllByYearAndType(@Param("employeeId") Long employeeId, @Param("leaveTypeName") String leaveTypeName, @Param("selectedYear") Long year);

    @Query(value = "select l.* from employee_leave_days_history e, leave_record_history l, leave_type lt where e.leave_record_history_id = l.id and l.leave_type_id = lt.id " +
            "and l.employee_id = :employeeId and lt.name != :leaveTypeName and year(e.leave_days) = :selectedYear ", nativeQuery = true)
    List<LeaveRecordHistory> getAllByYearAndNotType(@Param("employeeId") Long employeeId, @Param("leaveTypeName") String leaveTypeName, @Param("selectedYear") Long year);
}

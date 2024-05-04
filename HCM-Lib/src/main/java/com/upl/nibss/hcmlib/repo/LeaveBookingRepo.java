package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.LeaveBookingStatus;
import com.upl.nibss.hcmlib.model.LeaveBookings;
import com.upl.nibss.hcmlib.model.auditModel.LeaveBookingAud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by toyin.oladele on 09/12/2017.
 */
@Transactional
@Repository
public interface LeaveBookingRepo extends JpaRepository<LeaveBookings, Long> {

    //get by department
    @Query("select l from LeaveBookings l where l.employee.department.id = :departmentId ")
    List<LeaveBookings> findAllByEmployeeDepartment(@Param("departmentId") Long departmentId);

    //get by employee
    @Query("select l from LeaveBookings l where l.employee.id = :employeeId ")
    List<LeaveBookings> findAllByEmployee(@Param("employeeId") Long employeeId);

    //get by status
    @Query("select l from LeaveBookings l where l.status = :status ")
    List<LeaveBookings> findAllByStatus(@Param("status") LeaveBookingStatus status);

    //get by status
    @Query("select l from LeaveBookings l where l.status <> :status ")
    List<LeaveBookings> findAllByNotStatus(@Param("status") LeaveBookingStatus status);

    //count by status
    @Query("select count(l.id) from LeaveBookings l where l.employee.id = :employeeId and l.status = :status")
    Integer countByStatus(@Param("employeeId") Long employeeId , @Param("status") LeaveBookingStatus status);

    //count by status
    @Query("select count(l.id) from LeaveBookings l where l.employee.id = :employeeId")
    Integer countPerEmployee(@Param("employeeId") Long employeeId);

    @Query(value = "select l.day from leave_bookings l, employees e where l.employee_id = e.id AND e.department_id = :id group by l.day having count(l.id) >= :maxCountOfEmployeeAllowed ",nativeQuery = true)
    List<Date> findAllDayWithEmployeeCountGreaterThan(@Param("id") Long id, @Param("maxCountOfEmployeeAllowed") int maxCountOfEmployeeAllowed);

    @Query(value = "select count(l.id) from LeaveBookings l where l.employee.id = :employeeId and l.day in :bookedDates")
    int countOfSimilarBookedDaysByYou(@Param("employeeId") Long employeeId, @Param("bookedDates") List<Date> bookedDates);

    //get by status
    @Query("select l from LeaveBookings l where l.id in :ids and l.status = :status ")
    List<LeaveBookings> findBookingsByStatusAndId(@Param("ids") List<Long> ids,@Param("status") LeaveBookingStatus status);


    @Modifying
    @Query("update LeaveBookings l set l.status = :bookingStatus, l.color = :color, l.icon = :icon where l.leaveRecord.id = :leaveRecordId")
    void updateBookingStatus(@Param("leaveRecordId") Long leaveRecordId,
                             @Param("bookingStatus") LeaveBookingStatus leaveBookingStatus,
                             @Param("color") String color,
                             @Param("icon") String icon);

    @Modifying
    @Query("update LeaveBookings l set l.status = :bookingStatus, l.color = :color, l.icon = :icon, l.leaveRecord = null where l.leaveRecord.id = :leaveRecordId")
    void updateBookingStatusAndLeaveRecordToNull(@Param("leaveRecordId") Long leaveRecordId,
                             @Param("bookingStatus") LeaveBookingStatus leaveBookingStatus,
                             @Param("color") String color,
                             @Param("icon") String icon);

    @Modifying
    @Query("delete from LeaveBookings l where l.day in :days and l.employee.id = :employeeId and l.leaveRecord is null")
    int removeBookedDates(@Param("days") List<Date> days, @Param("employeeId") Long employeeId);

    //get by status per month
    @Query(value = "select * from leave_bookings l WHERE MONTH(l.day) = :theMonth and l.status = :status ",nativeQuery = true)
    List<LeaveBookings> findAllBookingsForTheMonth(@Param("status") LeaveBookingStatus status, @Param("theMonth") int month);

    //get by status per month
    @Query("select l from LeaveBookings l where l.day < :theDate and l.status = :status")
    List<LeaveBookings> findAllBookingsBeforeDay(@Param("status") LeaveBookingStatus status, @Param("theDate") Date date);

    @Modifying
    @Query("delete from LeaveBookings l where l.id in :id")
    int deleteByIds(@Param("id") List<Long> id);

//
////Auditing Query
//    @Query(value = " SELECT * from leave_bookings_aud \n#pageable\n ", countQuery = " SELECT count(id) from leave_bookings_aud", nativeQuery = true)
//    Page<LeaveBookingAud> getAllAudit(Pageable pageable);
//
//    @Query(value = " SELECT * from leave_bookings_aud WHERE modified_by = :employeeId or created_by = :employeeId \n#pageable\n ", countQuery = " SELECT count(id) from leave_bookings_aud WHERE modified_by = :employeeId or created_by = :employeeId", nativeQuery = true)
//    Page<LeaveBookingAud> getAllAuditByEmployeeId(@Param("employeeId") Long employeeId ,Pageable pageable);
//
//    @Query(value = " SELECT * from leave_bookings_aud WHERE id = :objectId \n#pageable\n ", countQuery = " SELECT count(id) from leave_bookings_aud WHERE id = :objectId", nativeQuery = true)
//    Page<LeaveBookingAud> getAllAuditByObjectId(@Param("objectId") Long objectId ,Pageable pageable);

}

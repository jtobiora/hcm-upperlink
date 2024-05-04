package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.LeaveBookingStatus;
import com.upl.nibss.hcmlib.model.LeaveBookings;
import com.upl.nibss.hcmlib.repo.LeaveBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by toyin.oladele on 09/12/2017.
 */
@Service
public class LeaveBookingService {

    @Autowired
    private LeaveBookingRepo leaveBookingRepo;

    public List<LeaveBookings> getAll() throws Exception{
        return leaveBookingRepo.findAll();
    }

    //get by department
    public List<LeaveBookings> getAllByDepartment(Long departmentId) throws Exception{
        return leaveBookingRepo.findAllByEmployeeDepartment(departmentId);
    }

    //get by employee
    public List<LeaveBookings> getAllByEmployee(Long employeeId) throws Exception{
        return leaveBookingRepo.findAllByEmployee(employeeId);
    }

    //count by status
    public int countOfLeaveBookingByStatus(Long employeeId, LeaveBookingStatus status) throws Exception{
        return leaveBookingRepo.countByStatus(employeeId, status);
    }

    //count Per Employee (EmployeeBookedDays)
    public int countOfEmployeeBookedDays(Long employeeId) throws Exception{
        return leaveBookingRepo.countPerEmployee(employeeId);
    }

    public LeaveBookings get(Long id) throws Exception{
        return leaveBookingRepo.findOne(id);
    }

    public LeaveBookings save(LeaveBookings leaveBookings) throws Exception{
        return leaveBookingRepo.save(leaveBookings);
    }

    public List<LeaveBookings> save(List<LeaveBookings> leaveBookings) throws Exception{
        return leaveBookingRepo.save(leaveBookings);
    }

    public List<Date> getAllDayWithDeptEmployeeCountGreaterThan(Long departmentId, int maxCountOfEmployeeAllowes) throws Exception{
        return leaveBookingRepo.findAllDayWithEmployeeCountGreaterThan(departmentId,maxCountOfEmployeeAllowes);
    }

    public int countOfSimilarBookedDaysByYou(Long employeeId, List<Date> dates) throws Exception{
        return leaveBookingRepo.countOfSimilarBookedDaysByYou(employeeId,dates);
    }

    public List<LeaveBookings> getLeaveBookingsByIdAndStatus(List<Long> ids, LeaveBookingStatus status) throws Exception{
        return leaveBookingRepo.findBookingsByStatusAndId(ids,status);
    }

    public void updateLeaveBookingStatus(Long leaveRecordId, LeaveBookingStatus status) throws Exception{
        leaveBookingRepo.updateBookingStatus(leaveRecordId,status,status.getColor(),status.getIcon());
    }

    public void updateLeaveBookingStatusAndLeaveRecordToNull(Long leaveRecordId, LeaveBookingStatus status) throws Exception{
        leaveBookingRepo.updateBookingStatusAndLeaveRecordToNull(leaveRecordId,status,status.getColor(),status.getIcon());
    }

    public int removeBookedDaysByEmployee(List<Date> days, Long employeeId) throws Exception {
        return leaveBookingRepo.removeBookedDates(days,employeeId);
    }

    public List<LeaveBookings> findAllBookingsForTheMonth(LeaveBookingStatus status, int month) throws Exception{
        return leaveBookingRepo.findAllBookingsForTheMonth(status, month);
    }

    public List<LeaveBookings> findAllBookingsBeforeDay(LeaveBookingStatus status, Date date) throws Exception{
        return leaveBookingRepo.findAllBookingsBeforeDay(status, date);
    }

    public void deleteByObject(List<LeaveBookings> leaveBookings) throws Exception {
        List<Long> ids = new ArrayList<>();
        leaveBookings.forEach(bookings -> ids.add(bookings.getId()));
        if (!ids.isEmpty()){
            leaveBookingRepo.deleteByIds(ids);
        }
    }

////Auditing Report >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//    //getAllAudit
//    public Page<LeaveBookingAud> getAllAudit(Pageable pageable){
//        return customLeaveBookingAudRepo.getAllAudit(pageable);
//    }
//
//    //getAllAuditByEmployeeId
//    public Page<LeaveBookingAud> getAllAuditByEmployeeId(Long employeeId, Pageable pageable){
//        return customLeaveBookingAudRepo.getAllAuditByEmployeeId(employeeId, pageable);
//    }
//
//    //getAllAuditByObjectId
//    public Page<LeaveBookingAud> getAllAuditByObjectId(Long objectId, Pageable pageable){
//        return customLeaveBookingAudRepo.getAllAuditByObjectId(objectId, pageable);
//    }

}

package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.LeaveRecord;
import com.upl.nibss.hcmlib.repo.EmployeeRepo;
import com.upl.nibss.hcmlib.repo.LeaveRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 10/12/2017.
 */
@Service
public class LeaveRecordService {

    @Autowired
    private LeaveRecordRepo leaveRecordRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<LeaveRecord> getAll() throws Exception {
        return leaveRecordRepo.findAll();
    }

    public List<LeaveRecord> getAllAjax() throws Exception {
        return leaveRecordRepo.findAll();
    }

    public List<LeaveRecord> getAllByEmployee(Long employeeId) throws Exception {
        return leaveRecordRepo.findAllByEmployee(employeeId);
    }

    public LeaveRecord get(Long id) throws Exception {
        return leaveRecordRepo.findOne(id);
    }

    public LeaveRecord getByEmployeeAndId(Long employee, Long id) throws Exception{
        return leaveRecordRepo.findByEmployeeAndId(employee, id);
    }

    public Document getHandOverNoteByEmployeeAndId(Long employee, Long id) throws Exception{
        return leaveRecordRepo.getHandOverNoteByEmployeeIdAndId(employee, id);
    }

    public LeaveRecord save(LeaveRecord leaveRecord) throws Exception {
        return leaveRecordRepo.save(leaveRecord);
    }

    public void delete(List<Long> ids) throws Exception {
        leaveRecordRepo.deleteByIds(ids);
    }

    public void deleteByObject(List<LeaveRecord> leaveRecords) throws Exception {
        List<Long> ids = new ArrayList<>();
        leaveRecords.forEach(leaveRecord -> ids.add(leaveRecord.getId()));
        if (!ids.isEmpty()){
            leaveRecordRepo.deleteByIds(ids);
        }
    }

    public List<LeaveRecord> getAllByEmployeeAndNotType(Long employeeId, String leaveTypeName) throws Exception{
        return leaveRecordRepo.findAllByEmployeeAndNotType(employeeId, leaveTypeName);
    }

    public List<LeaveRecord> getAllByEmployeeAndType(Long employeeId, String leaveTypeName) throws Exception{
        return leaveRecordRepo.findAllByEmployeeAndType(employeeId, leaveTypeName);
    }

    public List<LeaveRecord> getAllByNotType(String leaveTypeName) throws Exception{
        return leaveRecordRepo.findAllByNotType(leaveTypeName);
    }

    public List<LeaveRecord> getAllByType(String leaveTypeName) throws Exception{
        return leaveRecordRepo.findAllByType(leaveTypeName);
    }

    public boolean hasPendingLeaveByEmployee(Long employeeId, Long leaveTypeId) throws Exception{
        int countOfPendingLeaveByEmployee = leaveRecordRepo.countOfPendingLeaveByEmployee(employeeId, ApprovalStatus.PENDING_APPROVAL, leaveTypeId);
        if (countOfPendingLeaveByEmployee > 0){
            return true;
        }
        return false;
    }

    public List<LeaveRecord> getAllByDepartment(Long departmentId) throws Exception{
        return leaveRecordRepo.findAllByDepartment(departmentId);
    }

    public List<LeaveRecord> getAllByDepartmentAndLeaveType(Long departmentId, String leaveTypeName) throws Exception{
        return leaveRecordRepo.findAllByDepartmentAAndLeaveType(departmentId, leaveTypeName);
    }

    public List<LeaveRecord> getAllByDepartmentAndNotLeaveType(Long departmentId, String leaveTypeName) throws Exception{
        return leaveRecordRepo.findAllByDepartmentAAndNotLeaveType(departmentId, leaveTypeName);
    }

    public List<Employee> getEmployeesOnleaveForToday() throws Exception{
        return employeeRepo.getEmployeesOnleaveForToday();
    }

    public List<LeaveRecord> getLeaveApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return leaveRecordRepo.getLeaveApprovals(employeeId, approvalStatus.name());
    }

    public List<LeaveRecord> getLeaveApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return leaveRecordRepo.getLeaveApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }

    public List<LeaveRecord> getEmployeeOnLeave() throws Exception{
        return leaveRecordRepo.getEmployeesOnLeave();
    }

    public List<LeaveRecord> getEmployeesOnLeaveInDepartments(List<Long> departmentIds) throws Exception{
        return leaveRecordRepo.getEmployeesOnLeaveInDepartments(departmentIds);
    }

    public List<LeaveRecord> getEmployeeApprovedLeaveDays(Long employeeId) throws Exception{
        return leaveRecordRepo.getEmployeeApprovedLeaves(employeeId);
    }

}

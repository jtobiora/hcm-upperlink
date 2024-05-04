package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.LeaveStandard;
import com.upl.nibss.hcmlib.repo.LeaveStandardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 08/12/2017.
 */
@Service
public class LeaveStandardService {

    @Autowired
    private LeaveStandardRepo leaveStandardRepo;

    //getAll
    public List<LeaveStandard> getAll() throws Exception{
        return leaveStandardRepo.findAll(new Sort(Sort.Direction.DESC,"gradeLevel","leaveType"));
    }

    //getAllActivated
    public List<LeaveStandard> getAllActivated() throws Exception {
        return leaveStandardRepo.findAllAndActivated();
    }

    //getByleaveType
    public List<LeaveStandard> getAllByLeaveType(Long leaveType) throws Exception{
        return leaveStandardRepo.findAllByType(leaveType);
    }

    //getByNotleaveType
    public List<LeaveStandard> getAllByNotLeaveType(Long leaveType) throws Exception{
        return leaveStandardRepo.findAllByNotType(leaveType);
    }

    //getOne
    public LeaveStandard get(Long id) throws Exception{
        return leaveStandardRepo.findOne(id);
    }

    //getByleaveTypeAndActivated
    public List<LeaveStandard> getAllByLeaveTypeAndActivate(Long leaveType) throws Exception{
        return leaveStandardRepo.findAllByTypeAndActivated(leaveType);
    }

    //getByLeaveTypeAndGradeLevelAndActivate
    public List<LeaveStandard> getAllByLeaveTypeAndGradeLevelAndActivated(Long leaveType,Long gradeLevelId) throws Exception{
        return leaveStandardRepo.findByTypeAndGradeLevelAndActivated(leaveType,gradeLevelId);
    }

    //leaveTypeAndGradeLevelAlreadyExist
    public boolean leaveTypeAndGradeLevelAlreadyExist(Long leaveType,Long gradeLevelId) throws Exception{
        Integer integer = leaveStandardRepo.countOfSimilarLeaveTypeAndGradeLevel(leaveType, gradeLevelId);
        if (integer != null && integer.intValue() > 0){
            return true;
        }
        return false;
    }

    //leaveTypeAndGradeLevelAlreadyExistNotId
    public boolean leaveTypeAndGradeLevelAlreadyExistNotId(Long leaveType,Long gradeLevelId,Long id) throws Exception{
        Integer integer = leaveStandardRepo.countOfSimilarLeaveTypeAndGradeLevelNotId(leaveType, gradeLevelId, id);
        if (integer != null && integer.intValue() > 0){
            return true;
        }
        return false;
    }

    //toggle
    public boolean toggle(Long id) throws Exception{
        int affectedRows = leaveStandardRepo.toggle(id);
        if (affectedRows > 0){
            return true;
        }

        return false;
    }

    //save
    public LeaveStandard save(LeaveStandard leaveStandard) throws Exception{
        return leaveStandardRepo.save(leaveStandard);
    }

    public int countOfLeaveDaysPerGradeLevelAndLeaveType(Long gradelevelId, Long leaveType) throws Exception{
        Integer count = leaveStandardRepo.countOfLeaveDaysPerGradeLevelAndLeaveType(gradelevelId, leaveType);
        if (count == null){
            return 0;
        }
        return count;
    }

    public int countOfLeaveDaysPerLeaveType(Long leaveType) throws Exception{
        Integer countOfLeaveDaysPerLeaveType = leaveStandardRepo.countOfLeaveDaysPerLeaveType(leaveType);
        if (countOfLeaveDaysPerLeaveType == null){
            return 0;
        }
        return countOfLeaveDaysPerLeaveType;
    }

    public List<LeaveStandard> getEmployeeLeaveDaysPerType(Long employeeId) throws Exception{
        return leaveStandardRepo.getLeaveStandardByEmployeeId(employeeId);
    }

}

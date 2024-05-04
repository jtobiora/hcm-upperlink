package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.LeaveType;
import com.upl.nibss.hcmlib.repo.LeaveTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stanlee on 16/01/2018.
 */
@Service
public class LeaveTypeService {

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;

    //getALL
    public List<LeaveType> getAll() throws Exception {
        return leaveTypeRepo.findAll();
    }

    //getAllActivated
    public List<LeaveType> getAllActivated() throws Exception{
        return leaveTypeRepo.getAllActivated();
    }

    //getOne
    public LeaveType get(Long id) throws Exception{
        return leaveTypeRepo.findOne(id);
    }

    //getOneActivated
    public LeaveType getActivated(Long id) throws Exception{
        return leaveTypeRepo.getActivated(id);
    }

    //save
    public LeaveType save(LeaveType leaveType) throws Exception{
        leaveType.setName(leaveType.getName().toUpperCase());//Capitalise the names
        return leaveTypeRepo.save(leaveType);
    }

    //toggle
    public boolean toggle(Long id) throws Exception{
        int count = leaveTypeRepo.toggle(id);
        return (count > 0);
    }

    public LeaveType getByName(String name) throws Exception{
        return leaveTypeRepo.getByName(name);
    }
}

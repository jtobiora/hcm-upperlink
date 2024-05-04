package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.TrainingBookingStatus;
import com.upl.nibss.hcmlib.model.TrainingRecord;
import com.upl.nibss.hcmlib.model.TrainingRecordHistory;
import com.upl.nibss.hcmlib.repo.TrainingRecordHistoryRepo;
import com.upl.nibss.hcmlib.repo.TrainingRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 11/12/2017.
 */
@Service
public class TrainingRecordHistoryService {

    @Autowired
    private TrainingRecordHistoryRepo trainingRecordHistoryRepo;

    public List<TrainingRecordHistory> getAllTrainings() throws Exception{
        return trainingRecordHistoryRepo.findAll();
    }

    //getALLByStatus
    public List<TrainingRecordHistory> getAllByStatus(TrainingBookingStatus status) throws Exception{
        return trainingRecordHistoryRepo.getAllByStatus(status);
    }

    //getALLByNotStatus
    public List<TrainingRecordHistory> getAllNotStatus(TrainingBookingStatus status) throws Exception{
        return trainingRecordHistoryRepo.getAllByNotStatus(status);
    }

    //getAllByDepartment
    public List<TrainingRecordHistory> getAllByDepartment(Long departmentId) throws Exception{
        return trainingRecordHistoryRepo.getAllByDepartment(departmentId);
    }

    //getAllBySupervisor
    public List<TrainingRecordHistory> getAllBySupervisor(Long supervisorId) throws Exception{
        return trainingRecordHistoryRepo.getAllBySupervisor(supervisorId);
    }

    //save
    public TrainingRecordHistory save(TrainingRecordHistory trainingRecordHistory) throws Exception{
        return trainingRecordHistoryRepo.save(trainingRecordHistory);
    }

    //save
    public List<TrainingRecordHistory> save(List<TrainingRecordHistory> trainingRecordHistory) throws Exception{
        return trainingRecordHistoryRepo.save(trainingRecordHistory);
    }

    //get
    public TrainingRecordHistory get(Long id) throws Exception{
        return trainingRecordHistoryRepo.findOne(id);
    }

    public List<TrainingRecordHistory> getAllByYear(Long year) throws Exception{
        return trainingRecordHistoryRepo.getAllByYear(year);
    }

    public TrainingRecordHistory getAllByYearAndId(Long year, Long id) throws Exception{
        return trainingRecordHistoryRepo.getAllByYearAndId(year, id);
    }

    public List<TrainingRecordHistory> getAllByYearAndSupervisorId(Long year, Long supervisorId) throws Exception{
        return trainingRecordHistoryRepo.getAllByYearAndSupervisorId(year, supervisorId);
    }

    public List<TrainingRecordHistory> getAllByYearAndEmployeeId(Long year, Long employeeId) throws Exception{
        return trainingRecordHistoryRepo.getAllByYearAndEmployeeId(year, employeeId);
    }

    public List<TrainingRecordHistory> getAllByYearAndDepartmentId(Long year, Long departmentId) throws Exception{
        return trainingRecordHistoryRepo.getAllByYearAndDepartmentId(year, departmentId);
    }
}

package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.dto.report.TrainingRecordDTO;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.enums.TrainingBookingStatus;
import com.upl.nibss.hcmlib.model.TrainingRecord;
import com.upl.nibss.hcmlib.repo.TrainingRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by toyin.oladele on 11/12/2017.
 */
@Service
public class TrainingRecordService {

    @Autowired
    private TrainingRecordRepo trainingRecordRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TrainingRecord> getAllTrainings() throws Exception{
        return trainingRecordRepo.findAll();
    }

    //getALLByStatus
    public List<TrainingRecord> getAllByStatus(TrainingBookingStatus status) throws Exception{
        return trainingRecordRepo.getAllByStatus(status);
    }

    //getALLByNotStatus
    public List<TrainingRecord> getAllNotStatus(TrainingBookingStatus status) throws Exception{
        return trainingRecordRepo.getAllByNotStatus(status);
    }

    //getAllByDepartment
    public List<TrainingRecord> getAllByDepartment(Long departmentId) throws Exception{
        return trainingRecordRepo.getAllByDepartment(departmentId);
    }

    //getAllBySupervisor
    public List<TrainingRecord> getAllBySupervisor(Long supervisorId) throws Exception{
        return trainingRecordRepo.getAllBySupervisor(supervisorId);
    }

    public List<TrainingRecord> getAllByEmployee(Long employeeId) throws Exception{
        return trainingRecordRepo.getAllByEmployee(employeeId);
    }

    //save
    public TrainingRecord save(TrainingRecord trainingRecord) throws Exception{
        return trainingRecordRepo.save(trainingRecord);
    }

    //save
    public List<TrainingRecord> save(List<TrainingRecord> trainingRecord) throws Exception{
        return trainingRecordRepo.save(trainingRecord);
    }

    //get
    public TrainingRecord get(Long id) throws Exception{
        return trainingRecordRepo.findOne(id);
    }

    public void deleteByObject(List<TrainingRecord> trainingRecords){

        List<Long> ids = new ArrayList<>();
        trainingRecords.forEach(trainingRecord -> ids.add(trainingRecord.getId()));
        if (!ids.isEmpty()){
            trainingRecordRepo.deleteByIds(ids);
        }
    }

    public List<TrainingRecord> findAllByStartDay(Date startDate) throws Exception{
        return trainingRecordRepo.findAllByStartDayAndBooked(startDate);
    }

    //Approved Trainings
    public List<TrainingRecord> findAllWithinDay(Date day) throws Exception{
        return trainingRecordRepo.findAllWithinDay(day);
    }

    //Approved Trainings
    public List<TrainingRecord> findAllUsedTrainingToUpdated(Date endDate) throws Exception{
        return trainingRecordRepo.findAllUsedTrainingToUpdated(endDate);
    }

    public List<TrainingRecord> findAllUnapprovedBeforeDay(Date date) throws Exception{
        return trainingRecordRepo.findAllUnapprovedBeforeDay(date);
    }

    public List<TrainingRecord> getTrainingApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return trainingRecordRepo.getTrainingApprovals(employeeId, approvalStatus.name());
    }

    public List<TrainingRecord> getTrainingApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return trainingRecordRepo.getTrainingApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }

//    public List<TrainingRecordDTO> getReportDTO(){
//        return jdbcTemplate.query("select t.id, e.email, e.first_name, e.last_name, e.middle_name, d.name as departmentName, t.training_name, t.training_description, t.training_start_day, t.training_end_day, t.status, t.approval_status, t.approval_date,t.reason from training_records t, employees e, departments d WHERE t.supervisor_id = e.id and t.department_id = d.id UNION ALL " +
//                "SELECT th.id, e.email, e.first_name, e.last_name, e.middle_name, d.name as departmentName, th.training_name, th.training_description, th.training_start_day, th.training_end_day, th.status, th.approval_status, th.approval_date,th.reason from training_record_history th, employees e, departments d WHERE th.supervisor_id = e.id and th.department_id = d.id ", new BeanPropertyRowMapper<>(TrainingRecordDTO.class));
//    }

    public List<TrainingRecordDTO> getReportDTO(){
        return jdbcTemplate.query("select t.id, e.email, e.first_name, e.last_name, e.middle_name, d.name as departmentName, t.training_name, t.training_description, t.training_start_day, t.training_end_day, t.status, t.approval_status, t.approval_date,t.reason from training_records t, employees e, departments d WHERE t.supervisor_id = e.id and t.department_id = d.id", new BeanPropertyRowMapper<>(TrainingRecordDTO.class));
    }
}

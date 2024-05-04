package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.RotationRecord;
import com.upl.nibss.hcmlib.repo.RotationRecordRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RotationRecordService {
    private static final Logger logger = LoggerFactory.getLogger(RotationRecordService.class);

    @Autowired
    private RotationRecordRepo rotationRecordRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RotationRecord> getAll() throws Exception {
        return rotationRecordRepo.findAll();
    }

    public RotationRecord get(Long id) throws Exception {
        return rotationRecordRepo.findOne(id);
    }

    public RotationRecord getByEmployeeId(Long id) throws Exception {
        return rotationRecordRepo.findRotationRecordByEmployeeId(id);
    }

    public RotationRecord save(RotationRecord rotationRecord) throws Exception {
        return rotationRecordRepo.save(rotationRecord);
    }

    public List<RotationRecord> save(List<RotationRecord> rotationRecords) throws Exception {
        return rotationRecordRepo.save(rotationRecords);
    }

    public void delete(Long id){
        rotationRecordRepo.delete(id);
    }

    public void delete(RotationRecord rotationRecord) throws Exception{
        rotationRecordRepo.delete(rotationRecord);
    }

    public void delete(List<RotationRecord> rotationRecords) throws Exception{
        List<Long> ids = new ArrayList<>();
        rotationRecords.forEach(rotationRecord -> ids.add(rotationRecord.getId()));
        if (!ids.isEmpty()){
            rotationRecordRepo.deleteByIds(ids);
        }
    }

    public RotationRecord findRotationRecordByEmployeeId(Long employeeId){
        return rotationRecordRepo.findRotationRecordByEmployeeId(employeeId);
    }

    public List<RotationRecord> findRotationRecordsByEmployeeId(Long employeeId){
        return rotationRecordRepo.findRotationRecordsByEmployeeId(employeeId);
    }

    public List<RotationRecord> getRotationRecordByEffectiveDateAndAndApprovedApprovalStatus(Date effectiveDate) throws Exception{
        return rotationRecordRepo.getRotationRecordByEffectiveDateAndAndApprovedApprovalStatus(effectiveDate);
    }

    public List<RotationRecord> getRotationRecordByEndDateAndAndApprovedApprovalStatus(Date endDate) throws Exception{
        return rotationRecordRepo.getRotationRecordByEndDateAndAndApprovedApprovalStatus(endDate);
    }

    public List<RotationRecord> findAllByEmployeeIdAndApprovalStatus(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return rotationRecordRepo.findAllByEmployeeIdAndApprovalStatus(employeeId, approvalStatus);
    }

    public RotationRecord findByEmployeeIdAndApprovalStatus(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return rotationRecordRepo.findByEmployeeIdAndApprovalStatus(employeeId, approvalStatus);
    }

    public List<RotationRecord> getRotationApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return rotationRecordRepo.getRotationApprovals(employeeId, approvalStatus.name());
    }

    public List<RotationRecord> getRotationApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return rotationRecordRepo.getRotationApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }

    public List<RotationRecord> getReport() throws Exception{
        return jdbcTemplate.query("select r.* from rotation_record r UNION ALL " +
                "SELECT rh.* from rotation_record_history rh", new BeanPropertyRowMapper<>(RotationRecord.class));
    }

}

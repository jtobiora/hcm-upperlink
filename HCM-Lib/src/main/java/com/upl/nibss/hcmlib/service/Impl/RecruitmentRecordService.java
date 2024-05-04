package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.RecruitmentRecord;
import com.upl.nibss.hcmlib.repo.RecruitmentRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecruitmentRecordService {

    @Autowired
    private RecruitmentRecordRepo recruitmentRecordRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RecruitmentRecord> getAll() throws Exception{
        return recruitmentRecordRepo.findAll();
    }

    public List<RecruitmentRecord> getAllForSupervisorId(Long supervisorId) throws Exception{
        return recruitmentRecordRepo.findAllBySupervisorAndDeleted(supervisorId);
    }

    public RecruitmentRecord getBySupervisorId(Long supervisorId) throws Exception{
        return recruitmentRecordRepo.findBySupervisorAndDeleted(supervisorId);
    }

    public RecruitmentRecord getBySupervisorIdAndId(Long id, Long supervisorId) throws Exception{
        return recruitmentRecordRepo.findByIdAndSupervisorAndDeleted(id, supervisorId);
    }

    public RecruitmentRecord get(Long id) throws Exception{
        return recruitmentRecordRepo.findOne(id);
    }

    public RecruitmentRecord save(RecruitmentRecord recruitmentRecord) throws Exception{
        return recruitmentRecordRepo.save(recruitmentRecord);
    }

    public void delete(List<RecruitmentRecord> recruitmentRecords) throws Exception{

        List<Long> ids = new ArrayList<>();
        recruitmentRecords.forEach(recruitmentRecord -> ids.add(recruitmentRecord.getId()));
        if (!ids.isEmpty()){
            recruitmentRecordRepo.deleteByIds(ids);
        }
    }

    public RecruitmentRecord pendingRecruitmentRequest(Long supervisorId) throws Exception{
        return recruitmentRecordRepo.findPendingBySupervisorId(supervisorId);
    }

    public List<RecruitmentRecord> getRecruitmentApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return recruitmentRecordRepo.getRecruitmentApprovals(employeeId, approvalStatus.name());
    }

    public List<RecruitmentRecord> getRecruitmentApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return recruitmentRecordRepo.getRecruitmentApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }

    public List<RecruitmentRecord> getRecruitmentReport() throws Exception{
        return jdbcTemplate.query("select r.* from recruitment_record r UNION ALL " +
            "SELECT rh.* from recruitment_record_history rh", new BeanPropertyRowMapper<>(RecruitmentRecord.class));
    }

}

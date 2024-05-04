package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.RecruitmentRecordHistory;
import com.upl.nibss.hcmlib.repo.RecruitmentRecordHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 26/11/2017.
 */
@Service
public class RecruitmentRecordHistoryService {

    @Autowired
    private RecruitmentRecordHistoryRepo recruitmentRecordHistoryRepo;

    public List<RecruitmentRecordHistory> getAll() throws Exception{
        return recruitmentRecordHistoryRepo.findAll();
    }

    public List<RecruitmentRecordHistory> getAllBySupervisor(Long supervisorId) throws Exception{
        return recruitmentRecordHistoryRepo.getAllBySupervisor(supervisorId);
    }

    public RecruitmentRecordHistory get(Long id) throws Exception{
        return recruitmentRecordHistoryRepo.findOne(id);
    }

    public RecruitmentRecordHistory getBySupervisorIdAndId(Long supervisorId, Long id) throws Exception{
        return recruitmentRecordHistoryRepo.getRecruitmentRecordHistoryBySupervisorAndId(supervisorId,id);
    }

    public RecruitmentRecordHistory save(RecruitmentRecordHistory recruitmentRecordHistory) throws Exception{
        return recruitmentRecordHistoryRepo.save(recruitmentRecordHistory);
    }

    public List<RecruitmentRecordHistory> save(List<RecruitmentRecordHistory> recruitmentRecordHistories) throws Exception{
        return recruitmentRecordHistoryRepo.save(recruitmentRecordHistories);
    }

}

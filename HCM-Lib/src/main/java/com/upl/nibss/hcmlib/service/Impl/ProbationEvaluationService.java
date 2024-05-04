package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.ProbationEvaluations;
import com.upl.nibss.hcmlib.repo.ProbationEvaluationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 07/12/2017.
 */
@Service
public class ProbationEvaluationService {

    @Autowired
    private ProbationEvaluationsRepo probationEvaluationsRepo;

    public List<ProbationEvaluations> findAll() throws Exception{
        return probationEvaluationsRepo.findAll();
    }

    public ProbationEvaluations find(Long id) throws Exception{
        return probationEvaluationsRepo.findOne(id);
    }

    public ProbationEvaluations findByEmployee(Long employeeId) throws Exception{
        return probationEvaluationsRepo.findByEmployee(employeeId);
    }

    public ProbationEvaluations findByEmployeeAndSupervisor(Long employeeId, Long supervisorId) throws Exception{
        return probationEvaluationsRepo.findByEmployeeAAndSupervisor(employeeId,supervisorId);
    }

    public ProbationEvaluations save(ProbationEvaluations probationEvaluations) throws Exception{
        return probationEvaluationsRepo.save(probationEvaluations);
    }

    public List<ProbationEvaluations> getProbationEvaluationApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return probationEvaluationsRepo.getProbationEvaluationApprovals(employeeId, approvalStatus.name());
    }

    public List<ProbationEvaluations> getProbationEvaluationApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return probationEvaluationsRepo.getProbationEvaluationApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }

}

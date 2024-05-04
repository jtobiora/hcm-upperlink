package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.Exit;
import com.upl.nibss.hcmlib.repo.ExitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExitService {
    @Autowired
    private ExitRepo exitRepo;

    public Exit getExit(Long id){
        return exitRepo.findOne(id);
    }

    public List<Exit> getExits(){
        return exitRepo.findAll();
    }

    public Exit getExitByEmployee(Long employeeId){
        return exitRepo.findExitByEmployeeId(employeeId);
    }

    public Exit save(Exit exit){
        return exitRepo.save(exit);
    }

    public void deleteExit(Long id){
        exitRepo.delete(id);
    }

    public void deleteExits(List<Long> ids){
        exitRepo.deleteByIds(ids);
    }

    public List<Exit> getExitApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return exitRepo.getExitApprovals(employeeId, approvalStatus.name());
    }

    public List<Exit> getExitApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return exitRepo.getExitApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }
}

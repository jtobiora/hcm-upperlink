package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.ExitReason;
import com.upl.nibss.hcmlib.repo.ExitReasonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExitReasonService {
    @Autowired
    private ExitReasonRepo exitReasonRepo;

    public ExitReason getExitReason(Long id){
        return exitReasonRepo.findOne(id);
    }

    public List<ExitReason> getExitReasons(){
        return exitReasonRepo.findAll();
    }

    public ExitReason save(ExitReason exitReason){
        return exitReasonRepo.save(exitReason);
    }

    public void deleteExitReason(Long id){
        exitReasonRepo.delete(id);
    }

    public void getExitReason(List<Long> ids){
        exitReasonRepo.deleteByIds(ids);
    }
}

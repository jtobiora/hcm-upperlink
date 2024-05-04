package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.ExitResponse;
import com.upl.nibss.hcmlib.repo.ExitResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExitResponseService {
    @Autowired
    private ExitResponseRepo exitResponseRepo;

    public ExitResponse getExitResponse(Long id){
        return exitResponseRepo.findOne(id);
    }

    public List<ExitResponse> getExitResponses(){
        return exitResponseRepo.findAll();
    }

    public List<ExitResponse> getResponseFromExitId(Long exitId){
        return exitResponseRepo.findExitResponsesByExitId(exitId);
    }

    public ExitResponse findExitResponsesByExitIdAndExitReasonId(Long exitId, Long exitReasonId){
        return exitResponseRepo.findExitResponsesByExitIdAndExitReasonId(exitId, exitReasonId);
    }

    public ExitResponse save(ExitResponse exitResponse){
        return exitResponseRepo.save(exitResponse);
    }

    public List<ExitResponse> save(Iterable<ExitResponse> exitResponses){
        return exitResponseRepo.save(exitResponses);
    }

    public void deleteExitResponse(Long id){
        exitResponseRepo.delete(id);
    }

    public void deleteExitResponse(List<Long> ids){
        exitResponseRepo.deleteByIds(ids);
    }
}

package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.TrainingJustification;
import com.upl.nibss.hcmlib.repo.TrainingJustificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by toyin.oladele on 12/12/2017.
 */
@Service
public class TrainingJustificationService {

    @Autowired
    private TrainingJustificationRepo trainingJustificationRepo;

    public TrainingJustification get(Long id) throws Exception{
        return trainingJustificationRepo.getOne(id);
    }

    public TrainingJustification getByTrainingRecord(Long trainingRecordId) throws Exception{
        return trainingJustificationRepo.getByTrainingRecord(trainingRecordId);
    }

    public TrainingJustification save(TrainingJustification trainingJustification) throws Exception{
        return trainingJustificationRepo.save(trainingJustification);
    }
}

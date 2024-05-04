package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.model.TrainingMaterials;
import com.upl.nibss.hcmlib.repo.TrainingMaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 15/12/2017.
 */
@Service
public class TrainingMaterialService {

    @Autowired
    private TrainingMaterialRepo trainingMaterialRepo;

    public List<TrainingMaterials> getAll() throws Exception{
        return trainingMaterialRepo.findAll();
    }

    public TrainingMaterials get(Long trainingMaterialId) throws Exception{
        return trainingMaterialRepo.getOne(trainingMaterialId);
    }

    public List<TrainingMaterials> getByTrainingRecord(Long trainingRecordId) throws Exception{
        return trainingMaterialRepo.getByTrainingRecord(trainingRecordId);
    }

    public TrainingMaterials save(TrainingMaterials trainingMaterials) throws Exception{
        return trainingMaterialRepo.save(trainingMaterials);
    }

    public List<Document> getDocument(Long id) throws Exception{
        TrainingMaterials trainingMaterials = trainingMaterialRepo.findOne(id);
        if (trainingMaterials != null){
            return trainingMaterials.getMaterials();
        }

        return new ArrayList<>();
    }
}

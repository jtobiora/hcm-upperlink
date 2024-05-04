package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.TrainingMaterialsPerEmployee;
import com.upl.nibss.hcmlib.repo.TrainingMaterialsPerEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 16/12/2017.
 */
@Service
public class TrainingMaterialsPerEmployeeService {

    @Autowired
    private TrainingMaterialsPerEmployeeRepo trainingMaterialsPerEmployeeRepo;

    public List<TrainingMaterialsPerEmployee> getAll() throws Exception {
        return trainingMaterialsPerEmployeeRepo.findAll();
    }

    public List<TrainingMaterialsPerEmployee> getByTrainingRecord(Long trainingRecordId) throws Exception{
        return trainingMaterialsPerEmployeeRepo.getByTrainingRecord(trainingRecordId);
    }

    public TrainingMaterialsPerEmployee getByTrainingRecordAndEmployee(Long trainingRecordId, Long employeeId) throws Exception{
        return trainingMaterialsPerEmployeeRepo.getByTrainingRecordAndEmployee(trainingRecordId,employeeId);
    }

    public TrainingMaterialsPerEmployee getId(Long id) throws Exception{
        return trainingMaterialsPerEmployeeRepo.findOne(id);
    }

    public TrainingMaterialsPerEmployee save(TrainingMaterialsPerEmployee trainingMaterialsPerEmployee) throws Exception{
        return trainingMaterialsPerEmployeeRepo.save(trainingMaterialsPerEmployee);
    }

    public List<Document> getDocument(Long id) throws Exception{
        TrainingMaterialsPerEmployee trainingMaterialsPerEmployee = trainingMaterialsPerEmployeeRepo.findOne(id);
        if (trainingMaterialsPerEmployee != null){
            return trainingMaterialsPerEmployee.getMaterials();
        }

        return new ArrayList<>();
    }

    public List<TrainingMaterialsPerEmployee> getTrainingMaterialApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return trainingMaterialsPerEmployeeRepo.getTrainingMaterialApprovals(employeeId, approvalStatus.name());
    }

    public List<TrainingMaterialsPerEmployee> getTrainingMaterialApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return trainingMaterialsPerEmployeeRepo.getTrainingMaterialApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }
}

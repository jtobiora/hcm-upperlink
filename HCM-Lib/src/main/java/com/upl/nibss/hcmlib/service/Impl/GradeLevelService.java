package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.GradeLevel;
import com.upl.nibss.hcmlib.repo.GradeLevelRepo;
import com.upl.nibss.hcmlib.service.interfaces.IGradeLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 11/11/2017.
 */
@Service
public class GradeLevelService implements IGradeLevelService{

    private static final Logger logger = LoggerFactory.getLogger(GradeLevelService.class);

    @Autowired
    private GradeLevelRepo gradeLevelRepo;

    @Override
    public List<GradeLevel> getAll() throws Exception{
        return gradeLevelRepo.findAll();
    }

    @Override
    public List<GradeLevel> getAllActivated() throws Exception {
        return gradeLevelRepo.getAllActivated();
    }

    @Override
    public List<String> getAllGradeLevelNames() throws Exception {
        return gradeLevelRepo.getAllGradeLevelNames();
    }

    @Override
    public GradeLevel get(Long id) throws Exception{
        return gradeLevelRepo.findOne(id);
    }

    @Override
    public GradeLevel save(GradeLevel gradeLevel) throws Exception{
        return gradeLevelRepo.save(gradeLevel);
    }

    @Override
    public boolean doesGradeExists(Integer grade, String gradeName) throws Exception {

        Integer integer = gradeLevelRepo.confirmIfExists(grade, gradeName);
        if (integer != null && integer.intValue() < 1){
            return false;
        }
        return true;
    }

    @Override
    public boolean doesGradeExistsExceptId(Integer grade, String gradeName, Long id) throws Exception {

        Integer integer = gradeLevelRepo.confirmIfExistsExceptId(grade, gradeName, id);
        if (integer != null && integer.intValue() < 1){
            return false;
        }
        return true;
    }

    public Integer getNextGradeLevel(){
        Integer maxGradeLevel = gradeLevelRepo.getMaxGradeLevel();
        return ++maxGradeLevel;
    }

    @Override
    public List<GradeLevel> getAll(List<Long> gradelevelId) throws Exception {
        return gradeLevelRepo.findAllByids(gradelevelId);
    }
}

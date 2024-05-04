package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.GradeLevel;

import java.util.List;

/**
 * Created by toyin.oladele on 11/11/2017.
 */
public interface IGradeLevelService {

    List<GradeLevel> getAll() throws Exception;

    List<GradeLevel> getAllActivated() throws Exception;

    List<String> getAllGradeLevelNames() throws Exception;

    GradeLevel get(Long id) throws Exception;

    GradeLevel save(GradeLevel gradeLevel) throws Exception;

    boolean doesGradeExists(Integer grade, String gradeName) throws Exception;

    boolean doesGradeExistsExceptId(Integer grade, String gradeName, Long id) throws Exception;

    List<GradeLevel> getAll(List<Long> gradelevelId) throws Exception;

    Integer getNextGradeLevel();

}

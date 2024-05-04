package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.GradeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 11/11/2017.
 */
@Repository
@Transactional
public interface GradeLevelRepo extends JpaRepository<GradeLevel, Long> {

    @Query("select count(g.gradeId) from GradeLevel g where g.grade = :grade or g.gradeName = :gradeName")
    Integer confirmIfExists(@Param("grade") Integer grade, @Param("gradeName") String gradeName);

    @Query("select count(g.gradeId) from GradeLevel g where (g.grade = :grade or g.gradeName = :gradeName) and g.gradeId <> :id")
    Integer confirmIfExistsExceptId(@Param("grade") Integer grade, @Param("gradeName") String gradeName, @Param("id") Long id);

    @Query("select g from GradeLevel g where g.gradeId in :ids")
    List<GradeLevel> findAllByids(@Param("ids") List<Long> ids);

    @Query("select g.gradeName from GradeLevel g where g.deleted = false ")
    List<String> getAllGradeLevelNames();

    @Query("select g from GradeLevel g where g.deleted = false ")
    List<GradeLevel> getAllActivated();

    @Query("select max(g.grade) from GradeLevel g")
    Integer getMaxGradeLevel();
}

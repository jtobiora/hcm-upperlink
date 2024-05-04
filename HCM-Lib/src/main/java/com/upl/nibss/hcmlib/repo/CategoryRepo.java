package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Category;
import com.upl.nibss.hcmlib.model.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 02/11/2017.
 */
@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.deleted = false order by c.hierarchy desc")
    List<Category> findAllCategory();

    @Query("select c from Category c where c.deleted = false and c.activated = true order by c.hierarchy desc")
    List<Category> findAllActivated();

    @Query("select c.deptType from Category c where c.deleted = false and c.activated = true order by c.hierarchy desc")
    List<String> getAllDepartmentTypeNames();

    @Query("select c from Category c where c.id = :id and c.deleted = false ")
    Category findCategory(@Param("id") Long id);

    @Query("select max(c.hierarchy) from Category c where c.deleted = false")
    int findMaxPriority();

    @Query("select c.hierarchy from Category c where c.hierarchy = :hierarchy")
    String findPriority(@Param("hierarchy") int hierarchy);

    @Query("select c.hierarchy from Category c where c.hierarchy = :hierarchy and c.id <> :id")
    String findPriorityNotThisId(@Param("hierarchy") int hierarchy, @Param("id") Long id);

}

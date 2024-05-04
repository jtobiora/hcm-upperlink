package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 10/11/2017.
 */
@Transactional
@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
//
    @Query("select d from Department d")
    List<Department> findAllAjaxDepartment();

    @Query("select d from Department d where d.deleted = false")
    List<Department> findAllActivated();

    @Query("select d.deptName from Department d where d.deleted = false")
    List<String> getAllDepartmentNames();

    @Query("select d.parentDepartment from Department d where d.id = :id")
    Long findParentDept(@Param("id") Long id);

    @Query("select d.maxNumberOfPersonOnLeavePerDay from Department d where d.id = :deptId")
    int numberOfLeaveDaysPerDepartment(@Param("deptId") Long deptId);

    @Query("select d from Department d where d.locationId.id = :locationId and d.deleted = false")
    List<Department> getAllByLocation(@Param("locationId") Long locationId);
}

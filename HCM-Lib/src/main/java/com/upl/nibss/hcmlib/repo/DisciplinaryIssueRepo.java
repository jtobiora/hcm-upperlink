package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.DisciplinaryIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 08/12/2017.
 */
@Transactional
@Repository
public interface DisciplinaryIssueRepo extends JpaRepository<DisciplinaryIssue, Long> {

    @Query("select d from DisciplinaryIssue d where d.employee.id = :employee_id and d.deleted = false ")
    List<DisciplinaryIssue> findByEmployee(@Param("employee_id") Long employeeId);

    @Query("select d from DisciplinaryIssue d where d.employee.id in :employee_ids and d.deleted = false ")
    List<DisciplinaryIssue> findByEmployees(@Param("employee_ids") List<Long> employeeIds);

    @Query("select d from DisciplinaryIssue d where d.deleted = false ")
    List<DisciplinaryIssue> findAll();

    @Query("select d from DisciplinaryIssue d where d.id = :id and d.deleted = false")
    DisciplinaryIssue findOne(@Param("id") Long id);

    @Query("select d from DisciplinaryIssue d where d.id = :id and d.employee.id = :employee_id and d.deleted = false")
    DisciplinaryIssue findOneByEmployee(@Param("id") Long id, @Param("employee_id") Long employeeId);
}

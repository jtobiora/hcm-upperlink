package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.LoanRecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 24/11/2017.
 */
@Transactional
@Repository
public interface LoanRecordHistoryRepo extends JpaRepository<LoanRecordHistory, Long> {

    @Query("select l from LoanRecordHistory l where l.employee.id = :employeeId")
    List<LoanRecordHistory> findAllByEmployee(@Param("employeeId") Long employeeId);

    @Query("select l from LoanRecordHistory l where l.employee.id = :employeeId and l.id = :id")
    List<LoanRecordHistory> findAllByEmployeeAndId(@Param("employeeId") Long employeeId, @Param("id") Long id);
}

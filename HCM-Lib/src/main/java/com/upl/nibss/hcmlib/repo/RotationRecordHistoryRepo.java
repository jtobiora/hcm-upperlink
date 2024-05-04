package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.RotationRecord;
import com.upl.nibss.hcmlib.model.RotationRecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RotationRecordHistoryRepo extends JpaRepository<RotationRecordHistory, Long> {
    @Query("select r from RotationRecordHistory r where r.employee.id = :id")
    RotationRecordHistory findRotationRecordHistoryByEmployeeId(@Param("id") Long id);

    @Query("select r from RotationRecordHistory r where r.employee.id = :id")
    List<RotationRecordHistory> findRotationRecordHistoriesByEmployeeId(@Param("id") Long id);
}

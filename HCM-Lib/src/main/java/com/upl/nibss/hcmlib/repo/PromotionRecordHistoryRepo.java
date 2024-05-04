package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.PromotionRecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 23/11/2017.
 */
@Transactional
@Repository
public interface PromotionRecordHistoryRepo extends JpaRepository<PromotionRecordHistory, Long> {

    @Query("select p from PromotionRecordHistory p where p.employee.id = :employeeId")
    List<PromotionRecordHistory> getAllByEmployee(@Param("employeeId") Long employeeId);

    @Query("select p from PromotionRecordHistory p where p.employee.id = :employeeId and p.id = :id")
    PromotionRecordHistory getPromotionRecordHistoryByEmployeeAndId(@Param("employeeId") Long employeeId, @Param("id") Long id);

}

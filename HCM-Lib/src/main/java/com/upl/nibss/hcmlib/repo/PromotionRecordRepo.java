package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.PromotionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface PromotionRecordRepo extends JpaRepository<PromotionRecord, Long>{

    @Query("select p from PromotionRecord p where p.employee.id = :employeeId and deleted = false ")
    List<PromotionRecord> findAllByEmployeeAndDeleted(@Param("employeeId") Long employeeId);

    @Modifying
    @Query("delete from PromotionRecord p where p.id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);

    @Query("select p.id from PromotionRecord p")
    List<Long> findAllIds();

    @Query("select p from PromotionRecord p where p.effectiveDate = :theDate")
    List<PromotionRecord> getPromotionRecordsByEffectiveDate(@Param("theDate") Date date);
}

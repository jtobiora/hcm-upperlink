package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.ExitResponse;
import com.upl.nibss.hcmlib.model.RotationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ExitResponseRepo extends JpaRepository<ExitResponse, Long> {
    @Query("select e from ExitResponse e where e.exit.id = :id and deleted = false")
    List<ExitResponse> findExitResponsesByExitId(@Param("id") Long id);

    @Query("select e from ExitResponse e where e.exit.id = :exitId and e.exitReason.id = :exitReasonId and deleted = false")
    ExitResponse findExitResponsesByExitIdAndExitReasonId(@Param("exitId") Long exitId, @Param("exitReasonId") Long exitReasonId);

    @Modifying
    @Query("delete from ExitResponse e where e.id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);
}

package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.ExitReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ExitReasonRepo extends JpaRepository<ExitReason, Long> {
    @Modifying
    @Query("delete from ExitReason e where e.id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);
}

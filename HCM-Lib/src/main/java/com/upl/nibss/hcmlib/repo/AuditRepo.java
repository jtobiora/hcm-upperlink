package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.model.Audits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface AuditRepo extends JpaRepository<Audits,Long> {
    @Query("select a from Audits a where a.username = :username")
    List<Audits> findByUsername(@Param("username") String username);

    @Query("select a from Audits a where a.createdAt >= :start_date and a.createdAt <= :end_date")
    List<Audits> findByDateRange(@Param(Constants.START_DATE) Date startDate, @Param(Constants.END_DATE) Date endDate);

    @Query("select a from Audits a where a.username = :username and a.createdAt >= :start_date and a.createdAt <= :end_date")
    List<Audits> findByUsernameAndDateRange(@Param("username") String username, @Param(Constants.START_DATE) Date startDate, @Param(Constants.END_DATE) Date endDate);
}

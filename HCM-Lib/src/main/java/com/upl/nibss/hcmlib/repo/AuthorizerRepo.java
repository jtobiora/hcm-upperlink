package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Authorizers;
import com.upl.nibss.hcmlib.model.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Transactional
@Repository
public interface AuthorizerRepo extends JpaRepository<Authorizers, Long> {

//    @Query("select a from Authorizers a where a.l.id = :loanRequestId and a.roles.id in :ids")
//    List<Authorizers> findByLoanRequest(@Param("loanRequestId") Long loanRequestId, @Param("ids") List<Long> ids);

    @Query("select a.roles.jobRole from Authorizers a where a.id = :id")
    JobRole findJobRole(@Param("id") Long id);

}

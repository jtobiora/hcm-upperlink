package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
public interface AllowanceRepo extends JpaRepository<Allowance,Long>{

    @Query("select a from Allowance a where a.id in :ids")
    List<Allowance> findAllByIds(@Param("ids") List<Long> ids);

    @Query("select a from Allowance a where a.id in :ids and a.activated = true ")
    List<Allowance> findAllActivatedByIds(@Param("ids") List<Long> ids);

    @Query("select a from Allowance a where a.deleted = false ")
    List<Allowance> findAllUndeleted();

    @Query("select a from Allowance a where a.activated = true and a.deleted = false ")
    List<Allowance> findAllowanceByActivated();

    @Query("select a from Allowance a where a.name = :allowanceName")
    Allowance getAllowanceByName(@Param("allowanceName") String allowanceName);

    @Query("select a from Allowance a where a.name = :allowanceName and a.id <> :id")
    Allowance getAllowanceByNameAndNotId(@Param("allowanceName") String allowanceName, @Param("id") Long id);

    @Modifying
    @Query("update Allowance a set a.activated = case a.activated when true then false else true end where a.id = :id")
    int toggle(@Param("id") Long id);

}

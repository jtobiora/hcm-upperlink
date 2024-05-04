package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
@Repository
@Transactional
public interface StateRepo extends JpaRepository<State, Long> {

    @Query("select s from State s")
    List<State> getAllAjaxState();

    @Query("select s from State s where s.stateId = :id")
    State getAjaxState(@Param("id") Long id);

}

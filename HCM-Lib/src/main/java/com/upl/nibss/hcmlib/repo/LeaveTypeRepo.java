package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by stanlee on 16/01/2018.
 */
@Transactional
public interface LeaveTypeRepo extends JpaRepository<LeaveType, Long> {

    @Query("select l from LeaveType l where l.activated = true and l.deleted = false ")
    List<LeaveType> getAllActivated();

    @Query("select l from LeaveType l where l.activated = true and l.deleted = false and l.id = :id ")
    LeaveType getActivated(@Param("id") Long id);

    @Modifying
    @Query("UPDATE LeaveType l SET l.activated = CASE l.activated WHEN true THEN false ELSE true END WHERE l.id = :id")
    int toggle(@Param("id") Long id);

    @Query("select l from LeaveType l where l.name = :name and l.activated = true and l.deleted = false")
    LeaveType getByName(@Param("name") String name);
}

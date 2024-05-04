package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 01/11/2017.
 */
@Repository
@Transactional
public interface JobRoleRepo extends JpaRepository<JobRole, Long> {

    @Query("select jbr from JobRole jbr ")
    List<JobRole> findAllJobRoles();

    @Query("select jbr from JobRole jbr where jbr.deleted = false and jbr.activated = true ")
    List<JobRole> getAllActivated();

    @Query("select jbr.jobTitle from JobRole jbr where jbr.deleted = false and jbr.activated = true ")
    List<String> getAllJobRoleTitles();

    @Query("select jbr from JobRole jbr where jbr.jobRoleId = :id")
    JobRole findJobRoles(@Param("id") Long id);

    @Query("select j from JobRole j where j.jobRoleId in :jobRoleIds")
    Set<JobRole> findAllJobRoleEOById(@Param("jobRoleIds") List<Long> jobRoleIds);

}

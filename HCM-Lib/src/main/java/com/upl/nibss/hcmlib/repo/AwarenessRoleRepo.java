package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.AwarenessRole;
import com.upl.nibss.hcmlib.model.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by toyin.oladele on 23/11/2017.
 */
public interface AwarenessRoleRepo extends JpaRepository<AwarenessRole, Long> {

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename")
    List<AwarenessRole> findAllByPortalModuleName(@Param("portalmodulename") PortalModuleName portalmodulename);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.activated = true ")
    List<AwarenessRole> findAllByPortalModuleNameAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.awareRoleId = :id")
    AwarenessRole findByPortalModuleName(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.awareRoleId = :id and a.activated = true ")
    AwarenessRole findByPortalModuleNameAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id);

    @Query("select a.jobRole from AwarenessRole a where a.portalModule.name = :portalmodulename and a.activated = true ")
    List<JobRole> findAllJobRoleByPortalModuleNameAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename);

    @Query("select a.awareRoleId from AwarenessRole a where a.portalModule.name = :portalmodulename and a.activated = true ")
    List<Long> findAllidsByPortalModuleNameAndAlertTypeAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename);

    @Query("select count(a.awareRoleId) from AwarenessRole a where a.portalModule.name = :portalModuleName")
    int countOfSamePortalModuleAndPriority(@Param("portalModuleName") PortalModuleName portalModuleName);

    @Query("select count(a.awareRoleId) from AwarenessRole a where a.portalModule.name = :portalModuleName and a.awareRoleId <> :id")
    int countOfSamePortalModuleAndPriorityNotId(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("id") Long id);

    //Job role
    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId ")
    List<AwarenessRole> findAllByPortalModuleNameAndJobrole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("jobroleId") Long jobroleId);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.activated = true and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId")
    List<AwarenessRole> findAllByPortalModuleNameAndActivatedAndJobrole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("jobroleId") Long jobroleId);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.awareRoleId = :id and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId")
    AwarenessRole findByPortalModuleNameAndJobrole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id, @Param("jobroleId") Long jobroleId);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.awareRoleId = :id and a.activated = true and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId")
    AwarenessRole findByPortalModuleNameAndActivatedAndJobrole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id, @Param("jobroleId") Long jobroleId);

    @Query("select a.jobRole from AwarenessRole a where a.portalModule.name = :portalmodulename and a.activated = true and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId")
    List<JobRole> findAllJobRoleByPortalModuleNameAndActivatedAndJobrole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("jobroleId") Long jobroleId);

    @Query("select a.awareRoleId from AwarenessRole a where a.portalModule.name = :portalmodulename and a.activated = true and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId")
    List<Long> findAllidsByPortalModuleNameAndAlertTypeAndActivatedAndJobrole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("jobroleId") Long jobroleId);

    @Query("select count(a.awareRoleId) from AwarenessRole a where a.portalModule.name = :portalModuleName and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId")
    int countOfSamePortalModuleAndPriorityAndJobrole(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("jobroleId") Long jobroleId);

    @Query("select count(a.awareRoleId) from AwarenessRole a where a.portalModule.name = :portalModuleName and a.awareRoleId <> :id  and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId")
    int countOfSamePortalModuleAndPriorityNotIdAndJobrole(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("id") Long id, @Param("jobroleId") Long jobroleId);


    //Department
    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.departmentTrainingAuthorizer.id = :deptId")
    List<AwarenessRole> findAllByPortalModuleNameAndDept(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("deptId") Long deptId);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.departmentTrainingAuthorizer.id = :deptId and a.activated = true ")
    List<AwarenessRole> findAllByPortalModuleNameAndActivatedAndDept(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("deptId") Long deptId);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.awareRoleId = :id and a.departmentTrainingAuthorizer.id = :deptId")
    AwarenessRole findByPortalModuleNameAndDept(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id, @Param("deptId") Long deptId);

    @Query("select a from AwarenessRole a where a.portalModule.name = :portalmodulename and a.awareRoleId = :id and a.departmentTrainingAuthorizer.id = :deptId and a.activated = true ")
    AwarenessRole findByPortalModuleNameAndActivatedAndDept(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id, @Param("deptId") Long deptId);

    @Query("select a.jobRole from AwarenessRole a where a.portalModule.name = :portalmodulename and a.departmentTrainingAuthorizer.id = :deptId and a.activated = true ")
    List<JobRole> findAllJobRoleByPortalModuleNameAndActivatedAndDept(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("deptId") Long deptId);

    @Query("select a.awareRoleId from AwarenessRole a where a.portalModule.name = :portalmodulename and a.departmentTrainingAuthorizer.id = :deptId and a.activated = true ")
    List<Long> findAllidsByPortalModuleNameAndAlertTypeAndActivatedAndDept(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("deptId") Long deptId);

    @Query("select count(a.awareRoleId) from AwarenessRole a where a.portalModule.name = :portalModuleName and a.departmentTrainingAuthorizer.id = :deptId")
    int countOfSamePortalModuleAndPriorityAndDept(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("deptId") Long deptId);

    @Query("select count(a.awareRoleId) from AwarenessRole a where a.portalModule.name = :portalModuleName and a.awareRoleId <> :id and a.departmentTrainingAuthorizer.id = :deptId")
    int countOfSamePortalModuleAndPriorityNotIdAndDept(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("id") Long id, @Param("deptId") Long deptId);

}

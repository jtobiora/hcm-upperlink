package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.AuthorizationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
@Repository
@Transactional
public interface AuthorizationRoleRepo extends JpaRepository<AuthorizationRole, Long> {

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.deleted = false ")
    List<AuthorizationRole> findAllByPortalModuleName(@Param("portalmodulename") PortalModuleName portalmodulename);

    //new Queries
    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId and a.deleted = false ")
    List<AuthorizationRole> findAllByPortalModuleNameAndJobRole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("jobroleId") Long jobroleId);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.departmentTrainingAuthorizer.id = :deptId and a.deleted = false ")
    List<AuthorizationRole> findAllByPortalModuleNameAndDepartment(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("deptId") Long deptId);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId and a.activated = true and a.deleted = false ")
    List<AuthorizationRole> findAllByPortalModuleNameAndJobRoleAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("jobroleId") Long jobroleId);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.departmentTrainingAuthorizer.id = :deptId and a.activated = true and a.deleted = false ")
    List<AuthorizationRole> findAllByPortalModuleNameAndDepartmentAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("deptId") Long deptId);

    //To get an instance
    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.id = :id and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId and a.deleted = false ")
    AuthorizationRole findByPortalModuleNameAndJobRole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id, @Param("jobroleId") Long jobroleId);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.id = :id  and a.departmentTrainingAuthorizer.id = :deptId and a.deleted = false ")
    AuthorizationRole findByPortalModuleNameAndDepartment(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id, @Param("deptId") Long deptId);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.id = :id  and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId and a.activated = true and a.deleted = false ")
    AuthorizationRole findByPortalModuleNameAndJobRoleAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id, @Param("jobroleId") Long jobroleId);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.id = :id  and a.departmentTrainingAuthorizer.id = :deptId and a.activated = true and a.deleted = false ")
    AuthorizationRole findByPortalModuleNameAndDepartmentAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id, @Param("deptId") Long deptId);
    //


    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.activated = true  and a.deleted = false ")
    List<AuthorizationRole> findAllByPortalModuleNameAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.id = :id  and a.deleted = false ")
    AuthorizationRole findByPortalModuleName(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.id = :id  and a.deleted = false ")
    AuthorizationRole findAjaxByPortalModuleName(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.id = :id and a.activated = true  and a.deleted = false ")
    AuthorizationRole findByPortalModuleNameAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("id") Long id);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.deleted = false ")
    List<AuthorizationRole> findAllByPortalModuleNameAndAlertType(@Param("portalmodulename") PortalModuleName portalmodulename);
//
    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.activated = true  and a.deleted = false ")
    List<AuthorizationRole> findAllByPortalModuleNameAndAlertTypeAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename);

    @Query("select a from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.activated = true  and a.deleted = false ")
    List<AuthorizationRole> findAllAjaxByPortalModuleNameAndAlertTypeAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename);

    @Query("select a.authRoleId from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.activated = true  and a.deleted = false ")
    List<Long> findAllidsByPortalModuleNameAndAlertTypeAndActivated(@Param("portalmodulename") PortalModuleName portalmodulename);

    @Query("select count(a.authRoleId) from AuthorizationRole a where a.portalModule.name = :portalModuleName and a.order_value = :priority  and a.deleted = false ")
    int countOfSamePortalModuleAndPriority(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("priority") int priority);

    @Query("select count(a.authRoleId) from AuthorizationRole a where a.portalModule.name = :portalModuleName and a.order_value = :priority and a.authRoleId <> :id  and a.deleted = false ")
    int countOfSamePortalModuleAndPriorityNotId(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("priority") int priority, @Param("id") Long id);

    //New Query

    @Query("select a.authRoleId from AuthorizationRole a where a.portalModule.name = :portalmodulename and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId  and a.activated = true  and a.deleted = false ")
    List<Long> findAllidsByPortalModuleNameAndAlertTypeAndActivatedAndJobRole(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("jobroleId") Long jobroleId);

    @Query("select count(a.authRoleId) from AuthorizationRole a where a.portalModule.name = :portalModuleName and a.order_value = :priority and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId   and a.deleted = false ")
    int countOfSamePortalModuleAndPriorityAndJobRole(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("priority") int priority, @Param("jobroleId") Long jobroleId);

    @Query("select count(a.authRoleId) from AuthorizationRole a where a.portalModule.name = :portalModuleName and a.order_value = :priority and a.authRoleId <> :id and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId and a.deleted = false ")
    int countOfSamePortalModuleAndPriorityNotIdAndJobRole(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("priority") int priority, @Param("id") Long id, @Param("jobroleId") Long jobroleId);


    @Query("select a.authRoleId from AuthorizationRole a where a.portalModule.name = :portalmodulename  and a.departmentTrainingAuthorizer.id = :deptId and a.activated = true  and a.deleted = false ")
    List<Long> findAllidsByPortalModuleNameAndAlertTypeAndActivatedAndDepartment(@Param("portalmodulename") PortalModuleName portalmodulename, @Param("deptId") Long deptId);

    @Query("select count(a.authRoleId) from AuthorizationRole a where a.portalModule.name = :portalModuleName and a.order_value = :priority  and a.departmentTrainingAuthorizer.id = :deptId  and a.deleted = false ")
    int countOfSamePortalModuleAndPriorityAndDepartment(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("priority") int priority, @Param("deptId") Long deptId);

    @Query("select count(a.authRoleId) from AuthorizationRole a where a.portalModule.name = :portalModuleName and a.order_value = :priority and a.authRoleId <> :id  and a.departmentTrainingAuthorizer.id = :deptId  and a.deleted = false ")
    int countOfSamePortalModuleAndPriorityNotIdAndDepartment(@Param("portalModuleName") PortalModuleName portalModuleName, @Param("priority") int priority, @Param("id") Long id, @Param("deptId") Long deptId);

    //get the authorizers based on orderValue and portalModule
    @Query("select a from AuthorizationRole a where a.order_value >= :orderValue and a.portalModule = :portalModuleName")
    List<AuthorizationRole> getAllByOrder_valueAndPortalModule(@Param("orderValue") int orderValue, @Param("portalModuleName") PortalModuleName portalModuleName);

    @Query("select a from AuthorizationRole a where a.order_value >= :orderValue and a.portalModule = :portalModuleName and a.jobRoleleaveAuthorizer.jobRoleId = :jobroleId")
    List<AuthorizationRole> getAllByOrder_valueAndPortalModuleAndJobRoleleaveAuthorizer(@Param("orderValue") int orderValue, @Param("portalModuleName") PortalModuleName portalModuleName, @Param("jobroleId") Long jobroleId);

    @Query("select a from AuthorizationRole a where a.order_value >= :orderValue and a.portalModule = :portalModuleName and a.departmentTrainingAuthorizer.id = :deptId")
    List<AuthorizationRole> getAllByOrder_valueAndPortalModuleAndDepartmentTrainingAuthorizer(@Param("orderValue") int orderValue, @Param("portalModuleName") PortalModuleName portalModuleName, @Param("deptId") Long deptId);

}

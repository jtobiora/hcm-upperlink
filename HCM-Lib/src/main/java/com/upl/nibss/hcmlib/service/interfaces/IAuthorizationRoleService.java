package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.AuthorizationRole;

import java.util.List;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
public interface IAuthorizationRoleService {

    List<AuthorizationRole> getAll() throws Exception;

    List<AuthorizationRole> getAllByPortalModuleName(PortalModuleName portalModuleName) throws Exception;

    List<AuthorizationRole> getAllByPortalModuleNameAndActivated(PortalModuleName portalModuleName) throws Exception;

    //New Query
    List<AuthorizationRole> getAllByPortalModuleNameAndJobrole(PortalModuleName portalModuleName, Long jobroleId) throws Exception;

    List<AuthorizationRole> getAllByPortalModuleNameAndActivatedAndJobrole(PortalModuleName portalModuleName, Long jobroleId) throws Exception;

    List<AuthorizationRole> getAllByPortalModuleNameAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception;

    List<AuthorizationRole> getAllByPortalModuleNameAndActivatedAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception;

    AuthorizationRole getByPortalModuleNameAndJobrole(PortalModuleName portalModuleName, Long id, Long jobroleId) throws Exception;

    AuthorizationRole getByPortalModuleNameAndActivatedAndJobrole(PortalModuleName portalModuleName, Long id, Long jobroleId) throws Exception;

    AuthorizationRole getByPortalModuleNameAndDept(PortalModuleName portalModuleName, Long id, Long deptId) throws Exception;

    AuthorizationRole getByPortalModuleNameAndActivatedAndDept(PortalModuleName portalModuleName, Long id, Long deptId) throws Exception;


    AuthorizationRole get(Long id) throws Exception;

    AuthorizationRole getByPortalModuleName(PortalModuleName portalModuleName, Long id) throws Exception;

    AuthorizationRole getAjaxByPortalModuleName(PortalModuleName portalModuleName, Long id) throws Exception;

    AuthorizationRole getByPortalModuleNameAndActivated(PortalModuleName portalModuleName, Long id) throws Exception;

    AuthorizationRole save(AuthorizationRole authorizationRole) throws Exception;

    List<AuthorizationRole> getAllByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception;

    List<AuthorizationRole> getAllAjaxByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception;

    List<Long> getAllidsByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception;

    int countOfSamePortalModuleAndPriority(PortalModuleName portalModuleName, int priority);

    int countOfSamePortalModuleAndPriorityNotId(PortalModuleName portalModuleName, int priority, Long id);

    //New Query
    List<Long> getAllidsByPortalModuleNameAndActivatedAndAlertAndJobrole(PortalModuleName portalModuleName, Long jobroleId) throws Exception;

    int countOfSamePortalModuleAndPriorityAndJobrole(PortalModuleName portalModuleName, int priority, Long jobroleId);

    int countOfSamePortalModuleAndPriorityNotIdAndJobrole(PortalModuleName portalModuleName, int priority, Long id, Long jobroleId);

    List<Long> getAllidsByPortalModuleNameAndActivatedAndAlertAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception;

    int countOfSamePortalModuleAndPriorityAndDept(PortalModuleName portalModuleName, int priority, Long deptId);

    int countOfSamePortalModuleAndPriorityNotIdAndDept(PortalModuleName portalModuleName, int priority, Long id, Long deptId);

    Boolean toggle(PortalModuleName portalModuleName, Long id) throws Exception;

    /**
     *
     * @param orderValue it is the authorization priority value
     * @param moduleName the name of the module
     * @return
     */
    List<AuthorizationRole> getAllByOrderValueAndPortalModuleName(int orderValue, PortalModuleName moduleName);

    /**
     *
     * @param orderValue it is the authorization priority value
     * @param moduleName the name of the module
     * @param jobRoleLeaveAuthorizeId the Id of the jobRole whose leave is to be authorized
     * @return
     */
    List<AuthorizationRole> getAllByOrderValueAndPortalModuleNameAndJobRoleLeaveAuthorizerId(int orderValue, PortalModuleName moduleName, Long jobRoleLeaveAuthorizeId);

    /**
     *
     * @param orderValue it is the authorization priority value
     * @param moduleName the name of the module
     * @param departmentTrainingAuthorizeId the Id of the department whose training is to be authorized
     * @return
     */
    List<AuthorizationRole> getAllByOrderValueAndPortalModuleNameAndDepartmentTrainingAuthorizerId(int orderValue, PortalModuleName moduleName, Long departmentTrainingAuthorizeId);

    void save(List<AuthorizationRole> authorizationRoles);
}

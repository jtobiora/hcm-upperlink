package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.AuthorizationRole;
import com.upl.nibss.hcmlib.repo.AuthorizationRoleRepo;
import com.upl.nibss.hcmlib.service.interfaces.IAuthorizationRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
@Service
public class AuthorizationRoleService implements IAuthorizationRoleService {

    @Autowired
    private AuthorizationRoleRepo authorizationRoleRepo;

    @Override
    public List<AuthorizationRole> getAll() throws Exception{
        return authorizationRoleRepo.findAll();
    }

    @Override
    public List<AuthorizationRole> getAllByPortalModuleName(PortalModuleName portalModuleName) throws Exception{
        return authorizationRoleRepo.findAllByPortalModuleName(portalModuleName);
    }

    @Override
    public List<AuthorizationRole> getAllByPortalModuleNameAndActivated(PortalModuleName portalModuleName) throws Exception{
        return authorizationRoleRepo.findAllByPortalModuleNameAndActivated(portalModuleName);
    }

    @Override
    public AuthorizationRole get(Long id) throws Exception{
        return authorizationRoleRepo.findOne(id);
    }

    @Override
    public AuthorizationRole getByPortalModuleName(PortalModuleName portalModuleName, Long id) throws Exception {
        return authorizationRoleRepo.findByPortalModuleName(portalModuleName, id);
    }

    @Override
    public AuthorizationRole getAjaxByPortalModuleName(PortalModuleName portalModuleName, Long id) throws Exception{
        return authorizationRoleRepo.findAjaxByPortalModuleName(portalModuleName, id);
    }

    @Override
    public AuthorizationRole getByPortalModuleNameAndActivated(PortalModuleName portalModuleName, Long id) throws Exception{
        return authorizationRoleRepo.findByPortalModuleNameAndActivated(portalModuleName, id);
    }

    @Override
    public AuthorizationRole save(AuthorizationRole authorizationRole) throws Exception{
        return authorizationRoleRepo.save(authorizationRole);
    }

    @Override
    public List<AuthorizationRole> getAllByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception {
        return authorizationRoleRepo.findAllByPortalModuleNameAndAlertTypeAndActivated(portalModuleName);
    }

    @Override
    public List<AuthorizationRole> getAllAjaxByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception {
        return authorizationRoleRepo.findAllAjaxByPortalModuleNameAndAlertTypeAndActivated(portalModuleName);
    }

    @Override
    public List<Long> getAllidsByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception {
        return authorizationRoleRepo.findAllidsByPortalModuleNameAndAlertTypeAndActivated(portalModuleName);
    }

    @Override
    public int countOfSamePortalModuleAndPriority(PortalModuleName portalModuleName, int priority) {
        return authorizationRoleRepo.countOfSamePortalModuleAndPriority(portalModuleName,priority);
    }

    @Override
    public int countOfSamePortalModuleAndPriorityNotId(PortalModuleName portalModuleName, int priority, Long id) {
        return authorizationRoleRepo.countOfSamePortalModuleAndPriorityNotId(portalModuleName, priority, id);
    }

    @Override
    public Boolean toggle(PortalModuleName portalModuleName, Long id) throws Exception{

        AuthorizationRole authorizationRole = authorizationRoleRepo.findByPortalModuleName(portalModuleName, id);
        if (authorizationRole == null){
            return Boolean.FALSE;
        }
        authorizationRole.setActivated(!authorizationRole.isActivated());
        authorizationRole = authorizationRoleRepo.save(authorizationRole);
        if (authorizationRole != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    @Override
    public List<AuthorizationRole> getAllByPortalModuleNameAndJobrole(PortalModuleName portalModuleName, Long jobroleId) throws Exception {
        return authorizationRoleRepo.findAllByPortalModuleNameAndJobRole(portalModuleName,jobroleId);
    }

    @Override
    public List<AuthorizationRole> getAllByPortalModuleNameAndActivatedAndJobrole(PortalModuleName portalModuleName, Long jobroleId) throws Exception {
        return authorizationRoleRepo.findAllByPortalModuleNameAndJobRoleAndActivated(portalModuleName, jobroleId);
    }

    @Override
    public List<AuthorizationRole> getAllByPortalModuleNameAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception {
        return authorizationRoleRepo.findAllByPortalModuleNameAndDepartment(portalModuleName, deptId);
    }

    @Override
    public List<AuthorizationRole> getAllByPortalModuleNameAndActivatedAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception {
        return authorizationRoleRepo.findAllByPortalModuleNameAndDepartmentAndActivated(portalModuleName, deptId);
    }

    @Override
    public AuthorizationRole getByPortalModuleNameAndJobrole(PortalModuleName portalModuleName, Long id, Long jobroleId) throws Exception {
        return authorizationRoleRepo.findByPortalModuleNameAndJobRole(portalModuleName, id, jobroleId);
    }

    @Override
    public AuthorizationRole getByPortalModuleNameAndActivatedAndJobrole(PortalModuleName portalModuleName, Long id, Long jobroleId) throws Exception {
        return authorizationRoleRepo.findByPortalModuleNameAndJobRoleAndActivated(portalModuleName, id, jobroleId);
    }

    @Override
    public AuthorizationRole getByPortalModuleNameAndDept(PortalModuleName portalModuleName, Long id, Long deptId) throws Exception {
        return authorizationRoleRepo.findByPortalModuleNameAndDepartment(portalModuleName, id, deptId);
    }

    @Override
    public AuthorizationRole getByPortalModuleNameAndActivatedAndDept(PortalModuleName portalModuleName, Long id, Long deptId) throws Exception {
        return authorizationRoleRepo.findByPortalModuleNameAndDepartmentAndActivated(portalModuleName, id, deptId);
    }

    @Override
    public List<Long> getAllidsByPortalModuleNameAndActivatedAndAlertAndJobrole(PortalModuleName portalModuleName, Long jobroleId) throws Exception {
        return authorizationRoleRepo.findAllidsByPortalModuleNameAndAlertTypeAndActivatedAndJobRole(portalModuleName, jobroleId);
    }

    @Override
    public int countOfSamePortalModuleAndPriorityAndJobrole(PortalModuleName portalModuleName, int priority, Long jobroleId) {
        return authorizationRoleRepo.countOfSamePortalModuleAndPriorityAndJobRole(portalModuleName, priority, jobroleId);
    }

    @Override
    public int countOfSamePortalModuleAndPriorityNotIdAndJobrole(PortalModuleName portalModuleName, int priority, Long id, Long jobroleId) {
        return authorizationRoleRepo.countOfSamePortalModuleAndPriorityNotIdAndJobRole(portalModuleName, priority, id, jobroleId);
    }

    @Override
    public List<Long> getAllidsByPortalModuleNameAndActivatedAndAlertAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception {
        return authorizationRoleRepo.findAllidsByPortalModuleNameAndAlertTypeAndActivatedAndDepartment(portalModuleName, deptId);
    }

    @Override
    public int countOfSamePortalModuleAndPriorityAndDept(PortalModuleName portalModuleName, int priority, Long deptId) {
        return authorizationRoleRepo.countOfSamePortalModuleAndPriorityAndDepartment(portalModuleName, priority, deptId);
    }

    @Override
    public int countOfSamePortalModuleAndPriorityNotIdAndDept(PortalModuleName portalModuleName, int priority, Long id, Long deptId) {
        return authorizationRoleRepo.countOfSamePortalModuleAndPriorityNotIdAndDepartment(portalModuleName, priority, id, deptId);
    }

    @Override
    public List<AuthorizationRole> getAllByOrderValueAndPortalModuleName(int orderValue, PortalModuleName moduleName) {
        return authorizationRoleRepo.getAllByOrder_valueAndPortalModule(orderValue, moduleName);
    }

    @Override
    public List<AuthorizationRole> getAllByOrderValueAndPortalModuleNameAndJobRoleLeaveAuthorizerId(int orderValue, PortalModuleName moduleName, Long jobRoleLeaveAuthorizeId) {
        return authorizationRoleRepo.getAllByOrder_valueAndPortalModuleAndJobRoleleaveAuthorizer(orderValue, moduleName, jobRoleLeaveAuthorizeId);
    }

    @Override
    public List<AuthorizationRole> getAllByOrderValueAndPortalModuleNameAndDepartmentTrainingAuthorizerId(int orderValue, PortalModuleName moduleName, Long departmentTrainingAuthorizeId) {
        return authorizationRoleRepo.getAllByOrder_valueAndPortalModuleAndDepartmentTrainingAuthorizer(orderValue, moduleName, departmentTrainingAuthorizeId);
    }

    @Override
    public void save(List<AuthorizationRole> authorizationRoles) {
        authorizationRoleRepo.save(authorizationRoles);
    }
}

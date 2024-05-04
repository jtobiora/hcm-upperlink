package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.AwarenessRole;
import com.upl.nibss.hcmlib.model.JobRole;
import com.upl.nibss.hcmlib.repo.AwarenessRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 23/11/2017.
 */
@Service
public class AwarenessRoleService {


    @Autowired
    private AwarenessRoleRepo awarenessRoleRepo;


    public List<AwarenessRole> getAll() throws Exception{
        return awarenessRoleRepo.findAll();
    }


    public List<AwarenessRole> getAllByPortalModuleName(PortalModuleName portalModuleName) throws Exception{
        return awarenessRoleRepo.findAllByPortalModuleName(portalModuleName);
    }


    public List<AwarenessRole> getAllByPortalModuleNameAndActivated(PortalModuleName portalModuleName) throws Exception{
        return awarenessRoleRepo.findAllByPortalModuleNameAndActivated(portalModuleName);
    }


    public AwarenessRole get(Long id) throws Exception{
        return awarenessRoleRepo.findOne(id);
    }


    public AwarenessRole getByPortalModuleName(PortalModuleName portalModuleName, Long id) throws Exception {
        return awarenessRoleRepo.findByPortalModuleName(portalModuleName, id);
    }


    public AwarenessRole getAjaxByPortalModuleName(PortalModuleName portalModuleName, Long id) throws Exception{
        return awarenessRoleRepo.findByPortalModuleName(portalModuleName, id);
    }


    public AwarenessRole getByPortalModuleNameAndActivated(PortalModuleName portalModuleName, Long id) throws Exception{
        return awarenessRoleRepo.findByPortalModuleNameAndActivated(portalModuleName, id);
    }


    public AwarenessRole save(AwarenessRole authorizationRole) throws Exception{
        return awarenessRoleRepo.save(authorizationRole);
    }


    public List<AwarenessRole> getAllByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception {
        return awarenessRoleRepo.findAllByPortalModuleNameAndActivated(portalModuleName);
    }

    public List<JobRole> getAllJobRoleByPortalModuleNameAndActivated(PortalModuleName portalModuleName) throws Exception {
        return awarenessRoleRepo.findAllJobRoleByPortalModuleNameAndActivated(portalModuleName);
    }


    public List<AwarenessRole> getAllAjaxByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception {
        return awarenessRoleRepo.findAllByPortalModuleNameAndActivated(portalModuleName);
    }


    public List<Long> getAllidsByPortalModuleNameAndActivatedAndAlert(PortalModuleName portalModuleName) throws Exception {
        return awarenessRoleRepo.findAllidsByPortalModuleNameAndAlertTypeAndActivated(portalModuleName);
    }


    public int countOfSamePortalModuleAndPriority(PortalModuleName portalModuleName) {
        return awarenessRoleRepo.countOfSamePortalModuleAndPriority(portalModuleName);
    }


    public int countOfSamePortalModuleAndPriorityNotId(PortalModuleName portalModuleName, Long id) {
        return awarenessRoleRepo.countOfSamePortalModuleAndPriorityNotId(portalModuleName, id);
    }


    public Boolean toogle(PortalModuleName portalModuleName, Long id) throws Exception{

        AwarenessRole authorizationRole = awarenessRoleRepo.findByPortalModuleName(portalModuleName, id);
        if (authorizationRole == null){
            return Boolean.FALSE;
        }
        authorizationRole.setActivated(!authorizationRole.isActivated());
        authorizationRole = awarenessRoleRepo.save(authorizationRole);
        if (authorizationRole != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    //Job Role
    public List<AwarenessRole> getAllByPortalModuleNameAndJobrole(PortalModuleName portalModuleName, Long jobRoleId) throws Exception{
        return awarenessRoleRepo.findAllByPortalModuleNameAndJobrole(portalModuleName, jobRoleId);
    }


    public List<AwarenessRole> getAllByPortalModuleNameAndActivatedAndJobrole(PortalModuleName portalModuleName, Long jobRoleId) throws Exception{
        return awarenessRoleRepo.findAllByPortalModuleNameAndActivatedAndJobrole(portalModuleName,jobRoleId);
    }

    public AwarenessRole getByPortalModuleNameAndJobrole(PortalModuleName portalModuleName, Long id, Long jobRoleId) throws Exception {
        return awarenessRoleRepo.findByPortalModuleNameAndJobrole(portalModuleName, id, jobRoleId);
    }

    public AwarenessRole getByPortalModuleNameAndActivatedAndJobrole(PortalModuleName portalModuleName, Long id, Long jobRoleId) throws Exception{
        return awarenessRoleRepo.findByPortalModuleNameAndActivatedAndJobrole(portalModuleName, id, jobRoleId);
    }

    public List<JobRole> getAllJobRoleByPortalModuleNameAndActivatedAndJobrole(PortalModuleName portalModuleName, Long jobRoleId) throws Exception {
        return awarenessRoleRepo.findAllJobRoleByPortalModuleNameAndActivatedAndJobrole(portalModuleName, jobRoleId);
    }


    public List<Long> getAllidsByPortalModuleNameAndActivatedAndAlertAndJobrole(PortalModuleName portalModuleName, Long jobRoleId) throws Exception {
        return awarenessRoleRepo.findAllidsByPortalModuleNameAndAlertTypeAndActivatedAndJobrole(portalModuleName, jobRoleId);
    }


    public int countOfSamePortalModuleAndPriorityAndJobrole(PortalModuleName portalModuleName, Long jobRoleId) {
        return awarenessRoleRepo.countOfSamePortalModuleAndPriorityAndJobrole(portalModuleName, jobRoleId);
    }


    public int countOfSamePortalModuleAndPriorityNotIdAndJobrole(PortalModuleName portalModuleName, Long id, Long jobRoleId) {
        return awarenessRoleRepo.countOfSamePortalModuleAndPriorityNotIdAndJobrole(portalModuleName, id, jobRoleId);
    }


    //Department
    public List<AwarenessRole> getAllByPortalModuleNameAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception{
        return awarenessRoleRepo.findAllByPortalModuleNameAndDept(portalModuleName, deptId);
    }


    public List<AwarenessRole> getAllByPortalModuleNameAndActivatedAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception{
        return awarenessRoleRepo.findAllByPortalModuleNameAndActivatedAndDept(portalModuleName, deptId);
    }

    public AwarenessRole getByPortalModuleNameAndDept(PortalModuleName portalModuleName, Long id, Long deptId) throws Exception {
        return awarenessRoleRepo.findByPortalModuleNameAndDept(portalModuleName, id, deptId);
    }

    public AwarenessRole getByPortalModuleNameAndActivatedAndDept(PortalModuleName portalModuleName, Long id, Long deptId) throws Exception{
        return awarenessRoleRepo.findByPortalModuleNameAndActivatedAndDept(portalModuleName, id, deptId);
    }

    public List<JobRole> getAllJobRoleByPortalModuleNameAndActivatedAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception {
        return awarenessRoleRepo.findAllJobRoleByPortalModuleNameAndActivatedAndDept(portalModuleName, deptId);
    }


    public List<Long> getAllidsByPortalModuleNameAndActivatedAndAlertAndDept(PortalModuleName portalModuleName, Long deptId) throws Exception {
        return awarenessRoleRepo.findAllidsByPortalModuleNameAndAlertTypeAndActivatedAndDept(portalModuleName, deptId);
    }


    public int countOfSamePortalModuleAndPriorityAndDept(PortalModuleName portalModuleName, Long deptId) {
        return awarenessRoleRepo.countOfSamePortalModuleAndPriorityAndDept(portalModuleName, deptId);
    }


    public int countOfSamePortalModuleAndPriorityNotIdAndDept(PortalModuleName portalModuleName, Long id, Long deptId) {
        return awarenessRoleRepo.countOfSamePortalModuleAndPriorityNotIdAndDept(portalModuleName, id, deptId);
    }

}

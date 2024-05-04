package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.model.JobRole;
import com.upl.nibss.hcmlib.repo.JobRoleRepo;
import com.upl.nibss.hcmlib.service.interfaces.IJobRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 01/11/2017.
 */
@Service
public class JobRoleService implements IJobRoleService {

    private static final Logger logger = LoggerFactory.getLogger(JobRoleService.class);

    @Autowired
    private JobRoleRepo jobRoleRepo;

    @Override
    public List<JobRole> getAll() throws Exception {
        return jobRoleRepo.findAllJobRoles();
    }

    @Override
    public List<JobRole> getAllActivated() throws Exception {
        return jobRoleRepo.getAllActivated();
    }

    @Override
    public List<String> getAlljobRoleTitle() throws Exception {
        return jobRoleRepo.getAllJobRoleTitles();
    }

    @Override
    public JobRole getAjaxJobRole(Long id) throws Exception {
        return jobRoleRepo.findJobRoles(id);
    }

    @Override
    public JobRole get(Long id) throws Exception {
        return jobRoleRepo.findOne(id);
    }

    @Override
    public Document getJobDescriptionDoc(Long id) {

        JobRole jobRole = jobRoleRepo.findOne(id);
        if (jobRole == null){
            logger.error("jobrole not found, jobroleid is --> {}", id);
            return null;
        }

        return jobRole.getJobDescDoc();
    }

    @Override
    public JobRole saveJobRole(JobRole roleEO) throws Exception {
        return jobRoleRepo.save(roleEO);
    }

    @Override
    public List<JobRole> saveJobRole(List<JobRole> roleEO) throws Exception {
        return jobRoleRepo.save(roleEO);
    }

    @Override
    public boolean togglejobRole(Long id) throws Exception {
        return false;
    }

    @Override
    public Set<JobRole> get(List<Long> jobroleid) {
        return jobRoleRepo.findAllJobRoleEOById(jobroleid);
    }
}

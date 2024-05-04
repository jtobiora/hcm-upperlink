package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.model.JobRole;

import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 01/11/2017.
 */
public interface IJobRoleService {

    List<JobRole> getAll() throws Exception;

    List<JobRole> getAllActivated() throws Exception;

    List<String> getAlljobRoleTitle() throws Exception;

    JobRole getAjaxJobRole(Long id) throws Exception;

    JobRole get(Long id) throws Exception;

    Document getJobDescriptionDoc(Long id);

    JobRole saveJobRole(JobRole roleEO) throws Exception;

    List<JobRole> saveJobRole(List<JobRole> roleEO) throws Exception;

    boolean togglejobRole(Long id) throws Exception;

    Set<JobRole> get(List<Long> jobroleid);

}

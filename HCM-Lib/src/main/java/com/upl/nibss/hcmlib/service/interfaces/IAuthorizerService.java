package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.Authorizers;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.JobRole;

import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
public interface IAuthorizerService {

//List<Authorizers> getALLByLoanRequestAndAuthRoleIds(Long loanRequest, List<Long> authRoleIds) throws Exception;

    JobRole getJobRoles(Long authorizerId);

    Authorizers save(Authorizers authorizers) throws Exception;

    Authorizers get(Long id) throws Exception;
}

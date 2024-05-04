package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Authorizers;
import com.upl.nibss.hcmlib.model.JobRole;
import com.upl.nibss.hcmlib.repo.AuthorizerRepo;
import com.upl.nibss.hcmlib.service.interfaces.IAuthorizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Service
public class AuthorizerService implements IAuthorizerService {

    @Autowired
    private AuthorizerRepo authorizerRepo;

    public JobRole getJobRoles(Long authorizerId){
        return authorizerRepo.findJobRole(authorizerId);
    }

//    @Override
//    public List<Authorizers> getALLByLoanRequestAndAuthRoleIds(Long loanRequest, List<Long> authRoleIds) throws Exception {
//        return authorizerRepo.findByLoanRequest(loanRequest,authRoleIds);
//    }

    @Override
    public Authorizers save(Authorizers authorizers) throws Exception {
        return authorizerRepo.save(authorizers);
    }

    @Override
    public Authorizers get(Long id) throws Exception {
        return authorizerRepo.findOne(id);
    }
}

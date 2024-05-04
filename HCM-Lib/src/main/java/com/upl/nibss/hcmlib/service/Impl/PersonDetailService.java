package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.PersonDetails;
import com.upl.nibss.hcmlib.repo.PersonalDetailRepo;
import com.upl.nibss.hcmlib.service.interfaces.IPersonDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonDetailService implements IPersonDetailService{
    private static final Logger logger = LoggerFactory.getLogger(PersonDetailService.class);

    @Autowired
    private PersonalDetailRepo personalDetailRepo;

    @Override
    public List<PersonDetails> getAllAjax() throws Exception {
        return personalDetailRepo.findAllAjax();
    }

    @Override
    public PersonDetails getAjax(Long id) throws Exception {
        return personalDetailRepo.findAjax(id);
    }

    @Override
    public PersonDetails findByEmployeeId(Long id) throws Exception {
        return personalDetailRepo.findByEmployeeId(id);
    }

    @Override
    public PersonDetails save(PersonDetails personDetails) throws Exception {
        return personalDetailRepo.save(personDetails);
    }
}

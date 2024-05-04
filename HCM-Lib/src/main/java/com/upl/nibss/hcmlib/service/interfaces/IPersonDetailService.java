package com.upl.nibss.hcmlib.service.interfaces;


import com.upl.nibss.hcmlib.model.PersonDetails;

import java.util.List;

public interface IPersonDetailService {

    List<PersonDetails> getAllAjax() throws Exception;

    PersonDetails getAjax(Long id) throws Exception;

    PersonDetails findByEmployeeId(Long id) throws Exception;

    PersonDetails save(PersonDetails personDetails) throws Exception;
}

package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.Country;

import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
public interface ICountryService {

    List<Country> getAll() throws Exception;

    Country getAjaxCountry(Long id) throws Exception;

    Country get(Long id) throws Exception;

}

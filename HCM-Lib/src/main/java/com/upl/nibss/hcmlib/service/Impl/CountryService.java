package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Country;
import com.upl.nibss.hcmlib.repo.CountryRepo;
import com.upl.nibss.hcmlib.service.interfaces.ICountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
@Service
public class CountryService implements ICountryService{

    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepo countryRepo;

    @Override
    public List<Country> getAll() throws Exception {
        return countryRepo.findAllAjaxCountry();
    }

    @Override
    public Country getAjaxCountry(Long id) throws Exception {
        return countryRepo.findAjaxCountry(id);
    }

    @Override
    public Country get(Long id) throws Exception {
        return countryRepo.findOne(id);
    }
}

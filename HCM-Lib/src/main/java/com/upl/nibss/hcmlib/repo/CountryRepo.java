package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
@Repository
@Transactional
public interface CountryRepo extends JpaRepository<Country, Long> {

    @Query("select c from Country c ")
    List<Country> findAllAjaxCountry();

    @Query("select c from Country c where c.countryId = :id")
    Country findAjaxCountry(@Param("id") Long id);

}

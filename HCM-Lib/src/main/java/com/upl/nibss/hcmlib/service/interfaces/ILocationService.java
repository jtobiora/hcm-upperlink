package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.Location;

import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
public interface ILocationService {


    List<Location> getAll() throws Exception;

    List<String> getAllLocationNames() throws Exception;

    Location get(Long id) throws Exception;

    Location getAjaxLocation(Long id) throws Exception;

    Location save(Location location) throws Exception;

}

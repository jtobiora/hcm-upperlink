package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Location;
import com.upl.nibss.hcmlib.repo.LocationRepo;
import com.upl.nibss.hcmlib.service.interfaces.ILocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
@Service
public class LocationService implements ILocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    private LocationRepo locationRepo;

    @Override
    public List<Location> getAll() throws Exception {
        return locationRepo.findAllAjaxLocation();
    }

    @Override
    public List<String> getAllLocationNames() throws Exception {
        return locationRepo.findLocationNames();
    }

    @Override
    public Location get(Long id) throws Exception {
        return locationRepo.findOne(id);
    }

    @Override
    public Location getAjaxLocation(Long id) throws Exception {
        return locationRepo.findAjaxLocationById(id);
    }

    @Override
    public Location save(Location location) throws Exception {
        return locationRepo.save(location);
    }

}

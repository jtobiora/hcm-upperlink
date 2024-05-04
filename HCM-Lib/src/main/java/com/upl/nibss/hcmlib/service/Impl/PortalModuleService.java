package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.PortalModule;
import com.upl.nibss.hcmlib.repo.PortalModuleRepo;
import com.upl.nibss.hcmlib.service.interfaces.IPortalModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
@Service
public class PortalModuleService implements IPortalModuleService {

    @Autowired
    private PortalModuleRepo portalModuleRepo;

    @Override
    public List<PortalModule> getAll() {
        return portalModuleRepo.findAll();
    }

    @Override
    public PortalModule get(Long id) {
        return portalModuleRepo.findOne(id);
    }

    @Override
    public PortalModule getByPortalModuleName(PortalModuleName portalModuleName) {
        return portalModuleRepo.findByPortalmoduleName(portalModuleName);
    }
}

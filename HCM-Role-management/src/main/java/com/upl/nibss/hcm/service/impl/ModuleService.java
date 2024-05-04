package com.upl.nibss.hcm.service.impl;

import com.upl.nibss.hcm.service.interfaces.IModuleService;
import com.upl.nibss.hcmlib.model.Module;
import com.upl.nibss.hcmlib.repo.ModuleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 20/10/2017.
 */
@Service
public class ModuleService implements IModuleService {

    private final static Logger logger = LoggerFactory.getLogger(ModuleService.class);

    @Autowired
    private ModuleRepo moduleRepo;

    @Override
    public Module get(Long id) {

        try {
            return moduleRepo.findOne(id);
        } catch (Exception e) {
            logger.error("Unable to find the module with id : {}", id);
        }

        return new Module();
    }

    @Override
    public List<Module> getAll() {
        try {
            return moduleRepo.findAll();
        } catch (Exception e) {
            logger.error("Unable to find all modules", e);
        }

        return new ArrayList<>();
    }
}

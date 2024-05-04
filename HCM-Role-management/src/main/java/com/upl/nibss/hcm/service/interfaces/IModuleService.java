package com.upl.nibss.hcm.service.interfaces;

import com.upl.nibss.hcmlib.model.Module;

import java.util.List;

/**
 * Created by toyin.oladele on 20/10/2017.
 */
public interface IModuleService {

    Module get(Long id);

    List<Module> getAll();

}

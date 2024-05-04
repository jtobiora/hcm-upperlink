package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.PortalModule;

import java.util.List;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
public interface IPortalModuleService {

    List<PortalModule> getAll();

    PortalModule get(Long id);

    PortalModule getByPortalModuleName(PortalModuleName portalModuleName);
}

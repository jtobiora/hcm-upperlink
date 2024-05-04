package com.upl.nibss.hcmlib.proxyClass;

import lombok.Data;

/**
 * Created by toyin.oladele on 24/10/2017.
 */
@Data
public class AjaxModuleTask {

    private Long id;
    private String name;
    private String moduleName;

    public AjaxModuleTask() {
    }

    public AjaxModuleTask(Long id, String name, String moduleName) {
        this.id = id;
        this.name = name;
        this.moduleName = moduleName;
    }
}

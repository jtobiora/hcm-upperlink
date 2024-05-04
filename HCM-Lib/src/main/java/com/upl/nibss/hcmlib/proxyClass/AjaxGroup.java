package com.upl.nibss.hcmlib.proxyClass;

import lombok.Data;

/**
 * Created by toyin.oladele on 16/10/2017.
 */
@Data
public class AjaxGroup {

    private Long id;
    private String name;
    private boolean status;

    public AjaxGroup() {
    }

    public AjaxGroup(Long id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}

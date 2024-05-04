package com.upl.nibss.hcm.dto.group;

import com.upl.nibss.hcmlib.dto.SuperResponse;
import com.upl.nibss.hcmlib.proxyClass.AjaxGroup;
import lombok.Data;

import java.util.List;

/**
 * Created by toyin.oladele on 22/10/2017.
 */
@Data
public class GroupResponse extends SuperResponse {
    private List<AjaxGroup> groups;
}

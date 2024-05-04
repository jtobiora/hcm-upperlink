package com.upl.nibss.hcm.dto.privilege;

import com.upl.nibss.hcmlib.dto.SuperResponse;
import com.upl.nibss.hcmlib.proxyClass.AjaxTasksPerGroup;
import lombok.Data;

import java.util.List;

/**
 * Created by toyin.oladele on 22/10/2017.
 */
@Data
public class PrivilegeResponse extends SuperResponse {
    private List<AjaxTasksPerGroup> groupTasks;
}

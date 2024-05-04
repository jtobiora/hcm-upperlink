package com.upl.nibss.hcm.dto.task;

import com.upl.nibss.hcmlib.dto.SuperResponse;
import com.upl.nibss.hcmlib.proxyClass.AjaxModuleTask;
import lombok.Data;

import java.util.List;

/**
 * Created by toyin.oladele on 22/10/2017.
 */
@Data
public class TaskResponse extends SuperResponse {

    private List<AjaxModuleTask> tasks;

}

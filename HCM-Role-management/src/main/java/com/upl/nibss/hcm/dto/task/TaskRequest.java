package com.upl.nibss.hcm.dto.task;

import lombok.Data;

/**
 * Created by toyin.oladele on 21/10/2017.
 */
@Data
public class TaskRequest {

    private String name;
    private String route;
    private String description;
    private int step;
    private String icon;
    private Long moduleId;
    private Long parentTaskId;

}

package com.upl.nibss.hcm.dto.privilege;

import lombok.Data;

import java.util.List;

/**
 * Created by toyin.oladele on 25/10/2017.
 */
@Data
public class PrivilegeRequestByGroupName {

    private String groupName;
    private List<Long> taskIds;

}

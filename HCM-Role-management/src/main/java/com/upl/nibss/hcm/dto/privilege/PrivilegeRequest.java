package com.upl.nibss.hcm.dto.privilege;

import lombok.Data;

import java.util.List;

/**
 * Created by toyin.oladele on 22/10/2017.
 */
@Data
public class PrivilegeRequest {

    private Long groupId;
    private List<Long> taskIds;
}

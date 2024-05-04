package com.upl.nibss.hcmlib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by stanlee on 30/01/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDetail {

    private String sessionId;
    private String moduleName;
    private Long requestId;
    private Long authorizerId;
    private Long approverId;

}

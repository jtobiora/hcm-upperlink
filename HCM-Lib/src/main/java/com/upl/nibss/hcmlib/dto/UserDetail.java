package com.upl.nibss.hcmlib.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by toyin.oladele on 12/10/2017.
 */
@Data
@NoArgsConstructor
public class UserDetail {

    private Long userId;
    private String name;
    private String sessionId;
    private boolean generatedPassword;
//    private String jobRole;
    private List<String> groups;
    private List<String> modules;
    private Long employeeId;
    private Map<String,Object> employee = new HashMap<>();

    public UserDetail(String name, Long employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

}

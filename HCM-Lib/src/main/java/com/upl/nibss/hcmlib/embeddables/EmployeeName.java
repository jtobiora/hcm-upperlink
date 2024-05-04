/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author Gbenga
 */
@Data
@AllArgsConstructor
@Embeddable
public class EmployeeName implements Serializable {
    private Long id;

    @JsonProperty("employee_no")
    @Column(name = "employee_no")
    private String employeeNo;

    @JsonProperty("first_name")
    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    @JsonProperty("middle_name")
    @Column(name = "MIDDLE_NAME", length = 100)
    private String middleName;

    @JsonProperty("last_name")
    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    public EmployeeName() {
    }
}

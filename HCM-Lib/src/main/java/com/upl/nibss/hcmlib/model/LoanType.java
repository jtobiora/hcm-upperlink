/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "LOAN_TYPES")
public class LoanType extends SuperModel implements Serializable {

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TYPE_ID")
    private Long typeId;

    @JsonProperty("loan_type_name")
    @Column(name = "LOAN_TYPE", nullable = false, length = 100, unique = true)
    private String loanType;

    @JsonProperty("short_description")
    @Column(name = "SHORT_DESCRIPTION", length = 200)
    private String shortDescription;

    //% this is percentage
    private Integer interest_subsidy;

    //month(the number of month before the employee is eligible for the loan interest)
    private Integer interest_subsidy_tenor;

    private boolean activated = true;

    public LoanType() {

    }

}

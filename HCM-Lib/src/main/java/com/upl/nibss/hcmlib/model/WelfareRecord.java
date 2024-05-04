/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.embeddables.WelfareDocumentation;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "WELFARE_RECORDS")
public class WelfareRecord extends SuperModel implements Serializable {

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Record_ID")
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "WELFARE_DOCUMENTS", joinColumns = @JoinColumn(name = "Record_ID"))
    private Set<WelfareDocumentation> documentations;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_SUBJECT_ID", nullable = false)
    private Employee employeeSubject;

    public WelfareRecord() {
    }
}

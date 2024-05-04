/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.ExitType;
import com.upl.nibss.hcmlib.enums.OptionalityType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "EXIT_CHECKLISTS")
public class ExitChecklist extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "Category", length = 20)
    @Enumerated(EnumType.STRING)
    private ExitType exitCategory;

    @Column(name = "REQUIREMENTS", length = 100)
    private String requirement;

    @Column(name = "USER_COMMENT", length = 300)
    private String userComment;

    @Column(name = "REQ_OPTIONALITY", length = 30)
    @Enumerated(EnumType.STRING)
    private OptionalityType optionality;

    @JsonBackReference
    @OneToOne(mappedBy = "exitChecklist", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private ExitRecord exitRecord;

    public ExitChecklist() {
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.upl.nibss.hcmlib.enums.OptionalityType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "WELFARE_CHECKLISTS")
public class WelfareChecklist extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "REQUIREMENTS", length = 100)
    private String requirement;

    @Column(name = "USER_COMMENT", length = 300)
    private String userComment;

    @Column(name = "REQ_OPTIONALITY", length = 30)
    @Enumerated(EnumType.STRING)
    private OptionalityType optionality;

    public WelfareChecklist() {
    }

}

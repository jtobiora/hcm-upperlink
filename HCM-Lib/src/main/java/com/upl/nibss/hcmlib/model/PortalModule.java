/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.enums.PortalModuleName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "PORTAL_MODULES")
public class PortalModule extends SuperModel implements Serializable {

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Module_ID")
    private Long moduleId;

    @Column(name = "Module_Name", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private PortalModuleName name;

    @Column(name = "Module_Desc", length = 200)
    private String description;

    public PortalModule() {
    }

}

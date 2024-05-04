/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.embeddables.Document;
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
@Table(name = "company_forms")
public class CompanyForms extends SuperModel implements Serializable {

    private static final String CREATEDBY = "created_by";
    private static final String UPDATEDBY = "updated_by";
    private static final String COMPANY_DOCUMENT = "company_document";
    private static final String FORM_CATEGORY = "formcategory";

    @Id
    @JsonProperty(Constants.ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forms_id", nullable = false)
    private Long formId;

    private String name;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "form_category_id", nullable = false)
    private PortalModule formCategory;

    @JsonProperty(COMPANY_DOCUMENT)
    @Embedded
    private Document companyDoc;

    @JsonManagedReference
    @JsonProperty(CREATEDBY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    @JsonManagedReference
    @JsonProperty(UPDATEDBY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    private Employee updatedBy;

    private boolean activated;

}

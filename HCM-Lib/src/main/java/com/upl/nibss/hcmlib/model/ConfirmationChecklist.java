/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.CredentialType;
import com.upl.nibss.hcmlib.enums.OptionalityType;
import com.upl.nibss.hcmlib.enums.UserType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "confirmation_checklist", uniqueConstraints = @UniqueConstraint(columnNames = {"requirements","user_type"}))
public class ConfirmationChecklist extends SuperModel implements Serializable {

    private static final String USER_COMMENT = "user_comment";
    private static final String CREDENTIAL_CATEGORY = "credential_category";
    private static final String REQ_OPTIONALITY = "req_optionality";
    private static final String CONFIRMATION_RECORD = "confirmation_record";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(CREDENTIAL_CATEGORY)
    @Enumerated(EnumType.STRING)
    private CredentialType credentialCategory;

    @Column(name = "requirements", length = 100, unique = true)
    private String requirement;

    @JsonProperty(USER_COMMENT)
    @Column(name = "user_comment", length = 200)
    private String userComment;

    @JsonProperty(REQ_OPTIONALITY)
    @Column(name = "req_optionality", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private OptionalityType optionality;

    @JsonIgnore
    @Column(name = "USER_TYPE", length = 25, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean activated;

}

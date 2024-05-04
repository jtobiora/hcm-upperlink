/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.enums.CompletionStatus;
import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.enums.ExitType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "EXIT_RECORDS")
public class ExitRecord extends SuperModel implements Serializable {

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Long recordId;

//    @ElementCollection(targetClass = ExitDocumentation.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "EXIT_DOCUMENT", joinColumns = @JoinColumn(name = "Record_ID"))
//    private Set<ExitDocumentation> documentations;

    @Column(name = "COMPLETION_STATUS", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;

    @Column(name = "COMMENT_ON_DOC", length = 300)
    private String documentComment;

    @Column(name = "COMPLETION_DATE")
    private Date completionDate;

    @Embedded
    private Document exitDocument;

    @Column(name = "DOC_AVAILABLE", nullable = false)
    private boolean docAvailable;

//    @ElementCollection(targetClass = ExitDocumentation.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "EXIT_AUTHORIZATIONS", joinColumns = @JoinColumn(name = "AUTH_ID"))
//    private Set<Authorizers> exitAuthorization;
//
//    @ElementCollection(targetClass = ExitDocumentation.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "EXIT_AWARENESS", joinColumns = @JoinColumn(name = "AWARE_ID"))
//    private Set<Awarers> exitAwareness;


    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "EMPLOYEE_INITIATOR_ID", nullable = false)
    private Employee employeeInitiator;

    @JsonManagedReference
    @ManyToOne(targetEntity = Employee.class, optional = false)
    @JoinColumn(name = "EMPLOYEE_SUBJECT_ID", nullable = false)
    private Employee employeeSubject;

    @Column(name = "EXIT_CATEGORY", length = 20)
    @Enumerated(EnumType.STRING)
    private ExitType exitCategory;

    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CHECKLIST_RECORD_AUTHORIZER",
            joinColumns = @JoinColumn(name = "checklist_record_id", referencedColumnName = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "authorizers_id", referencedColumnName = "id"))
    private Set<Authorizers> confirmationAuthorizers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CHECKLIST_RECORD_AWARER",
            joinColumns = @JoinColumn(name = "checklist_record_id", referencedColumnName = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "awarers_id"))
    private Set<Awarers> confirmationAwarers;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exit_checklist_id")
    private ExitChecklist exitChecklist;

}

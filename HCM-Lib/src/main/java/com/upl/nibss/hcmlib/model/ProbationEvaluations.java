package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.enums.BehaviouralMetrics;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by toyin.oladele on 06/12/2017.
 */
@Data
@Entity
public class ProbationEvaluations extends SuperModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(unique = true)
    private Employee employee;

    @NotNull
    @ManyToOne
    private Employee supervisor;

    private Year financialYear;

    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics quality_and_accuracy_of_work;
    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics acceptance_and_discharge_of_responsibility;
    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics attendance_or_punctuality;
    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics initiative;
    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics responsibility_with_others;
    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics external_customer_courtesy;
    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics proactive_learning_and_application_of_new_knowledge;
    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics integrity;
    @Enumerated(EnumType.STRING)
    private BehaviouralMetrics agreed_functional_objectives;

    //Have the objectives identified for the probation period been met
    private boolean objectives_met;

    //If NO, please provide intervention
    @Lob
    private String intervention;

    //Have the training/development needs/interventions identified for the probationary period been addressed?
    private boolean t_and_d_identified_addressed;

    //Summarise the employee's performance and progress over the period.
    @Lob
    private String employee_performance_summarise;

    //Is the employee's appointment to be confirmed?
    private boolean to_be_confirmed;

    //If NO, please provide reasons below and summarize what action has been taken to address any difficulties which have arisen during the probationary period.
    @Lob
    private String reason_and_suggestion;

    //The employee may provide any comments about their experience of the probationary process here.
    @Lob
    private String probationary_process_experience;

    //Should the emplyee's probationary period be extended?
    private boolean probationary_period_be_extended;

    //If YES, please provide reasons and, where appropriate, specify any areas of improvement required and how these will be monitored.
    @Lob
    private String reason_for_extending;

    //Length of the extension (maximum 3 months)
    private Integer extension_length;

    //Extended Final Review date:
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date final_review_date;

    //To confirm appointment? Yes/No
    private boolean confirm_employee;

    //To terminate employment? Yes/No
    private boolean terminate_employee;

    @JsonProperty("approval_status")
    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @Lob
    private String authComment;

    @JsonProperty("approval_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    @JsonProperty("authorizers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authorizers> requestAuthorization = new HashSet<>();

    @JsonProperty("awarers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Awarers> requestAwareness = new HashSet<>();

    private String reason;

}

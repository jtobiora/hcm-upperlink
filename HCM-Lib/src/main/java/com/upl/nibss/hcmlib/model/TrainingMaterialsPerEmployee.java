package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 * Created by toyin.oladele on 15/12/2017.
 */
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "training_record_id"}))
public class TrainingMaterialsPerEmployee extends SuperModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "employee_training_materials")
    @Embedded
    private List<Document> materials = new ArrayList<>();

    private boolean transportAllowance;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_record_id")
    private TrainingRecord trainingRecord;

    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    @JsonProperty("authorizers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "employee_training_materials_authorizations",
            joinColumns = @JoinColumn(name = "training_material_per_employee_id"),
            inverseJoinColumns = @JoinColumn(name = "authoriser_id"))
    private Set<Authorizers> requestAuthorization = new HashSet<>();

    @JsonProperty("awarers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "employee_training_materials_awareness",
            joinColumns = @JoinColumn(name = "training_material_per_employee_id"),
            inverseJoinColumns = @JoinColumn(name = "awarer_id"))
    private Set<Awarers> requestAwareness = new HashSet<>();

    private String reason;


}

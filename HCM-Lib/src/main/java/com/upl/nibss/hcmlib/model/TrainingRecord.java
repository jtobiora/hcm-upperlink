package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.enums.TrainingBookingStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 * Created by toyin.oladele on 11/12/2017.
 */
@Data
@Entity
@Table(name = "training_records")
public class TrainingRecord extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Employee supervisor;

    @ManyToOne
    private Department department;

    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Employee> trainees = new ArrayList<>();

    private String trainingName;

    @Lob
    private String trainingDescription;

    @JsonProperty("start")
    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date training_start_day;

    @JsonProperty("end")
    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date training_end_day;

    @Enumerated(EnumType.STRING)
    private TrainingBookingStatus status;

    private String title;

    private String icon;

    private String color;

    //Bytecode enhancement is the only viable workaround.
    // However, it only works if
    // the parent side is annotated with @LazyToOne(LazyToOneOption.NO_PROXY)
    // and the child side is NOT using @MapsId.
//    @LazyToOne(LazyToOneOption.NO_PROXY) . This is for better performance
//    @OneToOne
//    private TrainingJustification trainingJustification;

    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    @JsonProperty("authorizers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "training_request_authorizations",
            joinColumns = @JoinColumn(name = "training_record_id"),
            inverseJoinColumns = @JoinColumn(name = "authoriser_id"))
    private Set<Authorizers> requestAuthorization = new HashSet<>();

    @JsonProperty("awarers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "training_request_awareness",
            joinColumns = @JoinColumn(name = "training_record_id"),
            inverseJoinColumns = @JoinColumn(name = "awarer_id"))
    private Set<Awarers> requestAwareness = new HashSet<>();

    private String reason;


    public Department getDepartment() {
        return supervisor != null && supervisor.getDepartment() != null ? new Department(supervisor.getDepartment().getId()) : null;
    }

    public String getColor() {
        return status != null ? status.getColor() : color;
    }

    public String getIcon() {
        return status != null ? status.getIcon() : icon;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TrainingRecord that = (TrainingRecord) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(trainingName, that.trainingName) &&
                Objects.equals(trainingDescription, that.trainingDescription) &&
                Objects.equals(training_start_day, that.training_start_day) &&
                Objects.equals(training_end_day, that.training_end_day) &&
                status == that.status &&
                Objects.equals(title, that.title) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(color, that.color) &&
                approvalStatus == that.approvalStatus &&
                Objects.equals(approvalDate, that.approvalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, trainingName, trainingDescription, training_start_day, training_end_day, status, title, icon, color, approvalStatus, approvalDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainingRecord{");
        sb.append("id=").append(id);
        sb.append(", trainingName='").append(trainingName).append('\'');
        sb.append(", trainingDescription='").append(trainingDescription).append('\'');
        sb.append(", training_start_day=").append(training_start_day);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", training_end_day=").append(training_end_day);
        sb.append(", status=").append(status);
        sb.append(", title='").append(title).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", deleted=").append(deleted);
        sb.append(", approvalStatus=").append(approvalStatus);
        sb.append(", approvalDate=").append(approvalDate);
        sb.append('}');
        return sb.toString();
    }
}
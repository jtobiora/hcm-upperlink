package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by toyin.oladele on 25/11/2017.
 */
@Data
@Entity
public class TransferRecordHistory extends SuperModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("employee_id")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @JsonProperty("new_department_id")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Department newdepartment;

    @JsonProperty("old_department_id")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Department oldDepartment;

    @JsonProperty("new_job_role_id")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private JobRole newJobRole;

    @JsonProperty("old_job_role_id")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private JobRole oldJobRole;

    @JsonProperty("effective_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date effectiveDate;

    @JsonProperty("new_supervisor_id")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee newSupervisor;

    @JsonProperty("old_supervisor_id")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee oldSupervisor;

    @JsonProperty("last_transfer_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastTransferDate;

//    @JsonProperty("new_location_id")
//    @JsonManagedReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Location newLocation;
//
//    @JsonProperty("old_location_id")
//    @JsonManagedReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Location oldLocation;

//    @JsonIgnore
//    @JsonManagedReference
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "transfer_record_history_allowances",
//            joinColumns = @JoinColumn(name = "transfer_record_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "allowance_id", referencedColumnName = "id"))
//    private Set<Allowance> allowances;

    @JsonProperty("approval_status")
    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @JsonProperty("approval_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    @JsonProperty("authorizers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "transfer_history_request_authorizations",
            joinColumns = @JoinColumn(name = "transfer_history_record_id"),
            inverseJoinColumns = @JoinColumn(name = "authoriser_id"))
    private Set<Authorizers> requestAuthorization = new HashSet<>();

    @JsonProperty("awarers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "transfer_history_request_awareness",
            joinColumns = @JoinColumn(name = "transfer_history_record_id"),
            inverseJoinColumns = @JoinColumn(name = "awarer_id"))
    private Set<Awarers> requestAwareness = new HashSet<>();

    @JsonIgnore
    private boolean deleted;

    private String reason;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TransferRecordHistory that = (TransferRecordHistory) o;
        return deleted == that.deleted &&
                Objects.equals(id, that.id) &&
                Objects.equals(effectiveDate, that.effectiveDate) &&
                approvalStatus == that.approvalStatus &&
                Objects.equals(approvalDate, that.approvalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, effectiveDate, approvalStatus, approvalDate, deleted);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransferRecord{");
        sb.append("id=").append(id);
        sb.append(", effectiveDate=").append(effectiveDate);
        sb.append(", approvalStatus=").append(approvalStatus);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", approvalDate=").append(approvalDate);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
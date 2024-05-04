/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
public class LeaveRecord extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @ElementCollection
    @CollectionTable(name = "employee_leave_days")
    private List<Date> leaveDays = new ArrayList<>();

    @JsonProperty("leave_type")
    @NonNull
    @ManyToOne
    private LeaveType leaveType;

    private String leaveReason;

    @ManyToOne
    private Employee standIn;

    @Embedded
    private Document handoverNote;

    private String reason;

    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    @JsonProperty("authorizers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "leave_request_authorizations",
            joinColumns = @JoinColumn(name = "leave_record_id"),
            inverseJoinColumns = @JoinColumn(name = "authoriser_id"))
    private Set<Authorizers> requestAuthorization = new HashSet<>();

    @JsonProperty("awarers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "leave_request_awareness",
            joinColumns = @JoinColumn(name = "leave_record_id"),
            inverseJoinColumns = @JoinColumn(name = "awarer_id"))
    private Set<Awarers> requestAwareness = new HashSet<>();


    public LeaveRecord() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LeaveRecord that = (LeaveRecord) o;
        return Objects.equals(id, that.id) &&
                leaveType == that.leaveType &&
                Objects.equals(leaveReason, that.leaveReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, leaveType, leaveReason);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LeaveRecord{");
        sb.append("id=").append(id);
        sb.append(", leaveType=").append(leaveType);
        sb.append(", leaveDays=").append(leaveDays);
        sb.append(", leaveReason='").append(leaveReason).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}

package com.upl.nibss.hcmlib.model;

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

@Data
@Entity
@Table(name = "rotation_record")
public class RotationRecord extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("employee")
    @JoinColumn(name="employee_id")
    private Employee employee;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("new_job_role")
    @JoinColumn(name="new_job_role_id")
    private JobRole jobRole;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("old_job_role")
    @JoinColumn(name="old_job_role_id")
    private JobRole oldJobRole;

    @JsonProperty("effective_date")
    @JsonSerialize(using = JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;

    @JsonProperty("end_date")
    @JsonSerialize(using = JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @JsonProperty("approval_status")
    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @JsonProperty("approval_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("receiving_supervisor")
    @JoinColumn(name="receiving_supervisor_id")
    private Employee receivingSupervisor;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("sending_supervisor")
    @JoinColumn(name="sending_supervisor_id")
    private Employee sendingSupervisor;

//    @JsonManagedReference
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonProperty("sending_ed")
//    @JoinColumn(name="sending_ed_id")
//    private Employee sendingExecutiveDirector;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("receiving_dh")
    @JoinColumn(name="receiving_dh_id")
    private Employee receivingDepartmentHead;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("receiving_ed")
    @JoinColumn(name="receiving_ed_id")
    private Employee receivingExecutiveDirector;

    @JsonManagedReference
    @JsonProperty("new_department")
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="new_department_id")
    private Department newDepartment;

    @JsonManagedReference
    @JsonProperty("old_department")
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="old_department_id")
    private Department oldDepartment;

    private String reason;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty("authorizers_detail")
    private Set<Authorizers> requestAuthorizers = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty("awarers_detail")
    private Set<Awarers> requestAwarers = new HashSet<>();

    public RotationRecord(){
    }

    public RotationRecord(RotationRecordHistory rotationRecordHistory){
        this.setReceivingSupervisor(rotationRecordHistory.getReceivingSupervisor());
        this.setReceivingExecutiveDirector(rotationRecordHistory.getReceivingExecutiveDirector());
        this.setNewDepartment(rotationRecordHistory.getNewDepartment());
        this.setReceivingDepartmentHead(rotationRecordHistory.getReceivingDepartmentHead());
        this.setJobRole(rotationRecordHistory.getJobRole());
        this.setEmployee(rotationRecordHistory.getEmployee());
        this.setEffectiveDate(rotationRecordHistory.getEffectiveDate());
        this.setApprovalStatus(rotationRecordHistory.getApprovalStatus());
        this.setId(rotationRecordHistory.getId());
        this.setRequestAuthorizers(rotationRecordHistory.getRequestAuthorizers());
        this.setRequestAwarers(rotationRecordHistory.getRequestAwarers());
        this.setCreatedAt(rotationRecordHistory.getCreatedAt());
        this.setUpdatedAt(rotationRecordHistory.getUpdatedAt());
        this.setDeleted(rotationRecordHistory.isDeleted());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RotationRecord that = (RotationRecord) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(effectiveDate, that.effectiveDate) &&
                Objects.equals(approvalStatus, that.approvalStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, effectiveDate, approvalStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RotationRecord{");
        sb.append("id=").append(id);
        sb.append(", effectiveDate=").append(effectiveDate);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", approvalStatus=").append(approvalStatus);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", approvalDate=").append(approvalDate);
        sb.append('}');
        return sb.toString();
    }
}

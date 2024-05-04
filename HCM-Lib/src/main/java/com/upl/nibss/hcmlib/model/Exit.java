package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "exit_record")
public class Exit extends SuperModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("employee")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonProperty("eligible_for_rehire")
    private boolean eligibleForRehire;

    @JsonProperty("exit_date")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date exitDate;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "exit")
    private Set<ExitResponse> exitResponses;

    @JsonProperty("approval_status")
    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @JsonProperty("approval_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonProperty("authorizers_detail")
    private Set<Authorizers> requestAuthorizers = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonProperty("awarers_detail")
    private Set<Awarers> requestAwarers = new HashSet<>();

    private String reason;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Exit exit = (Exit) o;
        return eligibleForRehire == exit.eligibleForRehire &&
                Objects.equals(id, exit.id) &&
                Objects.equals(exitDate, exit.exitDate) &&
                approvalStatus == exit.approvalStatus &&
                Objects.equals(approvalDate, exit.approvalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, eligibleForRehire, exitDate, approvalStatus, approvalDate);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.enums.GenderType;
import com.upl.nibss.hcmlib.enums.MaritalType;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
public class RecruitmentRecordHistory extends SuperModel implements Serializable {


    @Id
    @JsonProperty(Constants.ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("number_of_opening")
    public int numberOfOpening;

    @JsonProperty("job_description")
    public String jobDescription;

    @JsonProperty("min_grade_range")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "min_grade_level_id")
    public GradeLevel minGradeLevel;

    @JsonProperty("max_grade_range")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "max_grade_level_id")
    public GradeLevel maxGradeLevel;

    @JsonProperty("min_years_experience")
    public int minYearsExperience;

//    @JsonProperty(Constants.START_DATE)
//    public Date startDate;
//
//    @JsonProperty(Constants.END_DATE)
//    public Date endDate;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = JobRole.MODEL_KEY_ID, referencedColumnName = JobRole.MODEL_KEY_ID)
    private JobRole jobRole;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", referencedColumnName = Constants.ID)
    private Employee supervisor;

    @JsonProperty("approval_status")
    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.APPROVED;

    @JsonProperty("approval_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    @JsonProperty("authorizers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "recruitment_history_request_authorizations",
            joinColumns = @JoinColumn(name = "recruitment_record_history_id"),
            inverseJoinColumns = @JoinColumn(name = "authoriser_id"))
    private Set<Authorizers> requestAuthorization = new HashSet<>();

    @JsonProperty("awarers_detail")
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "recruitment_history_request_awareness",
            joinColumns = @JoinColumn(name = "recruitment_record_history_id"),
            inverseJoinColumns = @JoinColumn(name = "awarer_id"))
    private Set<Awarers> requestAwareness = new HashSet<>();

    private String reason;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RecruitmentRecordHistory that = (RecruitmentRecordHistory) o;
        return numberOfOpening == that.numberOfOpening &&
                minYearsExperience == that.minYearsExperience &&
                Objects.equals(id, that.id) &&
                Objects.equals(jobDescription, that.jobDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, numberOfOpening, jobDescription, minYearsExperience);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecruitmentRecordHistory{");
        sb.append("id=").append(id);
        sb.append(", numberOfOpening=").append(numberOfOpening);
        sb.append(", jobDescription='").append(jobDescription).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", minYearsExperience=").append(minYearsExperience);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}

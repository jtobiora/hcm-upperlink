package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.*;
import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Created by toyin.oladele on 30/10/2017.
 */
@Data
@Entity
@Table(name = "JOB_ROLES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobRole extends SuperModel implements Serializable {

    public static final String MODEL_KEY_ID = "job_role_id";

    @JsonProperty(Constants.ID)
    @Id
    @Column(name = MODEL_KEY_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobRoleId;

    @Column(nullable = false, length = 200, unique = true)
    private String jobTitle;

    @Lob
    private String jobDescription;

    @Embedded
    private Document jobDescDoc;

    private Long parentJobRole;

//    @JsonManagedReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private Department department;

    @JsonBackReference
    @OneToMany(mappedBy = "jobRole")
    private Set<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "JOB_ROLE_GRADES",
            joinColumns = @JoinColumn(name = "job_role_id", referencedColumnName = "job_role_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id", referencedColumnName = "grade_id"))
    private Set<GradeLevel> gradeList;

    private boolean activated;

    public JobRole() {
    }

    public JobRole(Long jobRoleId,String jobTitle) {
        this.jobRoleId = jobRoleId;
        this.jobTitle = jobTitle;
    }

    public JobRole(Long jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JobRole jobRole = (JobRole) o;
        return activated == jobRole.activated &&
                Objects.equals(jobRoleId, jobRole.jobRoleId) &&
                Objects.equals(createdAt, jobRole.createdAt) &&
                Objects.equals(updatedAt, jobRole.updatedAt) &&
                Objects.equals(jobTitle, jobRole.jobTitle) &&
                Objects.equals(jobDescription, jobRole.jobDescription) &&
                Objects.equals(jobDescDoc, jobRole.jobDescDoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jobRoleId, createdAt, updatedAt, jobTitle, jobDescription, jobDescDoc, activated);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JobRole{");
        sb.append("now=").append(LocalDateTime.now());
        sb.append(", jobRoleId=").append(jobRoleId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", jobTitle='").append(jobTitle).append('\'');
        sb.append(", jobDescription='").append(jobDescription).append('\'');
        sb.append(", jobDescDoc=").append(jobDescDoc);
        sb.append(", activated=").append(activated);
        sb.append('}');
        return sb.toString();
    }
}

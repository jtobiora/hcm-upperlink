package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.EmployeeEmbeddable;
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
 * Created by toyin.oladele on 23/11/2017.
 */
@Data
@Entity
public class PromotionRecord extends SuperModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

//    @JsonProperty("employee")
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "id", column = @Column(name = "employee_id")),
//            @AttributeOverride(name = "", column = @Column(name = "employee_title"))
//    })
//    private EmployeeEmbeddable employee;

    @JsonProperty("old_job_role")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "old_job_role_id", nullable = false)
    private JobRole oldJobRole;

    @JsonProperty("new_job_role")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "new_job_role_id", nullable = false)
    private JobRole newJobRole;

    @JsonProperty("resumption_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date resumptionDate;

    @JsonProperty("effective_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date effectiveDate;

    @JsonProperty("last_promotion_date")
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date lastPromotionDate;

    @JsonProperty("old_grade_level")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "old_grade_level_id", nullable = false)
    private GradeLevel oldGradeLevel;

    @JsonProperty("new_grade_level")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "new_grade_level_id", nullable = false)
    private GradeLevel newGradeLevel;

//    @JsonIgnore
//    @JsonManagedReference
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "promotion_record_allowances",
//            joinColumns = @JoinColumn(name = "promotion_record_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "allowance_id", referencedColumnName = "id"))
//    private Set<Allowance> allowances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PromotionRecord that = (PromotionRecord) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(effectiveDate, that.effectiveDate) &&
                Objects.equals(lastPromotionDate, that.lastPromotionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, effectiveDate, lastPromotionDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PromotionRecord{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", effectiveDate=").append(effectiveDate);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", lastPromotionDate=").append(lastPromotionDate);
        sb.append('}');
        return sb.toString();
    }
}

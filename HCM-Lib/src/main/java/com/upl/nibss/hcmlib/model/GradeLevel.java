package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * Created by toyin.oladele on 30/10/2017.
 */
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"grade","gradeName"}) )
public class GradeLevel extends SuperModel implements Serializable{

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "grade_id")
    private Long gradeId;

    private Integer grade;

    @JsonProperty("grade_name")
    private String gradeName;

    @Column
    private BigDecimal salary;

    @JsonBackReference
    @ManyToMany()
    @JoinTable(name = "grade_level_allowance",
                joinColumns = @JoinColumn(name = "grade_level_id", referencedColumnName = "grade_id"),
                inverseJoinColumns = @JoinColumn(name = "allowance_id",referencedColumnName = "id"))
    private Set<Allowance> allowances;

    public GradeLevel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GradeLevel that = (GradeLevel) o;
        return Objects.equals(gradeId, that.gradeId) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(gradeName, that.gradeName) &&
                Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gradeId, grade, gradeName, salary);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GradeLevelEmbeddable{");
        sb.append("gradeId=").append(gradeId);
        sb.append(", grade=").append(grade);
        sb.append(", gradeName='").append(gradeName).append('\'');
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }
}

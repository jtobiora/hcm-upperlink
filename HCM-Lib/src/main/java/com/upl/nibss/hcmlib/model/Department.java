/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "departments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department extends SuperModel implements Serializable {

    private static final String DEPARTMENT_NAME = "name";
    private static final String LOCATION = "location";
    private static final String DEPARTMENT_HEAD = "department_head";
    private static final String CATEGORY = "category";
    private static final String PARENT_DEPARTMENT = "parent_department";
    private static final String CHILD_DEPARTMENT = "child_department";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(DEPARTMENT_NAME)
    @Column(name = "name", nullable = false, unique = true)
    private String deptName;

    @Column(name = "description")
    private String description;

    @JsonManagedReference
    @JsonProperty(LOCATION)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location locationId;

    @JsonManagedReference
    @JsonProperty(DEPARTMENT_HEAD)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_head")
    private Employee deptHead;

    @JsonManagedReference
    @JsonProperty(CATEGORY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "category_id")
    private Category deptCategory;

    @JsonProperty("max_number_of_person_on_leave_per_day")
    private int maxNumberOfPersonOnLeavePerDay = 1;

    @JsonProperty(PARENT_DEPARTMENT)
    private Long parentDepartment;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<JobRole> jobRoles;

    public Department() {
    }

    public Department(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, deptName, createdAt, updatedAt, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("id=").append(id);
        sb.append(", deptName='").append(deptName).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", description='").append(description).append('\'');
        sb.append(", deptCategory=").append(deptCategory);
        sb.append('}');
        return sb.toString();
    }
}

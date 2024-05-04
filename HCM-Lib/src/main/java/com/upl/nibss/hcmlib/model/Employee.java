/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.*;
import com.upl.nibss.hcmlib.enums.ConfirmationStatus;
import com.upl.nibss.hcmlib.enums.LoanEligibility;
import com.upl.nibss.hcmlib.enums.LeaveStatus;
import com.upl.nibss.hcmlib.enums.StaffType;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Gbenga
 */
@Data
@Audited
@Entity
@Table(name = "EMPLOYEES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "EMPLOYEE_NO", unique = true, nullable = false)
    private String employeeNo;

    @Embedded
    private NameType empName;

    @Column(unique = true)
    private String email;

    @Embedded
    private Person employeePersonDetail;

    @NotAudited
    @ElementCollection(targetClass = Address.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "Employee_addresses", joinColumns = @JoinColumn(name = "Employee_id"))
    @OrderBy("priority DESC")
    private Set<Address> addresses;

    @NotAudited
    @ElementCollection(targetClass = PhoneNo.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "Employee_PhoneList", joinColumns = @JoinColumn(name = "Employee_id"))
    @OrderBy("priority DESC")
    private Set<PhoneNo> phoneList;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "EXPECTED_RESUMPTION_DATE")
    private Date expectedResumptionDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "ACTUAL_RESUMPTION_DATE")
    private Date actualResumptionDate;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "EXPECTED_CONFIRMATION_DATE", nullable = false)
    private Date expectedConfirmationDate;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "CONFIRMATION_DATE")
    private Date confirmationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConfirmationStatus confirmationStatus = ConfirmationStatus.ON_PROBATION;

    @Enumerated(EnumType.STRING)
    @Column(name = "LEAVE_STATUS", nullable = false)
    private LeaveStatus leaveStatus = LeaveStatus.NOT_APPROVED;

    @Enumerated(EnumType.STRING)
    private LoanEligibility loanEligibility = LoanEligibility.NOT_ELIGIBLE;

    @NotAudited
    @JsonManagedReference
    @ManyToOne(targetEntity = JobRole.class, optional = false)
    @JoinColumn(name = "JOB_ROLE_ID", nullable = false)
    private JobRole jobRole;

    @Column(name = "SALARY")
    private BigDecimal salary;

//    @JsonIgnore
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
//    private Department department;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id",column = @Column(name = "department_id")),
            @AttributeOverride(name = "name", column = @Column(name = "department_name"))
    })
    private DepartmentEmbeddable department;

    @Enumerated(EnumType.STRING)
    @Column(name = "EMPLOYEE_TYPE", nullable = false)
    private StaffType employeeType;

//    @JsonIgnore
//    @OneToOne(targetEntity = EmployeeDetails.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "EMPLOYEE_DETAILS")
//    private EmployeeDetails employeeDetails;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERVISOR_ID")
    private Employee employeeSupervisor;

    @NotAudited
    @JsonBackReference
    @OneToMany(mappedBy = "employeeSupervisor")
    private Set<Employee> employeeSupervisee;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECOND_LEVEL_SUPERVISOR_ID")
    private Employee employeeSecondLevelSupervisor;

    @NotAudited
    @JsonBackReference
    @OneToMany(mappedBy = "employeeSecondLevelSupervisor")
    private Set<Employee> employeeSecondLevelSupervisee;

    @NotAudited
    @ManyToOne
    @JoinColumn(name = "GRADE")
    private GradeLevel gradeLevel;

    @NotAudited
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Allowance> allowances;

    private boolean activated;

    private boolean credentials_approved;

    private boolean supervisor;

    private boolean hcm;

    @Lob
    private String updateReason;

    @Lob
    private String disableReason;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee that = (Employee) o;
        return activated == that.activated &&
                Objects.equals(id, that.id) &&
                Objects.equals(employeeNo, that.employeeNo) &&
                Objects.equals(empName, that.empName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(employeePersonDetail, that.employeePersonDetail) &&
                Objects.equals(addresses, that.addresses) &&
                Objects.equals(phoneList, that.phoneList) &&
                Objects.equals(expectedResumptionDate, that.expectedResumptionDate) &&
                Objects.equals(actualResumptionDate, that.actualResumptionDate) &&
                Objects.equals(salary, that.salary) &&
                employeeType == that.employeeType &&
                Objects.equals(gradeLevel, that.gradeLevel) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(createdAt, that.createdAt);
//                Objects.equals(updatedBy, that.updatedBy) &&
//                Objects.equals(createdBy, that.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, employeeNo, empName, email, employeePersonDetail, addresses, phoneList, expectedResumptionDate, actualResumptionDate, salary, employeeType, gradeLevel, updatedAt, createdAt, activated);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", employeeNo='").append(employeeNo).append('\'');
        sb.append(", empName=").append(empName);
        sb.append(", email='").append(email).append('\'');
        sb.append(", employeePersonDetail=").append(employeePersonDetail);
        sb.append(", addresses=").append(addresses);
        sb.append(", phoneList=").append(phoneList);
        sb.append(", expectedResumptionDate=").append(expectedResumptionDate);
        sb.append(", actualResumptionDate=").append(actualResumptionDate);
        sb.append(", salary=").append(salary);
        sb.append(", employeeType=").append(employeeType);
        sb.append(", gradeLevel=").append(gradeLevel);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
//        sb.append(", updatedBy=").append(updatedBy);
//        sb.append(", createdBy=").append(createdBy);
        sb.append(", activated=").append(activated);
        sb.append('}');
        return sb.toString();
    }
}

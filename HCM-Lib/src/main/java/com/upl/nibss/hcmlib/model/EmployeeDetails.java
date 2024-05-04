/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.*;
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
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Embedded
    private OptionalEmployeeDetails optionalEmployeeDetail = new OptionalEmployeeDetails();

    @Embedded
    private MandatoryEmployeeDetails mandatoryEmployeeDetail = new MandatoryEmployeeDetails();

    @Embedded
    private FinanceDetails financeDetail = new FinanceDetails();

    @Embedded
    private Image employeePhoto = new Image();

    @Embedded
    private PensionInfo pensionInfo = new PensionInfo();

    @Embedded
    private TaxInfo taxInfo = new TaxInfo();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employeeId;

//    @Column(name = "ELIGIBILITY_COMMENT")
//    private String eligibilityComment;

    //which loanType interest he/she is eligible to acquire.
//    @ManyToMany
//    private Set<LoanType> loanTypes = new HashSet<>();

    @ElementCollection(targetClass = LoanTypeNextUse.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "employee_details_loan_types")
    private Set<LoanTypeNextUse> loanTypes = new HashSet<>();

    public EmployeeDetails() {
    }

    public static EmployeeDetails sanitize(EmployeeDetails employeeDetails){
//        if(employeeDetails == null){
//            employeeDetails = new EmployeeDetails();
//            if(employeeDetails.getMandatoryEmployeeDetail() == null){
//                employeeDetails.setMandatoryEmployeeDetail(new MandatoryEmployeeDetails());
//                if (employeeDetails.getMandatoryEmployeeDetail().getEmergencyContactId() == null){
//                    employeeDetails.getMandatoryEmployeeDetail().setEmergencyContactId(new PersonDetails());
//                }
//                if (employeeDetails.getMandatoryEmployeeDetail().getNextOfKinId() == null){
//                    employeeDetails.getMandatoryEmployeeDetail().setNextOfKinId(new PersonDetails());
//                }
//                if (employeeDetails.getMandatoryEmployeeDetail().getNextOfKinId().getPersonAddress() == null){
//                    employeeDetails.getMandatoryEmployeeDetail().getNextOfKinId().setPersonAddress(new Address());
//                }
//                if (employeeDetails.getMandatoryEmployeeDetail().getQualifications() == null){
//                    employeeDetails.getMandatoryEmployeeDetail().setQualifications(new HashSet<>());
//                }
//                if (employeeDetails.getMandatoryEmployeeDetail().getRefereeList() == null){
//                    employeeDetails.getMandatoryEmployeeDetail().setRefereeList(new HashSet<>());
//                }
//            }
//            if(employeeDetails.getOptionalEmployeeDetail() == null){
//                employeeDetails.setOptionalEmployeeDetail(new OptionalEmployeeDetails());
//            }
//            if (employeeDetails.getOptionalEmployeeDetail().getFamilyDetailsList() == null){
//                employeeDetails.getOptionalEmployeeDetail().setFamilyDetailsList(new HashSet<>());
//            }
//
//            if(employeeDetails.getFinanceDetail() == null){
//                employeeDetails.setFinanceDetail(new FinanceDetails());
//            }
//
//            if (employeeDetails.getPensionInfo() == null){
//                employeeDetails.setPensionInfo(new PensionInfo());
//            }
//
//            if (employeeDetails.getTaxInfo() == null){
//                employeeDetails.setTaxInfo(new TaxInfo());
//            }
//
//            employeeDetails.getMandatoryEmployeeDetail().setEmergencyContactId(new PersonDetails());
//        }else {
//            if(employeeDetails.getMandatoryEmployeeDetail() == null){
//                employeeDetails.setMandatoryEmployeeDetail(new MandatoryEmployeeDetails());
//            }
//            if(employeeDetails.getOptionalEmployeeDetail() == null){
//                employeeDetails.setOptionalEmployeeDetail(new OptionalEmployeeDetails());
//            }
//            if(employeeDetails.getFinanceDetail() == null){
//                employeeDetails.setFinanceDetail(new FinanceDetails());
//            }
//
//            if (employeeDetails.getPensionInfo() == null){
//                employeeDetails.setPensionInfo(new PensionInfo());
//            }
//
//            if (employeeDetails.getTaxInfo() == null){
//                employeeDetails.setTaxInfo(new TaxInfo());
//            }
//        }

        return employeeDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EmployeeDetails that = (EmployeeDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(optionalEmployeeDetail, that.optionalEmployeeDetail) &&
                Objects.equals(mandatoryEmployeeDetail, that.mandatoryEmployeeDetail) &&
                Objects.equals(financeDetail, that.financeDetail) &&
                Objects.equals(employeePhoto, that.employeePhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, optionalEmployeeDetail, mandatoryEmployeeDetail, financeDetail, employeePhoto);
    }
}

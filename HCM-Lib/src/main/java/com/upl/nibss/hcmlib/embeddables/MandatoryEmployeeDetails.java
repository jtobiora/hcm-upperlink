/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.upl.nibss.hcmlib.model.PersonDetails;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class MandatoryEmployeeDetails implements Serializable {

    @ElementCollection(targetClass = QualificationDetails.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "EMPLOYEE_QUALIFICATIONS", joinColumns = @JoinColumn(name = "Employee_id"))
    private Set<QualificationDetails> qualifications;

    @ElementCollection(targetClass = RefereeDetails.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "REFEREES", joinColumns = @JoinColumn(name = "Employee_id"))
    private Set<RefereeDetails> refereeList = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private PersonDetails nextOfKinId = new PersonDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private PersonDetails emergencyContactId = new PersonDetails();

    public MandatoryEmployeeDetails() {
    }

}

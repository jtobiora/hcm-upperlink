/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.upl.nibss.hcmlib.embeddables.Address;
import com.upl.nibss.hcmlib.embeddables.NameType;
import com.upl.nibss.hcmlib.embeddables.PhoneNo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
public class PersonDetails extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private NameType personName = new NameType();

    private String personEmail;

    @Embedded
    private PhoneNo personPhoneNo = new PhoneNo();

    @JsonBackReference
    @Embedded
    private Address personAddress = new Address();

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "personDetails")
    private Set<EducationDetail> educationDetails;

    public PersonDetails() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonDetails that = (PersonDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personName, that.personName) &&
                Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(personPhoneNo, that.personPhoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, personName, personEmail, personPhoneNo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDetails{");
        sb.append("id=").append(id);
        sb.append(", personName=").append(personName);
        sb.append(", personEmail='").append(personEmail).append('\'');
        sb.append(", personPhoneNo=").append(personPhoneNo);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}

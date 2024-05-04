/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class OptionalEmployeeDetails implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date marriageDate;

    @JsonManagedReference
    @ElementCollection(targetClass = FamilyDetails.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "FAMILY_DETAILS", joinColumns = @JoinColumn(name = "Employee_id"))
    private Set<FamilyDetails> familyDetailsList = new HashSet<>();

    public OptionalEmployeeDetails() {
    }

}

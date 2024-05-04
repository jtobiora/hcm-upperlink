/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upl.nibss.hcmlib.enums.RelationShipType;
import com.upl.nibss.hcmlib.model.PersonDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Gbenga
 */
@Data
@AllArgsConstructor
@Embeddable
public class FamilyDetails implements Serializable {

    /*@OneToOne
    @JoinColumn
    private PersonDetails memberDetails;
    */

    @Embedded
    private NameType name = new NameType();

    @Column(name = "RELATIONSHIP_TYPE", length = 30)
    @Enumerated(EnumType.STRING)
    private RelationShipType relationship;

    @Embedded
    @JsonIgnore
    private Image memberPhoto = new Image();

    public FamilyDetails() {
    }

}

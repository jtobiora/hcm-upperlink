/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.upl.nibss.hcmlib.enums.ProRefereeType;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class RefereeDetails implements Serializable {

    @Embedded
    private NameType refereeName = new NameType();

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name = "PHONE_NO", length = 25, nullable = false)
    private String phoneNo;

    private String jobRole;

    private String companyName;

    @Enumerated(EnumType.STRING)
    private ProRefereeType relationship;

    public RefereeDetails() {
    }
}

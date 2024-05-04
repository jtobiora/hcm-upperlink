/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.GenderType;
import com.upl.nibss.hcmlib.enums.MaritalType;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gbenga
 */
@Data
@AllArgsConstructor
@Embeddable
public class Person implements Serializable {

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "MARITAL_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private MaritalType maritalStatus;

    @Column(name = "PERSONAL_DESC", length = 500)
    private String personalDesc;

    public Person() {
    }

}

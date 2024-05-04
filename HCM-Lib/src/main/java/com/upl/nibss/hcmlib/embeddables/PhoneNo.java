/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.PhoneNoType;
import com.upl.nibss.hcmlib.model.Country;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class PhoneNo implements Serializable {

    @JsonIgnore
    @Column(name = "PHONE_NO_ID")
    private String phoneNoId;

    @JsonProperty("phone_number")
    @Column(name = "PHONE_NO", length = 25)
    private String phoneNo;

    @Column(name = "PHONE_NO_TYPE", length = 30)
    @Enumerated(EnumType.STRING)
    private PhoneNoType phoneNoType;

    @Column(name = "PHONE_NO_PRIORITY")
    private int priority;

    @Column(length = 30, name = "PHONE_NO_STATUS")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "PHONE_NO_DATE_STORED")
    private Date dateStored;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "PHONE_NO_DATE_UPDATED")
    private Date dateUpdated;

    @Column(length = 500, name = "PHONE_NO_DESCRIPTION")
    private String description;

    public PhoneNo() {
    }
}

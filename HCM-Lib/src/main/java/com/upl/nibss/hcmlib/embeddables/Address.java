/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.enums.StatusType;
import com.upl.nibss.hcmlib.model.Country;
import com.upl.nibss.hcmlib.model.State;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class Address implements Serializable {

    @JsonProperty(Constants.ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADDR_ID")
    private String addrId;

    @Column(name = "STREET_ADDR1", length = 100)
    private String streetAddr1;

    @Column(name = "STREET_ADDR2", length = 100)
    private String streetAddr2;

    @Column(name = "POSTAL_CODE", length = 15)
    private String postalCode;

    @NotAudited
    @JsonManagedReference
    @JoinColumn(name = "COUNTRY_REF")
    @OneToOne
    private Country countryRef = new Country(Long.valueOf("18"));

    @NotAudited
    @JsonManagedReference
    @JoinColumn(name = "STATE_PROV_REF")
    @OneToOne
    private State stateProRef = new State(Long.valueOf("1"));

    @Column(name = "POSTAL_CITY", length = 100)
    private String postalCity;

    @Column(name = "PRIORITY")
    private int priority = 0;

    @Column(name = "STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "ADDR_DATE_STORED")
    private Date dateStored;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "ADDR_DATE_UPDATED")
    private Date dateUpdated;

    @Column(length = 500, name = "ADDR_DESCRIPTION")
    private String description;

    public Address() {
    }

    public Address(String streetAddr1, String streetAddr2, String postalCode, String postalCity, int priority, StatusType status, String description) {
        this.streetAddr1 = streetAddr1;
        this.streetAddr2 = streetAddr2;
        this.postalCode = postalCode;
        this.postalCity = postalCity;
        this.priority = priority;
        this.status = status;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return priority == address.priority &&
                Objects.equals(addrId, address.addrId) &&
                Objects.equals(streetAddr1, address.streetAddr1) &&
                Objects.equals(streetAddr2, address.streetAddr2) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(postalCity, address.postalCity) &&
                status == address.status &&
                Objects.equals(dateStored, address.dateStored) &&
                Objects.equals(dateUpdated, address.dateUpdated) &&
                Objects.equals(description, address.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addrId, streetAddr1, streetAddr2, postalCode, postalCity, priority, status, dateStored, dateUpdated, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("addrId='").append(addrId).append('\'');
        sb.append(", streetAddr1='").append(streetAddr1).append('\'');
        sb.append(", streetAddr2='").append(streetAddr2).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", postalCity='").append(postalCity).append('\'');
        sb.append(", priority=").append(priority);
        sb.append(", status=").append(status);
        sb.append(", dateStored=").append(dateStored);
        sb.append(", dateUpdated=").append(dateUpdated);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

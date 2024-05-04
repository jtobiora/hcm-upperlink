/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "country")
public class Country implements Serializable {

    public static final String COUNTRY_CODE = "country_code";
    public static final String CAPITAL_CITY = "capital_city";

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private Long countryId;

    @JsonProperty(COUNTRY_CODE)
    @Column(name = "country_code", nullable = false)
    private int countryCode;

    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String country;

    @JsonProperty(CAPITAL_CITY)
    @Column(name = "capital_city", length = 50)
    private String capitalCity;

    @JsonIgnore
    @JoinColumn(name = "region_id")
    @ManyToOne(optional = false)
    private Region region;

    @JsonBackReference
    @Column(name = "STATE_PROVINCES", nullable = false)
    @OneToMany(mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("stateProvince ASC")
    private List<State> stateProvList;

    @Column(length = 500)
    private String description;

    public Country() {
    }

    public Country(Long countryId){
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Country country1 = (Country) o;
        return countryCode == country1.countryCode &&
                Objects.equals(countryId, country1.countryId) &&
                Objects.equals(country, country1.country) &&
                Objects.equals(capitalCity, country1.capitalCity) &&
                Objects.equals(description, country1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countryId, countryCode, country, capitalCity, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("countryId=").append(countryId);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", country='").append(country).append('\'');
        sb.append(", capitalCity='").append(capitalCity).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

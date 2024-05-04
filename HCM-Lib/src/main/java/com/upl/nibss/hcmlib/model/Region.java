/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
public class Region extends SuperModel implements Serializable {

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REGION_ID")
    private Long regionId;

    @Column(name = "REGION_NAME", nullable = false, length = 35)
    private String region;

    @Column(name = "REGION_CODE", nullable = false, length = 2)
    private String regionCode;

    @JsonIgnore
    @Column(name = "COUNTRY_ID")
    @OneToMany(mappedBy = "region", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("country ASC")
    private List<Country> countryList;

    @Column(length = 500)
    private String description;

    public Region() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Region region = (Region) o;
        return Objects.equals(regionId, region.regionId) &&
                Objects.equals(this.region, region.region) &&
                Objects.equals(regionCode, region.regionCode) &&
                Objects.equals(description, region.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), regionId, region, regionCode, description);
    }
}

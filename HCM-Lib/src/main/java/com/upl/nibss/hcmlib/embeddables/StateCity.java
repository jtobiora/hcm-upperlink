/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class StateCity implements Serializable {

    @Column(name = "CITY_CODE")
    private String cityCode;

    @Column(name = "CITY_NAME", length = 200, nullable = false)
    private String cityName;

    // @Max(value=?)  @Min(value=?)
    //if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CITY_LONGITUDE", precision = 5, scale = 2)
    private double cityLongitude;

    @Column(name = "CITY_LATITUDE", precision = 5, scale = 2)
    private double cityLatitude;

    public StateCity() {
    }

}

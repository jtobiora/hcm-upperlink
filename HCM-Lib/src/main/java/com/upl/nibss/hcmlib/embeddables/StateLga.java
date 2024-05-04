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
public class StateLga implements Serializable {

    @Column(name = "LGA_ID", nullable = false)
    private String lgaId;

    @Column(name = "LGA_NAME", length = 200, nullable = false)
    private String lgaName;

    // @Max(value=?)  @Min(value=?)
    //if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LGA_LONGITUDE", precision = 10, scale = 2)
    private double lgaLongitude;

    @Column(name = "LGA_LATITUDE", precision = 10, scale = 2)
    private double lgaLatitude;

    public StateLga() {
    }
}

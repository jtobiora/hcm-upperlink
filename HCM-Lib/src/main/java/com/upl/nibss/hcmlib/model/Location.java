package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.embeddables.Address;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by toyin.oladele on 30/10/2017.
 */
@Data
@Entity
@Table(name = "LOCATIONS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location extends SuperModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Module_Name", nullable = false, length = 100)
    private String locationName;

    @JsonManagedReference
    @Embedded
    private Address address;

    @Column(name = "SHORT_DESC", length = 200)
    private String shortDesc;

    public Location() {
    }
}

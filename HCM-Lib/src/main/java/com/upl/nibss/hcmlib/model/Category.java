package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by toyin.oladele on 30/10/2017.
 * Formally Known as DepartmentTypes
 */
@Data
@Entity
public class Category extends SuperModel implements Serializable {

    private static final String DEPARTMENT_TYPE = "department_type";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(DEPARTMENT_TYPE)
    @Column(nullable = false, unique = true)
    private String deptType;

    @Column(nullable = false, unique = true)
    private int hierarchy = 0;

    private boolean activated = true;

}

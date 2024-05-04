package com.upl.nibss.hcmlib.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Data
@Entity
@Table(name = "banks")
public class Bank extends SuperModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

}

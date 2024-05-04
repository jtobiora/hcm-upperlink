package com.upl.nibss.hcmlib.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by toyin.oladele on 30/10/2017.
 */
@Data
@Entity
public class Settings extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Lob
    private String value;

    public Settings() {
    }

    public Settings(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

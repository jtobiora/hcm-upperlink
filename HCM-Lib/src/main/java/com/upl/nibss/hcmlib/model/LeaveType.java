package com.upl.nibss.hcmlib.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by stanlee on 16/01/2018.
 */
@Data
@Entity
@Table(name = "leave_type")
@NoArgsConstructor
public class LeaveType extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    private boolean activated = true;

    public LeaveType(String name) {
        this.name = name;
    }

    public LeaveType(Long id) {
        this.id = id;
    }
}

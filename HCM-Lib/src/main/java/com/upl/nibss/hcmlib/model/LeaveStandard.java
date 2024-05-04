/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Gbenga
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "LEAVE_STANDARDS", uniqueConstraints = @UniqueConstraint(columnNames = {"grade_level_id","leave_type_id"}))
public class LeaveStandard extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("grade_level")
    @ManyToOne
    @JoinColumn(name = "grade_level_id", referencedColumnName = "grade_id")
    private GradeLevel gradeLevel;

    @JsonProperty("leave_type")
    @NonNull
    @ManyToOne
    @JoinColumn(name = "leave_type_id", referencedColumnName = "id")
    private LeaveType leaveType;

    @JsonProperty("leave_days")
    private int leaveDays = 0;

    private boolean activated = true;

}

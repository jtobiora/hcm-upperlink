/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "STATE")
public class State extends SuperModel implements Serializable {

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id", nullable = false)
    private Long stateId;

    @Basic(optional = false)
    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Column(name = "start_code", length = 3)
    private String stateCode;

    @JsonManagedReference
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    @ManyToOne(optional = false)
    private Country country;

    @Column(length = 500)
    private String description;

    public State() {
    }

    public State(Long stateId){
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        State state1 = (State) o;
        return Objects.equals(stateId, state1.stateId) &&
                Objects.equals(state, state1.state) &&
                Objects.equals(stateCode, state1.stateCode) &&
                Objects.equals(description, state1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stateId, state, stateCode, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("State{");
        sb.append("stateId=").append(stateId);
        sb.append(", state='").append(state).append('\'');
        sb.append(", stateCode='").append(stateCode).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", description='").append(description).append('\'');
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}

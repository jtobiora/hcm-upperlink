/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

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
@Table(name = "auth_profile")
public class AuthProfile extends SuperModel implements Serializable {

    private static final String PARAMETER_NAME = "parameter_name";
    private static final String PARAMETER_VAL = "parameter_val";

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parameter_id")
    private Long parameterId;

    @JsonProperty(PARAMETER_NAME)
    @Column(name = "parameter_name", nullable = false, length = 30)
    private String parameterName;

    @JsonProperty(PARAMETER_VAL)
    @Column(name = "parameter_val", nullable = false, precision = 10, scale = 2)
    private double parameterVal;

    @JsonProperty(Constants.DESCRIPTION)
    @Column(nullable = false, length = 200)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuthProfile that = (AuthProfile) o;
        return Double.compare(that.parameterVal, parameterVal) == 0 &&
                Objects.equals(parameterId, that.parameterId) &&
                Objects.equals(parameterName, that.parameterName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parameterId, parameterName, parameterVal, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthProfile{");
        sb.append("parameterId=").append(parameterId);
        sb.append(", parameterName='").append(parameterName).append('\'');
        sb.append(", parameterVal=").append(parameterVal);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", description='").append(description).append('\'');
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}

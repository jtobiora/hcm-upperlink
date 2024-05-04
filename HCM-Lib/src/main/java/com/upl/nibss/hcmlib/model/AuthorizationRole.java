/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.enums.RotationCategory;
import com.upl.nibss.hcmlib.enums.SupervisorCategory;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "authorization_roles")
public class AuthorizationRole extends SuperModel implements Serializable {

    public static final String JOB_ROLE = "job_role";
    public static final String HIERARCHY = "hierarchy";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(Constants.ID)
    @Column(name = "auth_role_id")
    private Long authRoleId;

    @JsonProperty(JOB_ROLE)
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_role_id", referencedColumnName = "job_role_id")
    private JobRole jobRole;

    @JsonProperty(AwarenessRole.SUPERVISOR_CATEGORY)
    @Enumerated(EnumType.STRING)
    private SupervisorCategory supervisorCategory = SupervisorCategory.NONE;

    @JsonProperty("rotation_category")
    @Enumerated(EnumType.STRING)
    private RotationCategory rotationCategory = RotationCategory.NONE;

    @JsonProperty(HIERARCHY)
    @Column(name = "order_value", nullable = false)
    private int order_value = 0;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "portal_module_id", nullable = false)
    private PortalModule portalModule;

    private boolean activated;

    @JsonProperty("job_role_leave_authorizer")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_role_leave_authorizer", referencedColumnName = "job_role_id")
    private JobRole jobRoleleaveAuthorizer;

    @JsonProperty("department_training_authorizer")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_training_authorizer", referencedColumnName = "id")
    private Department departmentTrainingAuthorizer;

    public AuthorizationRole() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuthorizationRole that = (AuthorizationRole) o;
        return activated == that.activated &&
                Objects.equals(authRoleId, that.authRoleId) &&
                supervisorCategory == that.supervisorCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authRoleId, supervisorCategory, activated);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorizationRole{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", authRoleId=").append(authRoleId);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", supervisorCategory=").append(supervisorCategory);
        sb.append(", activated=").append(activated);
        sb.append('}');
        return sb.toString();
    }
}

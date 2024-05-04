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

@Data
@Entity
@Table(name = "awareness_role")
public class AwarenessRole extends SuperModel implements Serializable{

    public static final String JOB_ROLE = "job_role";
    public static final String SUPERVISOR_CATEGORY = "supervisor_category";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(Constants.ID)
    @Column(name = "awareness_role_id")
    private Long awareRoleId;

    @JsonProperty(JOB_ROLE)
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_role_id", nullable = false, referencedColumnName = "job_role_id")
    private JobRole jobRole;

    @JsonProperty(SUPERVISOR_CATEGORY)
    @Enumerated(EnumType.STRING)
    private SupervisorCategory supervisorCategory = SupervisorCategory.NONE;

    @JsonProperty("rotation_category")
    @Enumerated(EnumType.STRING)
    private RotationCategory rotationCategory = RotationCategory.NONE;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
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

    public AwarenessRole() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AwarenessRole that = (AwarenessRole) o;
        return Objects.equals(awareRoleId, that.awareRoleId) &&
                supervisorCategory == that.supervisorCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), awareRoleId, supervisorCategory);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AwarenessRole{");
        sb.append("awareRoleId=").append(awareRoleId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", supervisorCategory=").append(supervisorCategory);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}

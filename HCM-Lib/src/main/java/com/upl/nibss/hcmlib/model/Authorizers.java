/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
public class Authorizers extends SuperModel implements Serializable {

    private static final String AUTHORIZATION_ROLE = "authorization_role";
    private static final String NOTIFICATION_DATE = "notification_date";
    private static final String AUTHORIZATION_DATE = "authorization_date";
    private static final String AUTHORIZATION_COMMENT = "authorization_comment";
    private static final String APPROVAL_STATUS = "approval_status";
    private static final String APPROVED_BY = "approved_by";

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(AUTHORIZATION_ROLE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorization_role", nullable = false)
    private AuthorizationRole roles;

    @JsonProperty(NOTIFICATION_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "notification_date")
    private Date notificationDate;

    @JsonProperty(AUTHORIZATION_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "authorization_date")
    private Date authorizationDate;

    @JsonProperty(AUTHORIZATION_COMMENT)
    @Column(name = "authorization_comment")
    private String authComment;

    @Enumerated(EnumType.STRING)
    private PortalModuleName authorisationType;

    @Enumerated(EnumType.STRING)
    @JsonProperty(APPROVAL_STATUS)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    private String reason;

    @JsonProperty(APPROVED_BY)
    @JsonManagedReference
    @ManyToOne
    private Employee approvedBy;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "confirmationAuthorizers")
    private Set<ExitRecord> exitRecord;


    public Authorizers() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Authorizers that = (Authorizers) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(notificationDate, that.notificationDate) &&
                Objects.equals(authorizationDate, that.authorizationDate) &&
                Objects.equals(authComment, that.authComment) &&
                authorisationType == that.authorisationType &&
                Objects.equals(approvalStatus, that.approvalStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, roles, notificationDate, authorizationDate, authComment, authorisationType, approvalStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Authorizers{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", id=").append(id);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", notificationDate=").append(notificationDate);
        sb.append(", authorizationDate=").append(authorizationDate);
        sb.append(", authComment='").append(authComment).append('\'');
        sb.append(", authorisationType=").append(authorisationType);
        sb.append(", approvalStatus=").append(approvalStatus);
        sb.append('}');
        return sb.toString();
    }
}

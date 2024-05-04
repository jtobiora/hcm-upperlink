package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
 * Created by Admin on 22/11/2017.
 */
@Data
@Entity
@Table(name = "awarers")
public class Awarers extends SuperModel implements Serializable{

    private static final String AUTHORIZATION_ROLE = "authorization_role";
    private static final String AWARENESS_ROLE = "awareness_role";
    private static final String NOTIFICATION_DATE = "notification_date";
    private static final String AUTHORIZATION_DATE = "authorization_date";
    private static final String AUTHORIZATION_COMMENT = "authorization_comment";
    private static final String AUTHORISATION_TYPE = "authorisation_type";
    private static final String APPROVAL_STATUS = "approval_status";

    @JsonProperty(Constants.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(AWARENESS_ROLE)
    @ManyToOne
    @JoinColumn(name = "awareness_role", nullable = false)
    private AwarenessRole roles;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonProperty(NOTIFICATION_DATE)
    @Column(name = "notification_date")
    private Date notificationDate;

    @JsonProperty(AUTHORISATION_TYPE)
    @Enumerated(EnumType.STRING)
    private PortalModuleName authorisationType;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_record_id")
    private LoanRecord loanRecord;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_record_id")
    private LeaveRecord leaveRecord;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "confirmationAwarers")
    private Set<ExitRecord> exitRecord;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Awarers awarers = (Awarers) o;
        return Objects.equals(id, awarers.id) &&
                Objects.equals(notificationDate, awarers.notificationDate) &&
                authorisationType == awarers.authorisationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, notificationDate, authorisationType);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Awarers{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", id=").append(id);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", notificationDate=").append(notificationDate);
        sb.append(", authorisationType=").append(authorisationType);
        sb.append('}');
        return sb.toString();
    }
}

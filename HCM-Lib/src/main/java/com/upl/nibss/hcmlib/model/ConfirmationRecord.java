/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.CompletionStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "confirmation_records", uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id","confirmation_checklist_id"}))
public class ConfirmationRecord extends SuperModel implements Serializable {

    private static final String COMPLETION_STATUS = "completion_status";
    private static final String COMPLETION_DATE = "completion_date";
    private static final String EMPLOYEE = "employee";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Long id;

    @JsonProperty(COMPLETION_STATUS)
    @Column(name = "completion_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "completion_date")
    private Date completionDate;

    @Embedded
    private Document document;

    @Lob
    private String comment;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "confirmation_checklist_id")
    private ConfirmationChecklist confirmationChecklist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConfirmationRecord that = (ConfirmationRecord) o;
        return available == that.available &&
                Objects.equals(id, that.id) &&
                completionStatus == that.completionStatus &&
                Objects.equals(completionDate, that.completionDate) &&
                Objects.equals(document, that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, completionStatus, completionDate, document, available);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfirmationRecord{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", id=").append(id);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", completionStatus=").append(completionStatus);
        sb.append(", completionDate=").append(completionDate);
        sb.append(", available=").append(available);
        sb.append('}');
        return sb.toString();
    }
}

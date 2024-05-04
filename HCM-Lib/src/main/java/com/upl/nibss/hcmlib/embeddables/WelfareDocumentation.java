/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.upl.nibss.hcmlib.enums.CompletionStatus;
import com.upl.nibss.hcmlib.model.WelfareChecklist;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class WelfareDocumentation implements Serializable {

    @Column(name = "COMPLETION_STATUS", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;

    @Column(name = "COMMENT_ON_DOC", length = 300)
    private String documentComment;

    @Column(name = "COMPLETION_DATE")
    private Date completionDate;

    @ManyToOne(targetEntity = WelfareChecklist.class)
    @JoinColumn(name = "CHECKLIST_DOCTYPE_ID", nullable = false)
    private WelfareChecklist checklistDocType;

    @JsonManagedReference
    @Embedded
    private Document document;

    @Column(name = "DOC_AVAILABLE", nullable = false)
    private boolean docAvailable;

    public WelfareDocumentation() {
    }

}

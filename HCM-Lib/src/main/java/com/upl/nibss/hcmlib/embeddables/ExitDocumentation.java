/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.upl.nibss.hcmlib.enums.CompletionStatus;
import com.upl.nibss.hcmlib.model.ExitChecklist;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class ExitDocumentation implements Serializable {

    // --------------------------------------------------------------------------------
    @Column(name = "COMPLETION_STATUS", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;

    @Column(name = "COMMENT_ON_DOC", length = 300)
    private String documentComment;

    @Column(name = "COMPLETION_DATE")
    private Date completionDate;

    @ManyToOne(targetEntity = ExitChecklist.class)
    @JoinColumn(name = "CHECKLIST_DOCTYPE_ID", nullable = false)
    private ExitChecklist checklistDocType;

    @Embedded
    private Document exitDocument;

    @Column(name = "DOC_AVAILABLE", nullable = false)
    private boolean docAvailable;

    public ExitDocumentation() {
    }

}

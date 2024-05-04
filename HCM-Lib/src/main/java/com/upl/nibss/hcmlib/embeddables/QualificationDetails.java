/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.upl.nibss.hcmlib.enums.CredentialType;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class QualificationDetails implements Serializable {

    @Embedded
    private Document qualificationDoc = new Document();

    @Enumerated(EnumType.STRING)
    private CredentialType credentials;

    public QualificationDetails() {
    }
}

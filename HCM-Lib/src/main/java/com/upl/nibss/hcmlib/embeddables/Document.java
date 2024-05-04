package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by toyin.oladele on 30/10/2017.
 */
@Data
@Embeddable
public class Document implements Serializable{

    private static final String DOCUMENT_NAME = "document_name";
    private static final String DOCUMENT_CONTENT_TYPE = "document_content_type";
    private static final String DOCUMENT_DESCRIPTION = "document_description";

    @JsonIgnore
    @Lob
    @Column(name = "document")
    private byte[] theDocument;

    @JsonProperty(DOCUMENT_NAME)
    private String documentName;

    @JsonProperty(DOCUMENT_CONTENT_TYPE)
    @Column(length = 128)
    private String docContentType;

    @JsonIgnore
    private double docSize = 0;

    @JsonProperty(DOCUMENT_DESCRIPTION)
    @Basic
    @Column(length = 100)
    private String documentDesc;

    public Document() {
    }

    public Document(byte[] theDocument, String documentName, String docContentType, double docSize, String documentDesc) {
        this.theDocument = theDocument;
        this.documentName = documentName;
        this.docContentType = docContentType;
        this.docSize = docSize;
        this.documentDesc = documentDesc;
    }
}

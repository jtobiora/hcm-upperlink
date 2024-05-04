/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gbenga
 */
@Data
@AllArgsConstructor
@Embeddable
public class NameType implements Serializable {

    @JsonProperty("title")
    @Column(name = "TITLE", length = 30)
    private String title;

    @JsonProperty("first_name")
    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    @JsonProperty("last_name")
    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    @JsonProperty("middle_name")
    @Column(name = "MIDDLE_NAME", length = 100)
    private String middleName;

    public NameType() {

    }
}

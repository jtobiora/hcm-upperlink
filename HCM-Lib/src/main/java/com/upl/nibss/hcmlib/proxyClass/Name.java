package com.upl.nibss.hcmlib.proxyClass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.embeddables.NameType;
import lombok.Data;

/**
 * Created by toyin.oladele on 11/11/2017.
 */
@Data
public class Name {
    //NameType
    @JsonProperty("title")
    private String title;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("middle_name")
    private String middleName;

    public Name(NameType nameType) {
        if (nameType == null){
            nameType = new NameType();
        }
        this.title = nameType.getTitle();
        this.firstName = nameType.getFirstName();
        this.lastName = nameType.getLastName();
        this.middleName = nameType.getMiddleName();
    }
}

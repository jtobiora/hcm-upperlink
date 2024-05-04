package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by stanlee on 22/01/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TaxInfo {
    @JsonProperty("employee_tax_identification_number")
    private String employeeTaxIdentificationNumber;

    @JsonProperty("payee_id")
    private String payeeId;
}

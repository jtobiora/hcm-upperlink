package com.upl.nibss.hcmlib.embeddables;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.model.LoanType;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by stanlee on 01/01/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LoanTypeNextUse implements Serializable {

    @OneToOne
    private LoanType loanType;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date nextUse;

}

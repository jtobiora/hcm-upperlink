/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.embeddables;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gbenga
 */
@Data
@Embeddable
public class FinanceDetails implements Serializable {

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @Column(name = "ACCOUNT_NO")
    private String accountNo;

    @Column(name = "BANK_NAME")
    private String bankName;

    public FinanceDetails() {

    }

    public FinanceDetails(String accountName, String accountNo, String bankName) {
        this.accountName = accountName;
        this.accountNo = accountNo;
        this.bankName = bankName;
    }
}

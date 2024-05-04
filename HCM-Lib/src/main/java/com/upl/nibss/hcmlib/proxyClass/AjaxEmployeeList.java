package com.upl.nibss.hcmlib.proxyClass;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.NameType;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by toyin.oladele on 19/11/2017.
 */
@Data
public class AjaxEmployeeList {

    private Long id;
    private String employeeNo;
    private Name empName;
    private String email;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date resumption_date;

    public AjaxEmployeeList() {

    }

    public AjaxEmployeeList(Long id, String employeeNo, NameType empName, String email, Date resumption_date) {
        this.id = id;
        this.employeeNo = employeeNo;
        this.empName = new Name(empName);
        this.email = email;
        this.resumption_date = resumption_date;
    }
}

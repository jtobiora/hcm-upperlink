package com.upl.nibss.hcmlib.model.auditModel;

import com.upl.nibss.hcmlib.enums.LeaveBookingStatus;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.repo.jdbcTemplatePagination.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * Created by stanlee on 12/03/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBookingAud extends BaseModel {

    private Long id;

    private int revision_id;

    private int revision_type;

    private Employee employee;

    private String title;

    private Date day;

    @Enumerated(EnumType.STRING)
    private LeaveBookingStatus status;

    private String icon;

    private String color;

    private Employee created_by;

    private Employee modified_by;

    protected Date createdAt;

    protected Date updatedAt;

    protected boolean deleted = false;

    public LeaveBookingAud(Long id) {
        super(id);
    }
}

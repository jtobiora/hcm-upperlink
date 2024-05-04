package com.upl.nibss.hcmlib.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.*;
import com.upl.nibss.hcmlib.enums.ConfirmationStatus;
import com.upl.nibss.hcmlib.enums.LeaveStatus;
import com.upl.nibss.hcmlib.enums.LoanEligibility;
import com.upl.nibss.hcmlib.enums.StaffType;
import com.upl.nibss.hcmlib.model.Allowance;
import com.upl.nibss.hcmlib.model.GradeLevel;
import com.upl.nibss.hcmlib.model.JobRole;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by stanlee on 23/02/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeObject {

    private Long id;

    private String employeeNo;

    private NameType empName;

    private String email;

    private Person employeePersonDetail;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Date expectedResumptionDate;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Date actualResumptionDate;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Date expectedConfirmationDate;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Date confirmationDate;

    private ConfirmationStatus confirmationStatus = ConfirmationStatus.ON_PROBATION;

    private LeaveStatus leaveStatus = LeaveStatus.NOT_APPROVED;

    private LoanEligibility loanEligibility = LoanEligibility.NOT_ELIGIBLE;

    private JobRole jobRole;

    private BigDecimal salary;

    private DepartmentEmbeddable department;

    private StaffType employeeType;

    private Long employeeSupervisor;

    private Long employeeSecondLevelSupervisor;

    private GradeLevel gradeLevel;

    private boolean activated;

    private boolean credentials_approved;

    private boolean supervisor;

    private boolean hcm;

    private String updateReason;

    private String disableReason;

}

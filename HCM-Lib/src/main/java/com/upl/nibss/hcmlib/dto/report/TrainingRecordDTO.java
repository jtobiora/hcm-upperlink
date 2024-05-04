package com.upl.nibss.hcmlib.dto.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.enums.TrainingBookingStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * Created by stanlee on 28/02/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRecordDTO {

    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String middleName;

    private String departmentName;

    private String trainingName;

    private String trainingDescription;

    @JsonProperty("start")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date training_start_day;

    @JsonProperty("end")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date training_end_day;

    @Enumerated(EnumType.STRING)
    private TrainingBookingStatus status;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    private String reason;

}

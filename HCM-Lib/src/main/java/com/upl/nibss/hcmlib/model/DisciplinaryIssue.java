package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.DisciplinaryType;
import com.upl.nibss.hcmlib.enums.SanctionType;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by toyin.oladele on 08/12/2017.
 */
@Data
@Entity
public class DisciplinaryIssue extends SuperModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Employee employee;

    @JsonProperty("disciplinary_type")
    @Enumerated(EnumType.STRING)
    private DisciplinaryType disciplinaryType;

    @JsonProperty("sanction_type")
    @Enumerated(EnumType.STRING)
    private SanctionType sanctionType;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonProperty("start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonProperty("end_date")
    private Date endDate;

    @NotBlank
    private String reason;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonProperty("date_Of_query")
    private Date dateOfQuery;

}

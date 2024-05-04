/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.AppraisalStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Gbenga
 */
@Data
@Entity
@Table(name = "PERFORMANCE_APPRAISALS")
public class PerformanceAppraisal extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JsonProperty(PerformanceAppraisalHistory.APPRAISAL_CYCLE)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @CreatedDate
    private Date appraisalCycle;

    @JsonProperty(PerformanceAppraisalHistory.APPRAISAL_SCORE)
    @NotBlank
    private String appraisalScore;

    @JsonProperty(PerformanceAppraisalHistory.PARC_RANKING)
    @NotBlank
    private String parcRanking;

    @JsonProperty(PerformanceAppraisalHistory.APPRAISAL_COMMENT)
    @NotBlank
    private String appraisalComment;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Employee employee;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Employee supervisor;

    public PerformanceAppraisal() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerformanceAppraisal that = (PerformanceAppraisal) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(appraisalCycle, that.appraisalCycle) &&
                Objects.equals(appraisalScore, that.appraisalScore) &&
                Objects.equals(parcRanking, that.parcRanking) &&
                Objects.equals(appraisalComment, that.appraisalComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, appraisalCycle, appraisalScore, parcRanking, appraisalComment);
    }
}

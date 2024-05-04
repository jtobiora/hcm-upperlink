/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class PerformanceAppraisalHistory extends SuperModel implements Serializable {

    public static final String APPRAISAL_CYCLE = "appraisal_cycle";
    public static final String APPRAISAL_SCORE = "appraisal_score";
    public static final String APPRAISAL_COMMENT = "appraisal_comment";
    public static final String PARC_RANKING = "parc_ranking";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(APPRAISAL_CYCLE)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @CreatedDate
    private Date appraisalCycle;

    @JsonProperty(APPRAISAL_SCORE)
    @NotBlank
    private String appraisalScore;

    @JsonProperty(PARC_RANKING)
    @NotBlank
    private String parcRanking;

    @JsonProperty(APPRAISAL_COMMENT)
    @NotBlank
    private String appraisalComment;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUPERVISOR_ID")
    private Employee supervisor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerformanceAppraisalHistory that = (PerformanceAppraisalHistory) o;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PerformanceAppraisalHistory{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", id=").append(id);
        sb.append(", appraisalCycle=").append(appraisalCycle);
        sb.append(", parcRanking=").append(parcRanking);
        sb.append(", appraisalScore='").append(appraisalScore).append('\'');
        sb.append(", appraisalComment='").append(appraisalComment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

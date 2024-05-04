package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.embeddables.QualificationDetails;
import com.upl.nibss.hcmlib.enums.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class EducationDetail extends SuperModel implements Serializable{
    private static final String INSTRUCTION = "instruction";
    private static final String QUALIFICATION = "qualification";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(Constants.ID)
    private Long id;

    @JsonProperty(Constants.START_DATE)
    private Date startDate;

    @JsonProperty(Constants.END_DATE)
    private Date endDate;

    @JsonProperty(INSTRUCTION)
    private String institution;

    @JsonProperty(QUALIFICATION)
    private QualificationDetails qualificationObtained = new QualificationDetails();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_detail_id", referencedColumnName = "id")
    @JsonIgnore
    private PersonDetails personDetails = new PersonDetails();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EducationDetail that = (EducationDetail) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(institution, that.institution) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, institution, startDate, endDate);
    }
}

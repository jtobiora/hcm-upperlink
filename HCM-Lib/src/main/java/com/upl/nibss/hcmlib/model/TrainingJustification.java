package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.TrainingType;
import lombok.Data;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by toyin.oladele on 12/12/2017.
 */
@Data
@Entity
public class TrainingJustification extends SuperModel implements Serializable{

    @Id
    private Long id;

    // @MapsId --> This way, the id column serves as both Primary Key and FK.
    // You’ll notice that the @Id column no longer uses a @GeneratedValue annotation
    // since the identifier is populated with the identifier of the TrainingRecord association.
    //this is used for better performance
    @MapsId
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private TrainingRecord trainingRecord;

    @JsonProperty("training_id")
    private transient String trainingId;
    @JsonProperty("training_name")
    private transient String trainingName;
    @JsonProperty("training_description")
    private transient String trainingDescription;

    @JsonProperty("competency_to_be_addressed")
    @Lob
    private String competencyToBeAddressed;

    @JsonProperty("current_proficiency_level")
    private Integer currentProficiencyLevel;

    @JsonProperty("expected_learning_outcomes")
    @Lob
    private String expectedLearningOutcomes;

    @JsonProperty("determine_effectiveness")
    @Lob
    private String determineEffectiveness;

    @Enumerated(EnumType.STRING)
    private TrainingType trainingType = TrainingType.ONLINE;

    public TrainingJustification() {
        trainingId = trainingRecord != null ? String.valueOf(trainingRecord.getId()) : null;
        trainingName = trainingRecord != null ? trainingRecord.getTrainingName() : null;
        trainingDescription = trainingRecord != null ? trainingRecord.getTrainingDescription() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TrainingJustification that = (TrainingJustification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(competencyToBeAddressed, that.competencyToBeAddressed) &&
                Objects.equals(currentProficiencyLevel, that.currentProficiencyLevel) &&
                Objects.equals(expectedLearningOutcomes, that.expectedLearningOutcomes) &&
                Objects.equals(determineEffectiveness, that.determineEffectiveness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, competencyToBeAddressed, currentProficiencyLevel, expectedLearningOutcomes, determineEffectiveness);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainingJustification{");
        sb.append("id=").append(id);
        sb.append(", trainingRecord=").append(trainingRecord);
        sb.append(", competencyToBeAddressed='").append(competencyToBeAddressed).append('\'');
        sb.append(", currentProficiencyLevel=").append(currentProficiencyLevel);
        sb.append(", expectedLearningOutcomes='").append(expectedLearningOutcomes).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", determineEffectiveness='").append(determineEffectiveness).append('\'');
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", deleted=").append(deleted);
        sb.append('}');
        return sb.toString();
    }
}

package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "exit_response",  uniqueConstraints = @UniqueConstraint(columnNames = {"exit_id", "exit_reason_id"}))
public class ExitResponse extends SuperModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("exit")
    @JoinColumn(name="exit_id")
    private Exit exit;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("exit_reason")
    @JoinColumn(name="exit_reason_id")
    private ExitReason exitReason;

    @Lob
    @JsonProperty("answer")
    private String answer;

    @Lob
    @JsonProperty("reason")
    private String reason;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExitResponse that = (ExitResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, answer);
    }
}

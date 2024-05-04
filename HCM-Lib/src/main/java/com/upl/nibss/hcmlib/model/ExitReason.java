package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upl.nibss.hcmlib.enums.ResponseStatus;
import com.upl.nibss.hcmlib.enums.ResponseType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "exit_reason")
public class ExitReason extends SuperModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("question")
    private String question;

    @JsonProperty("response_type")
    private String responseType = ResponseType.TEXT.name();

    @JsonProperty("response_status")
    private String responseStatus = ResponseStatus.OPTIONAL.name();

    @JsonProperty("options")
    private String options;//Comma separated values e.g Yes, No

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExitReason that = (ExitReason) o;
        return Objects.equals(id, that.id) &&
                responseType == that.responseType &&
                responseStatus == that.responseStatus &&
                Objects.equals(options, that.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, responseType, responseStatus, options);
    }
}

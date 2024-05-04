package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.LeaveBookingStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by toyin.oladele on 09/12/2017.
 */
@Data
@Entity
@Audited
@Table(name = "leave_bookings", uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id","day"}))
public class LeaveBookings extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Employee employee;

    private String title;

    @JsonProperty("date")
    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date day;

    @Enumerated(EnumType.STRING)
    private LeaveBookingStatus status;

    private String icon;

    private String color;

    @NotAudited
    @JsonProperty("leave_record")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "leave_record_id")
    private LeaveRecord leaveRecord;

    @JsonIgnore
    @JoinColumn(name = "created_by")
    @ManyToOne
    @CreatedBy
    private Employee createdBy;

    @JsonIgnore
    @JoinColumn(name = "modified_by")
    @ManyToOne
    @LastModifiedBy
    private Employee modifiedBy;

    public LeaveBookings() {

    }

    public LeaveBookings(Employee employee, Date day, String title, LeaveBookingStatus status) {
        this.employee = employee;
        this.title = title;
        this.day = day;
        this.status = status;
        this.color = getColor();
        this.icon = getIcon();
    }

    public String getColor() {
        return status != null ? status.getColor() : color;
    }

    public String getIcon() {
        return status != null ? status.getIcon() : icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LeaveBookings that = (LeaveBookings) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(day, that.day) &&
                status == that.status &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, day, status, icon, color);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LeaveBookings{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", day=").append(day);
        sb.append(", status=").append(status);
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}

package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Created by toyin.oladele on 09/10/2017.
 */
@Data
@Entity
@Table(name = "tasks")
public class Task extends SuperModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn
    private Module module;

    private Long parentTask;

    private String name;
    private String route;
    private String route_key;
    private String description;
    private boolean visibility;
    private int step;

    @JsonIgnore
    @ManyToMany(mappedBy = "tasks",fetch = FetchType.LAZY)
    private Set<Group> groups;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return visibility == task.visibility &&
                step == task.step &&
                Objects.equals(name, task.name) &&
                Objects.equals(route, task.route) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, route, description, visibility, step);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("name='").append(name).append('\'');
        sb.append(", route='").append(route).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", visibility=").append(visibility);
        sb.append(", step=").append(step);
        sb.append('}');
        return sb.toString();
    }
}

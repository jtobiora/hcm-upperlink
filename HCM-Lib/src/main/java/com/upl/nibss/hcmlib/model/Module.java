package com.upl.nibss.hcmlib.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by toyin.oladele on 09/10/2017.
 */
@Data
@Entity
@Table(name = "modules")
public class Module extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private boolean visibility;
    private int step;
    private String icon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Module module = (Module) o;
        return visibility == module.visibility &&
                step == module.step &&
                Objects.equals(name, module.name) &&
                Objects.equals(description, module.description) &&
                Objects.equals(icon, module.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, visibility, step, icon);
    }
}

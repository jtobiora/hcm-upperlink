package com.upl.nibss.hcmlib.embeddables;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by toyin.oladele on 03/12/2017.
 */
@Data
@AllArgsConstructor
@Embeddable
public class DepartmentEmbeddable implements Serializable {

    @NotNull
    @Column(nullable = false)
    private Long id;
    private String name;

    public DepartmentEmbeddable() {

    }
}

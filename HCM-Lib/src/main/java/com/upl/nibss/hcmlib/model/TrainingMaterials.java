package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.embeddables.FinanceDetails;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 15/12/2017.
 */
@Data
@Entity
public class TrainingMaterials extends SuperModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "materials")
    @Embedded
    private List<Document> materials = new ArrayList<>();

    private BigDecimal agreed_cost;

    @Embedded
    private FinanceDetails account;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private TrainingRecord trainingRecord;
}

package com.upl.nibss.hcmlib.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.utils.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Data
@Entity
@Table(name = "loan_records")
public class LoanRecord extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @Column(nullable = false)
    private String purposeOfLoan;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private int paymentPeriod;

    @JsonProperty("source_of_payment")
    private String sourceOfRepayment;

    @JsonProperty("interest_subsidy")
    private boolean interestSubsidy;

    @Column(name = "APPROVAL_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date approvalDate;

    private String reason;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Authorizers> requestAuthorization = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Awarers> requestAwareness = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LoanRecord that = (LoanRecord) o;
        return paymentPeriod == that.paymentPeriod &&
                Objects.equals(id, that.id) &&
                Objects.equals(purposeOfLoan, that.purposeOfLoan) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(sourceOfRepayment, that.sourceOfRepayment) &&
                approvalStatus == that.approvalStatus &&
                Objects.equals(approvalDate, that.approvalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, purposeOfLoan, amount, paymentPeriod, sourceOfRepayment, approvalStatus, approvalDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoanRecord{");
        sb.append("id=").append(id);
        sb.append(", bank=").append(bank);
        sb.append(", purposeOfLoan='").append(purposeOfLoan).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", paymentPeriod=").append(paymentPeriod);
        sb.append(", sourceOfRepayment='").append(sourceOfRepayment).append('\'');
        sb.append(", approvalStatus=").append(approvalStatus);
        sb.append(", approvalDate=").append(approvalDate);
        sb.append('}');
        return sb.toString();
    }
}

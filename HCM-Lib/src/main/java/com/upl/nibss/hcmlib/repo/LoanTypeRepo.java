package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Transactional
@Repository
public interface LoanTypeRepo extends JpaRepository<LoanType, Long> {

    @Query("select count(l.typeId) from LoanType l where l.loanType = :name")
    int getCountOfSimilarName(@Param("name") String name);

    @Query("select count(l.typeId) from LoanType l where l.loanType = :name and l.typeId <> :id")
    int getCountOfSimilarNameNotId(@Param("name") String name, @Param("id") Long id);

    @Modifying
    @Query("update LoanType l set l.activated = case l.activated when true then false else true end where l.typeId = :id")
    int toggleStatus(@Param("id") Long id);

    @Query("select l from LoanType l where l.activated = true and l.deleted = false ")
    List<LoanType> findAllByActivated();

    @Query("select l.loanType from LoanType l where l.activated = true and l.deleted = false ")
    List<String> findAllLoanNamesByActivated();

    @Query("select l from LoanType l where l.activated = true and l.id = :id")
    LoanType findByActivated(@Param("id") Long id);

    @Query(value = "SELECT l.* FROM loan_types l, employee_details_loan_types ed, employee_details e " +
            "WHERE ed.loan_type_type_id = l.type_id and ed.employee_details_id = e.id and e.employee_id = :id and (ed.next_use <= CURRENT_DATE or ed.next_use is NULL ) and l.activated = true",nativeQuery = true)
    List<LoanType> findAllEligibleLoanTypesSubsidyByEmployeeId(@Param("id") Long id);

}

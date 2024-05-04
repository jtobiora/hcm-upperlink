package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonalDetailRepo extends JpaRepository<PersonDetails, Long> {
    @Query("select pd from PersonDetails pd where pd.employeeId.id = :id")
    PersonDetails findByEmployeeId(@Param("id") Long id);

    @Query("select pd from PersonDetails pd")
    List<PersonDetails> findAllAjax();

    @Query("select pd from PersonDetails pd where pd.employeeId.id = :id")
    PersonDetails findAjax(@Param("id") Long id);
}

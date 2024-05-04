package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.UserType;
import com.upl.nibss.hcmlib.model.ConfirmationChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
@Repository
@Transactional
public interface ConfirmationChecklistRepo extends JpaRepository<ConfirmationChecklist, Long> {

    @Query("select c from ConfirmationChecklist c where c.activated = true ")
    List<ConfirmationChecklist> findAllActivated();

    @Query("select c from  ConfirmationChecklist c where c.userType = :userType")
    List<ConfirmationChecklist> findAllByUserType(@Param("userType") UserType userType);

    @Query("select c from ConfirmationChecklist c where c.userType = :userType and c.activated = true ")
    List<ConfirmationChecklist> findAllByUserTypeAndActivated(@Param("userType") UserType userType);

    @Query("select c from  ConfirmationChecklist c where c.userType = :userType and c.id = :id")
    ConfirmationChecklist findByUserTypeAndId(@Param("userType") UserType userType, @Param("id") Long id);

    @Query("select count(c.id) from ConfirmationChecklist c where c.requirement = :requirementName")
    int countOfSameRequirementName(@Param("requirementName") String requirementName);

    @Query("select count(c.id) from ConfirmationChecklist c where c.requirement = :requirementName and c.id <> :id")
    int countOfSameRequirementNameNotId(@Param("requirementName") String requirementName, @Param("id") Long id);

}

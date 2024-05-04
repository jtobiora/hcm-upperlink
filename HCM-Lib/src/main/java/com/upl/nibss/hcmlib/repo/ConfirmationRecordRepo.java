package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.model.ConfirmationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 05/12/2017.
 */
@Transactional
@Repository
public interface ConfirmationRecordRepo extends JpaRepository<ConfirmationRecord, Long> {

    @Query("select c from ConfirmationRecord c where c.employee.id = :id and c.deleted = false ")
    List<ConfirmationRecord> findAllByEmployee(@Param("id") Long id);

    @Query("select c.document from ConfirmationRecord c where c.id = :id and c.deleted = false ")
    Document findDocumentById(@Param("id") Long id);

    @Query("select c from ConfirmationRecord c where c.id in :ids and c.employee.id = :employeeId and c.deleted = false")
    List<ConfirmationRecord> findAllByIdsAAndEmployee(@Param("ids") List<Long> ids, @Param("employeeId") Long employeeId);

    @Query("select count(c.id) from ConfirmationRecord c where c.employee.id = :employee_id and c.confirmationChecklist.id = :checklist_id and c.deleted = false ")
    Integer countOfConfirmationRecWithSimilarEmplAndChecklist(@Param("employee_id") Long employeeId, @Param("checklist_id") Long checklistId);

}

package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.TransferRecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 25/11/2017.
 */
@Transactional
@Repository
public interface TransferRecordHistoryRepo extends JpaRepository<TransferRecordHistory, Long>{

    @Query("select t from TransferRecordHistory t where t.employee.id = :employeeId")
    List<TransferRecordHistory> getAllByEmployee(@Param("employeeId") Long employeeId);

    @Query("select t from TransferRecordHistory t where t.employee.id = :employeeId and t.id = :id")
    TransferRecordHistory getTransferRecordHistoryByEmployeeAndId(@Param("employeeId") Long employeeId, @Param("id") Long id);

}

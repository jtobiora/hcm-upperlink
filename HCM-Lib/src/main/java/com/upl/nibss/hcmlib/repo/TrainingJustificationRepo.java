package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.TrainingJustification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by toyin.oladele on 12/12/2017.
 */
@Repository
@Transactional
public interface TrainingJustificationRepo extends JpaRepository<TrainingJustification, Long> {

    @Query("select t from TrainingJustification t where t.trainingRecord.id = :trainingRecordId")
    TrainingJustification getByTrainingRecord(@Param("trainingRecordId") Long trainingRecordId);
}

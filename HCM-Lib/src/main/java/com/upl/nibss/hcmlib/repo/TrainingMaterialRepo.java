package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.model.TrainingMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 15/12/2017.
 */
@Transactional
@Repository
public interface TrainingMaterialRepo extends JpaRepository<TrainingMaterials, Long>{

    @Query("select t from TrainingMaterials t where t.trainingRecord.id = :trainingRecordId")
    List<TrainingMaterials> getByTrainingRecord(@Param("trainingRecordId") Long trainingRecordId);

}

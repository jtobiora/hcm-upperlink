package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.RecruitmentRecordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RecruitmentRecordHistoryRepo extends JpaRepository<RecruitmentRecordHistory, Long> {

    @Query("select r from RecruitmentRecordHistory r where r.supervisor.id = :supervisorId")
    List<RecruitmentRecordHistory> getAllBySupervisor(@Param("supervisorId") Long supervisorId);

    @Query("select r from RecruitmentRecordHistory r where r.supervisor.id = :supervisorId and r.id = :id")
    RecruitmentRecordHistory getRecruitmentRecordHistoryBySupervisorAndId(@Param("supervisorId") Long supervisorId, @Param("id") Long id);
}

package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.TrainingMaterialsPerEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 16/12/2017.
 */
@Transactional
@Repository
public interface TrainingMaterialsPerEmployeeRepo extends JpaRepository<TrainingMaterialsPerEmployee,Long>{

    @Query("select t from TrainingMaterialsPerEmployee t where t.trainingRecord.id = :training_record_id ")
    List<TrainingMaterialsPerEmployee> getByTrainingRecord(@Param("training_record_id") Long training_record_id);

    @Query("select t from TrainingMaterialsPerEmployee t where t.trainingRecord.id = :training_record_id and t.employee.id = :employee_id")
    TrainingMaterialsPerEmployee getByTrainingRecordAndEmployee(@Param("training_record_id") Long training_record_id, @Param("employee_id") Long employeeId);

    @Query(value = "select t.* from training_materials_per_employee t, employee_training_materials_authorizations ta, authorizers a, authorization_roles ar, employees e " +
            "WHERE t.id = ta.training_material_per_employee_id and ta.authoriser_id = a.id and a.authorization_role = ar.auth_role_id " +
            "and ((e.id = :employeeId and e.job_role_id = ar.job_role_id) " +
            "or (ar.supervisor_category = 'SUPERVISOR' and e.id = t.employee_id and e.supervisor_id = :employeeId) " +
            "or (ar.supervisor_category = 'SECOND_LEVEL_SUPERVISOR' and e.id = t.employee_id and e.second_level_supervisor_id = :employeeId)) " +
            "and e.activated = true and e.deleted = false " +
            "and t.approval_status = :approvalStatus ", nativeQuery = true)
    List<TrainingMaterialsPerEmployee> getTrainingMaterialApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

    @Query(value = "select t.* from training_materials_per_employee t, employee_training_materials_authorizations ta, authorizers a, employees e " +
            "WHERE t.id = ta.training_material_per_employee_id and ta.authoriser_id = a.id " +
            "and e.id = :employeeId and e.activated = true and e.deleted = false " +
            "and a.approval_status = :approvalStatus and a.approved_by_id = :employeeId", nativeQuery = true)
    List<TrainingMaterialsPerEmployee> getTrainingMaterialApplicationBaseOnMyApprovals(@Param("employeeId") Long employeeId, @Param("approvalStatus") String approvalStatus);

}

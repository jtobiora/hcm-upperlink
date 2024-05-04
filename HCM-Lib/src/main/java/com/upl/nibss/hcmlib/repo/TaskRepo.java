package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 10/10/2017.
 */
@Repository
@Transactional
public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.deleted = false")
    List<Task> findAll();

    @Query("select t from Task t where t.id = :id and t.deleted = false")
    Task findOne(@Param("id") Long id);

    @Query("select t from Task t where t.id in :id and t.deleted = false")
    List<Task> findAllById(@Param("id") List<Long> id);

    @Query(value = "select t.name from tasks t, group_tasks g, user_groups u where t.id = g.tasks_id AND g.groups_id = u.groups_id and u.users_id = :userId ;", nativeQuery = true)
    List<String> findAllTasksByUserId(@Param("userId") Long userId);

//    @Query("select new com.upl.nibss.hcmlib.proxyClass.AjaxModuleTask(t.id, t.name, t.module.name) from Task t where t.deleted = false")
//    List<AjaxModuleTask> findAllAjaxModuleTask();
//
//    @Query("select new com.upl.nibss.hcmlib.proxyClass.AjaxTask(t.name , t.route, t.description, t.step, t.icon, t.module.icon, t.module.name, t.parentTask.name) from Task t where t.deleted = false")
//    List<AjaxTask> findAllAjaxTask();
//
//    @Query("select new com.upl.nibss.hcmlib.proxyClass.AjaxTask(t.name , t.route, t.description, t.step, t.icon, t.module.icon, t.module.name, t.parentTask.name) from Task t where t.deleted = false and t.id = :id ")
//    AjaxTask findTaskById(@Param("id") Long id);
}

package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 10/10/2017.
 */
@Repository
@Transactional
public interface GroupRepo extends JpaRepository<Group, Long> {

    @Query("select g from Group g where g.deleted = false")
    List<Group> findAll();

    @Query("select g from Group g where g.id = :id and g.deleted = false")
    Group findOne(@Param("id") Long id);

    @Query("select g from Group g where lower(g.name) = :name ")
    Group findByName(@Param("name") String name);

    @Query("select g from Group g where g.deleted = false")
    List<Group> findAllAjaxGroup();

    @Query("select new com.upl.nibss.hcmlib.model.Group(g.id,g.name) from Group g where g.deleted = false and g.activated = true ")
    List<Group> findAllEnabled();

    @Query("select g from Group g where g.deleted = false and g.id = :id ")
    Group findGroupById(@Param("id") Long id);

    @Query("select g.tasks from Group g where g.id = :id")
    Set<Task> findGroupTasksById(@Param("id") Long id);

    @Query("select g.tasks from Group g where g.id in :id")
    Set<Task> findGroupTasksByIds(@Param("id") List<Long> id);

//    @Query("select g from Group g where g.id in :id")
//    List<Group> findAllByIds(@Param("id") List<Long> id);

    @Modifying
    @Query("delete from Group g where g.id = :id")
    void deleteGroupById(@Param("id") Long id);

    @Query("select g from Group g where g.id in :ids")
    List<Group> findAllByIds(@Param("ids") List<Long> ids);
}

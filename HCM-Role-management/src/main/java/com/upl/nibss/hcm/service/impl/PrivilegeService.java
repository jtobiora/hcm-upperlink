package com.upl.nibss.hcm.service.impl;

import com.upl.nibss.hcm.service.interfaces.IPrivilegeService;
import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.Task;
import com.upl.nibss.hcmlib.proxyClass.AjaxTasksPerGroup;
import com.upl.nibss.hcmlib.repo.GroupRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by toyin.oladele on 20/10/2017.
 */
@Service
public class PrivilegeService implements IPrivilegeService {

    private static final Logger logger = LoggerFactory.getLogger(PrivilegeService.class);

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public List<AjaxTasksPerGroup> getAll() {

        try {

            List<Group> groups = groupRepo.findAll();
            return generateListOfTasksPerGroup(groups);

        } catch (Exception e) {
            logger.error("Unable to fetch grouptask ",e);
        }

        return new ArrayList<>();
    }

    @Override
    public List<AjaxTasksPerGroup> getByGroupId(Long id) {
        try {
            //pulls all groups with tasks
            Group group = groupRepo.findOne(id);
            List<Group> groups = new ArrayList<>();
            groups.add(group);
            return generateListOfTasksPerGroup(groups);
        } catch (Exception e) {
            logger.error("Unable to get groupTask : {}", id, e);
        }

        return new ArrayList<>();
    }

    @Override
    public List<AjaxTasksPerGroup> create(Group group, List<Task> tasks) throws Exception{

            group.setTasks(new HashSet<>(tasks));
            List<Group> groups = new ArrayList<>();
            group = groupRepo.save(group);
            groups.add(group);
            return generateListOfTasksPerGroup(groups);
    }

    @Override
    public List<AjaxTasksPerGroup> update(Group group, List<Task> tasks, Long groupId) throws Exception{

        //then recreate
        group.getTasks().clear();
        group.getTasks().addAll(tasks);
        group = groupRepo.save(group);

        List<AjaxTasksPerGroup> tasksPerGroups = new ArrayList<>();
        tasksPerGroups.add(new AjaxTasksPerGroup(group.getId(), group.getName(), group.isActivated(), group.getTasks()));
        return tasksPerGroups;

    }

    @Override
    public boolean toggle(Long groupId) throws Exception{
            Group group = groupRepo.findOne(groupId);
            if (group != null) {
                group.setActivated(!group.isActivated());
                groupRepo.save(group);
                return true;
            }
        return false;
    }

    private List<AjaxTasksPerGroup> generateListOfTasksPerGroup(List<Group> groups) {

        if (groups == null){
            logger.error("groups is null");
            return new ArrayList<>();
        }

        List<AjaxTasksPerGroup> tasksPerGroups = new ArrayList<>();
        groups.stream().forEach(group -> tasksPerGroups.add(new AjaxTasksPerGroup(group.getId(), group.getName(), group.isActivated(), group.getTasks())) );
        return tasksPerGroups;

    }

}

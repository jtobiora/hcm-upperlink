package com.upl.nibss.hcm.service.impl;

import com.upl.nibss.hcm.service.interfaces.ITaskService;
import com.upl.nibss.hcmlib.model.Module;
import com.upl.nibss.hcmlib.model.Task;
import com.upl.nibss.hcmlib.proxyClass.AjaxModuleTask;
import com.upl.nibss.hcmlib.repo.GroupRepo;
import com.upl.nibss.hcmlib.repo.TaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 20/10/2017.
 */
@Service
public class TaskService implements ITaskService {

    private final static Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public List<Task> getAll() {
        try {
            return taskRepo.findAll();
        } catch (Exception e) {
            logger.error("Unable to fetch all task", e);
        }

        return new ArrayList<>();
    }

    @Override
    public List<AjaxModuleTask> getAllTaskByGroupId(List<Long> groupIds) {

        try {
            List<AjaxModuleTask> ajaxModuleTasks = new ArrayList<>();
            Set<Task> tasks = groupRepo.findGroupTasksByIds(groupIds);
            tasks.stream().forEach(task -> ajaxModuleTasks.add(new AjaxModuleTask(task.getId(), task.getName(), task.getModule().getName())));
            return ajaxModuleTasks;
        } catch (Exception e) {
            logger.error("Unable get the Tasks for these groupids : {}", groupIds, e);
        }

        return new ArrayList<>();
    }

    @Override
    public Task getTask(Long id) {
        try {
            return taskRepo.findOne(id);
        } catch (Exception e) {
            logger.error("Unable to find task : id -> {} ", id, e);
        }

        return new Task();
    }

    @Override
    public List<Task> getAllTaskById(List<Long> ids) {

        try {
            return taskRepo.findAllById(ids);
        } catch (Exception e) {
            logger.error("Unable to find all the tasks by id", e);
        }

        return new ArrayList<>();
    }

    @Override
    public Task get(Long id) throws Exception {
            return taskRepo.findOne(id);
    }

    @Override
    public AjaxModuleTask create(Task ajaxTask, Task parentTask ,Module module) {

        Task task = new Task();
        task.setName(ajaxTask.getName());
        task.setDescription(ajaxTask.getDescription());
        task.setRoute(ajaxTask.getRoute());
        task.setStep(ajaxTask.getStep());
        task.setParentTask(parentTask != null ? parentTask.getId() : null);
        task.setModule(module);
        try {
            Task saved = taskRepo.save(task);
            if (saved != null) {
                return new AjaxModuleTask(saved.getId(), saved.getName(),saved.getModule().getName());
            }
        } catch (Exception e) {
            logger.error("Unable to save the Task : {}", ajaxTask, e);
        }

        return new AjaxModuleTask();

    }

    @Override
    public AjaxModuleTask update(Task ajaxTask, Task parentTask ,Module module, Long id) {

        Task task = taskRepo.findOne(id);
        task.setName(ajaxTask.getName());
        task.setDescription(ajaxTask.getDescription());
        task.setRoute(ajaxTask.getRoute());
        task.setStep(ajaxTask.getStep());
        task.setParentTask(parentTask != null ? parentTask.getId() : null);
        task.setModule(module);
        try {
            Task saved = taskRepo.save(task);
            if (saved != null) {
                return new AjaxModuleTask(saved.getId(), saved.getName(),saved.getModule().getName());
            }
        } catch (Exception e) {
            logger.error("Unable to update the Task : {}", id, e);
        }

        return new AjaxModuleTask();
    }

    @Override
    public List<String> getTaskByUserId(Long userId) throws Exception {
        if (userId == null){
            return new ArrayList<>();
        }

        return taskRepo.findAllTasksByUserId(userId);
    }
}

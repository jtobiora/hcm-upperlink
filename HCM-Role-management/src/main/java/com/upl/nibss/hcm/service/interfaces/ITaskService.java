package com.upl.nibss.hcm.service.interfaces;

import com.upl.nibss.hcmlib.model.Module;
import com.upl.nibss.hcmlib.model.Task;
import com.upl.nibss.hcmlib.proxyClass.AjaxModuleTask;

import java.util.List;

/**
 * Created by toyin.oladele on 20/10/2017.
 */
public interface ITaskService {

    List<Task> getAll() throws Exception;

    List<AjaxModuleTask> getAllTaskByGroupId(List<Long> groupIds) throws Exception;

    Task get(Long id) throws Exception;

    Task getTask(Long id) throws Exception;

    List<Task> getAllTaskById(List<Long> ids) throws Exception;

    AjaxModuleTask create(Task ajaxTask, Task parentTask , Module module) throws Exception;

    AjaxModuleTask update(Task ajaxTask, Task parentTask , Module module, Long id) throws Exception;

    List<String> getTaskByUserId(Long userId) throws Exception;

}

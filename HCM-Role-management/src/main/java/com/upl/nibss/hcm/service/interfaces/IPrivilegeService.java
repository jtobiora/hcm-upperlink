package com.upl.nibss.hcm.service.interfaces;

import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.Task;
import com.upl.nibss.hcmlib.proxyClass.AjaxTasksPerGroup;

import java.util.List;

/**
 * Created by toyin.oladele on 20/10/2017.
 */
public interface IPrivilegeService {

    List<AjaxTasksPerGroup> getAll();

    List<AjaxTasksPerGroup> getByGroupId(Long id);

    List<AjaxTasksPerGroup> create(Group group, List<Task> tasks) throws Exception;

    List<AjaxTasksPerGroup> update(Group group, List<Task> tasks, Long id) throws Exception;

    boolean toggle(Long id) throws Exception;

}

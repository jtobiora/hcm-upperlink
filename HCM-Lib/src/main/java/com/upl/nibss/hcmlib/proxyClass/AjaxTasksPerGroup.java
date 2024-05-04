package com.upl.nibss.hcmlib.proxyClass;

import com.upl.nibss.hcmlib.model.Task;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 20/10/2017.
 */
@Data
public class AjaxTasksPerGroup {

    private static final Logger logger = LoggerFactory.getLogger(AjaxTasksPerGroup.class);

    private AjaxGroup group;
    private List<AjaxModuleTask> tasks = new ArrayList<>();

    public AjaxTasksPerGroup() {
    }

    public AjaxTasksPerGroup(Long id, String groupname, boolean status, Set<Task> tasks) {
        this.group = new AjaxGroup(id, groupname, status);
        tasks.stream().forEach(task -> this.tasks.add(new AjaxModuleTask(task.getId(), task.getName(), task.getModule().getName())));
    }
}

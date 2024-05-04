package com.upl.nibss.hcm.controller;

import com.upl.nibss.hcm.dto.task.TaskRequest;
import com.upl.nibss.hcm.dto.task.TaskResponse;
import com.upl.nibss.hcm.enums.Errors;
import com.upl.nibss.hcm.enums.Success;
import com.upl.nibss.hcm.service.interfaces.IModuleService;
import com.upl.nibss.hcm.service.interfaces.ITaskService;
import com.upl.nibss.hcmlib.cache.IUserTokenCache;
import com.upl.nibss.hcmlib.config.JWTRedisToken;
import com.upl.nibss.hcmlib.dto.UserDetail;
import com.upl.nibss.hcmlib.model.Module;
import com.upl.nibss.hcmlib.model.Task;
import com.upl.nibss.hcmlib.proxyClass.AjaxModuleTask;
import com.upl.nibss.hcmlib.utils.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 19/10/2017.
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private ITaskService iTaskService;

    @Autowired
    private IModuleService iModuleService;

    @Autowired
    private JWTRedisToken jwtRedisToken;

    @Autowired
    private IUserTokenCache userTokenCache;

    @GetMapping(produces = "application/json")
    public ResponseEntity<GenericResponse> viewAll() throws Exception {
        return new ResponseEntity<>(GenericResponse.getAllResponse(iTaskService.getAll().toArray(), Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<GenericResponse> view(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(GenericResponse.getResponse(iTaskService.get(id),Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @PutMapping(value = "/bygroupIds", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> viewAllTaskByGroups(@RequestBody List<Long> groupIds) throws Exception {

        if (groupIds == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.INVALID_REQUEST.getValue()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GenericResponse.getAllResponse(iTaskService.getAllTaskByGroupId(groupIds).toArray(),Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> create(@RequestBody TaskRequest taskRequest) throws Exception {

        if (taskRequest == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.INVALID_REQUEST.getValue()), HttpStatus.BAD_REQUEST);
        }

        Task ajaxTask = new Task();
        ajaxTask.setDescription(taskRequest.getDescription());
        ajaxTask.setName(taskRequest.getName());
        ajaxTask.setRoute(taskRequest.getRoute());
        ajaxTask.setStep(taskRequest.getStep());

        Module module = iModuleService.get(taskRequest.getModuleId());
        if (module == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.MODULE_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        Task parentTask = iTaskService.getTask(taskRequest.getParentTaskId());
        if (parentTask == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.PARENT_TASK_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        List<AjaxModuleTask> ajaxTasks = new ArrayList<>();
        ajaxTasks.add(iTaskService.create(ajaxTask, parentTask, module));

        return new ResponseEntity<>(GenericResponse.getAllResponse(ajaxTasks.toArray(),Success.SUCCESS.getValue()), HttpStatus.OK);
    }

//    @PostMapping(value = "/create/bulk", consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<TaskResponse> create(@RequestBody List<TaskRequest> taskRequests){
////
//        TaskResponse response = new TaskResponse();
////
////        if (taskRequests == null){
////            response.setMessage(Errors.INVALID_REQEUST.getValue());
////            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
////        }
////
////        iModuleService.get()
//        response.setMessage("UNDONE");
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) throws Exception {

        if (taskRequest == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.INVALID_REQUEST.getValue()), HttpStatus.BAD_REQUEST);
        }

        Task ajaxTask = new Task();
        ajaxTask.setDescription(taskRequest.getDescription());
        ajaxTask.setName(taskRequest.getName());
        ajaxTask.setRoute(taskRequest.getRoute());
        ajaxTask.setStep(taskRequest.getStep());

        Module module = iModuleService.get(taskRequest.getModuleId());
        if (module == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.MODULE_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        Task parentTask = iTaskService.getTask(taskRequest.getParentTaskId());
        if (taskRequest.getParentTaskId() != null && parentTask == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.PARENT_TASK_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        List<AjaxModuleTask> ajaxTasks = new ArrayList<>();
        ajaxTasks.add(iTaskService.update(ajaxTask, parentTask, module, id));
        return new ResponseEntity<>(GenericResponse.getAllResponse(ajaxTasks.toArray(),Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @GetMapping(value = "/mytask")
    public ResponseEntity<GenericResponse> getMyTask(@RequestHeader("Authorization") String authorization) throws Exception{

        UserDetail userDetail = jwtRedisToken.decodeToken(authorization);

        if (userDetail == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse("Invalid user token"),HttpStatus.BAD_REQUEST);
        }

        if (!jwtRedisToken.isValidUserToken(authorization,userDetail.getSessionId())){
            logger.error("Invalid user token");
            return new ResponseEntity<>(GenericResponse.getErrorResponse("Unknown user token"),HttpStatus.BAD_REQUEST);
        }

        List<String> tasks = iTaskService.getTaskByUserId(userDetail.getUserId());

        userTokenCache.saveUserTokenAndTask(userDetail.getSessionId(), authorization, tasks);

        return new ResponseEntity<>(GenericResponse.getAllResponse(tasks.toArray(),Success.SUCCESS.getValue()),HttpStatus.OK);
    }

}

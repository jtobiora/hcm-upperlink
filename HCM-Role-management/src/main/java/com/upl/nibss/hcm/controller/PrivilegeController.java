package com.upl.nibss.hcm.controller;

import com.upl.nibss.hcm.dto.privilege.PrivilegeRequest;
import com.upl.nibss.hcm.dto.privilege.PrivilegeRequestByGroupName;
import com.upl.nibss.hcm.enums.Errors;
import com.upl.nibss.hcm.enums.Success;
import com.upl.nibss.hcm.service.interfaces.IPrivilegeService;
import com.upl.nibss.hcm.service.interfaces.ITaskService;
import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.Task;
import com.upl.nibss.hcmlib.service.interfaces.IGroupService;
import com.upl.nibss.hcmlib.utils.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 19/10/2017.
 */
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    private static final Logger logger = LoggerFactory.getLogger(PrivilegeController.class);

    @Autowired
    private IPrivilegeService iPrivilegeService;

    @Autowired
    private IGroupService iGroupService;

    @Autowired
    private ITaskService iTaskService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<GenericResponse> viewAll() throws Exception{
        return new ResponseEntity<>(GenericResponse.getAllResponse(iPrivilegeService.getAll().toArray(), Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @GetMapping(value = "/{groupId}", produces = "application/json")
    public ResponseEntity<GenericResponse> view(@PathVariable Long groupId) throws Exception{
        return new ResponseEntity<>(GenericResponse.getAllResponse(iPrivilegeService.getByGroupId(groupId).toArray(), Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> create(@RequestBody PrivilegeRequest request) throws Exception {

        if (request == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.INVALID_REQUEST.getValue()), HttpStatus.BAD_REQUEST);
        }

        Group group = iGroupService.getGroup(request.getGroupId());
        if (group == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.GROUP_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        List<Task> tasks = iTaskService.getAllTaskById(request.getTaskIds());
        if (tasks == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.TASK_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(GenericResponse.getAllResponse(iPrivilegeService.create(group, tasks).toArray(),Success.SUCCESS.getValue()), HttpStatus.OK);

    }

    @PostMapping(value = "/createByGroupName", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> createByGroupName(@RequestBody PrivilegeRequestByGroupName request) throws Exception {

        if (request == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.INVALID_REQUEST.getValue()), HttpStatus.BAD_REQUEST);
        }

        if (request.getGroupName() == null || request.getGroupName().isEmpty()){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.EMPTY_GROUP_NAME.getValue()), HttpStatus.BAD_REQUEST);
        }

        //create the group first
        Group group;
        try {
            group = iGroupService.createGroup(request.getGroupName());
        } catch (DataIntegrityViolationException e) {
            logger.error("Duplicate group Name",e);
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.GROUP_ALREADY_EXIST.getValue().replace("{}",request.getGroupName())), HttpStatus.NOT_ACCEPTABLE);
        }

        //allocate the tasks to the group.
        if (group == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.GROUP_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        List<Task> tasks = iTaskService.getAllTaskById(request.getTaskIds());
        if (tasks == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.TASK_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GenericResponse.getResponse(iPrivilegeService.create(group, tasks),Success.SUCCESS.getValue()), HttpStatus.OK);

    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> update(@RequestBody PrivilegeRequest request) throws Exception {

        if (request == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.INVALID_REQUEST.getValue()), HttpStatus.BAD_REQUEST);
        }

        Group group = iGroupService.getGroup(request.getGroupId());
        if (group == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.GROUP_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        List<Task> tasks = request.getTaskIds() == null || request.getTaskIds().isEmpty() ? new ArrayList<>() : iTaskService.getAllTaskById(request.getTaskIds());
        if (tasks == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.TASK_NOT_FOUND.getValue()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(GenericResponse.getResponse(iPrivilegeService.update(group, tasks, group.getId()),Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/toggle", produces = "application/json")
    public ResponseEntity<GenericResponse> toggle(@PathVariable Long id) throws Exception{

        boolean toggle = iPrivilegeService.toggle(id);

        if (!toggle){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(GenericResponse.getToggleResponse(Boolean.TRUE, Success.SUCCESS.getValue()), HttpStatus.OK);
    }

}

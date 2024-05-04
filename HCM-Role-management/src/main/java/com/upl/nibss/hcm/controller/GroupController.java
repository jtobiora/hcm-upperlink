package com.upl.nibss.hcm.controller;

import com.upl.nibss.hcm.dto.group.GroupRequest;
import com.upl.nibss.hcm.enums.Errors;
import com.upl.nibss.hcm.enums.Success;
import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.service.interfaces.IGroupService;
import com.upl.nibss.hcmlib.service.interfaces.IUserService;
import com.upl.nibss.hcmlib.utils.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by toyin.oladele on 18/10/2017.
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private IGroupService iGroupService;

    @Autowired
    private IUserService iUserService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<GenericResponse> viewAll() throws Exception {
        return new ResponseEntity<>(GenericResponse.getAllResponse(iGroupService.getAll().toArray(), Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @GetMapping("/enabled")
    public ResponseEntity<GenericResponse> viewAllEnabled() throws Exception{
        return ResponseEntity.ok(GenericResponse.getAllResponse(iGroupService.getAllEnabled().toArray(), Success.SUCCESS.getValue()));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<GenericResponse> view(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(GenericResponse.getResponse(iGroupService.get(id),Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> create(@RequestBody GroupRequest request) throws Exception {

        if (request == null || request.getName() == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.INVALID_REQUEST.getValue()), HttpStatus.BAD_REQUEST);
        }

//        if (iGroupService.getGroup(request.getName()) != null){
//            response.setMessage(Errors.GROUP_ALREADY_EXIST.getValue().replace("{}",request.getName()));
//            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
//        }

        Group ajaxGroup = null;
        try {
            ajaxGroup = iGroupService.create(request.getName());
        } catch (DataIntegrityViolationException e) {
            logger.error("Duplicate group Name",e);
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.GROUP_ALREADY_EXIST.getValue().replace("{}",request.getName())), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(GenericResponse.getResponse(ajaxGroup,Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenericResponse> update(@RequestBody GroupRequest request, @PathVariable Long id) throws Exception {

        if (request == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.INVALID_REQUEST.getValue()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GenericResponse.getResponse(iGroupService.update(request.getName(),id),Success.SUCCESS.getValue()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/toggle", produces = "application/json")
    public ResponseEntity<GenericResponse> toggle(@PathVariable Long id) throws Exception {

        if (id == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.ID_IS_NULL.getValue()),HttpStatus.BAD_REQUEST);
        }

        boolean toggle = iGroupService.toggle(id);

        if (!toggle){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(GenericResponse.getToggleResponse(true,Success.SUCCESS.getValue()),HttpStatus.OK);
    }

    @GetMapping(value = "/employees/{employeeId}", produces = "application/json")
    public ResponseEntity<GenericResponse> getGroupsByEmployeeId(@PathVariable("employeeId") Long employeeId) throws Exception{

        if (employeeId == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse("Employee Id is null"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(GenericResponse.getAllResponse(iUserService.getGroupByEmployeeId(employeeId).toArray(), Success.SUCCESS.getValue()), HttpStatus.OK);
    }

}

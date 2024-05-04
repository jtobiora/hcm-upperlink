package com.upl.nibss.hcm.controller;

import com.upl.nibss.hcm.enums.Errors;
import com.upl.nibss.hcm.enums.Success;
import com.upl.nibss.hcm.service.interfaces.IModuleService;
import com.upl.nibss.hcmlib.model.Module;
import com.upl.nibss.hcmlib.utils.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by toyin.oladele on 20/10/2017.
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private IModuleService iModuleService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<GenericResponse> viewAll(){
        return new ResponseEntity<>(GenericResponse.getAllResponse(iModuleService.getAll().toArray(), Success.SUCCESS.getValue()), HttpStatus.OK) ;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<GenericResponse> view(@PathVariable Long id){

        if (id == null){
            return new ResponseEntity<>(GenericResponse.getErrorResponse(Errors.ID_IS_NULL.getValue()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GenericResponse.getResponse(iModuleService.get(id),Success.SUCCESS.getValue()), HttpStatus.OK);
    }

}

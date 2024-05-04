package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.State;
import com.upl.nibss.hcmlib.repo.StateRepo;
import com.upl.nibss.hcmlib.service.interfaces.IStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
@Service
public class StateService implements IStateService {

    private static final Logger logger = LoggerFactory.getLogger(StateService.class);

    @Autowired
    private StateRepo stateRepo;

    @Override
    public List<State> getAll() throws Exception{
        return stateRepo.getAllAjaxState();
    }

    @Override
    public State getAjaxState(Long id) throws Exception{
        return stateRepo.getAjaxState(id);
    }

    @Override
    public State get(Long id) throws Exception {
        return stateRepo.findOne(id);
    }
}

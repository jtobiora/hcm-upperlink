package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.State;

import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
public interface IStateService {

    List<State> getAll() throws Exception;

    State getAjaxState(Long id) throws Exception;

    State get(Long id) throws Exception;

}

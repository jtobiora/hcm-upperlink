package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.Group;

import java.util.List;

/**
 * Created by toyin.oladele on 12/11/2017.
 */
public interface IGroupService {

    List<Group> findAll(List<Long> groupids) throws Exception;

    List<Group> getAll() throws Exception;

    List<Group> getAllEnabled() throws Exception;

    Group get(Long id) throws Exception;

    Group getGroup(Long id) throws Exception;

    Group getGroup(String name) throws Exception;

    Group create(String name) throws Exception;

    Group createGroup(String name) throws Exception;

    Group update(String name, Long id) throws Exception;

    boolean toggle(Long id) throws Exception;

}

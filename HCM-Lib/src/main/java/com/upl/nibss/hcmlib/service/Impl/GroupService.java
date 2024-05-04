package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.repo.GroupRepo;
import com.upl.nibss.hcmlib.service.interfaces.IGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 12/11/2017.
 */
@Service
public class    GroupService implements IGroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public List<Group> findAll(List<Long> groupids) throws Exception{
        return groupRepo.findAllByIds(groupids);
    }

    @Override
    public List<Group> getAll() throws Exception{
            return groupRepo.findAllAjaxGroup();
    }

    @Override
    public List<Group> getAllEnabled() throws Exception {
        return groupRepo.findAllEnabled();
    }

    @Override
    public Group get(Long id) throws Exception{
            return groupRepo.findGroupById(id);
    }

    @Override
    public Group getGroup(Long id) throws Exception{
            return groupRepo.findOne(id);
    }

    @Override
    public Group getGroup(String name) throws Exception{
        return groupRepo.findByName(name.toLowerCase());
    }

    @Override
    public Group create(String name) throws Exception {

        Group group = new Group();
        group.setName(name);
        group.setActivated(true);
        group = groupRepo.save(group);
        if (group != null) {
            return group;
        }

        return new Group();
    }

    @Override
    public Group createGroup(String name) throws Exception {
        Group group = new Group();
        group.setName(name);
        group.setActivated(true);
        return groupRepo.save(group);
    }

    @Override
    public Group update(String name, Long id) throws Exception {

            Group group = groupRepo.findOne(id);
            if (group != null) {
                group.setName(name);
                return groupRepo.save(group);
            }

        return new Group();
    }

    @Override
    public boolean toggle(Long id) throws Exception{

            Group group = groupRepo.findOne(id);
            if (group != null) {
                group.setActivated(!group.isActivated());
                groupRepo.save(group);
                return true;
            }

        return false;
    }

}

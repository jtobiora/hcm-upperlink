package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Category;
import com.upl.nibss.hcmlib.model.JobRole;
import com.upl.nibss.hcmlib.repo.CategoryRepo;
import com.upl.nibss.hcmlib.service.interfaces.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 02/11/2017.
 */
@Service
public class CategoryService implements ICategoryService{

    private static final Logger logger = LoggerFactory.getLogger(Category.class);

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAll() throws Exception{
        return categoryRepo.findAllCategory();
    }

    @Override
    public List<Category> getAllActivated() throws Exception {
        return categoryRepo.findAllActivated();
    }

    @Override
    public List<String> getAllDepartmentTypesName() throws Exception {
        return categoryRepo.getAllDepartmentTypeNames();
    }

    @Override
    public Category get(Long id) throws Exception {
        return categoryRepo.findOne(id);
    }

    @Override
    public Category getAjaxCategory(Long id) throws Exception {
        return categoryRepo.findCategory(id);
    }

    @Override
    public Category save(Category category) throws Exception{
        return categoryRepo.saveAndFlush(category);
    }

    @Override
    public boolean confirmPriorityExist(int priority) {

        String categoryRepoPriority = categoryRepo.findPriority(priority);
        int value = Integer.valueOf(categoryRepoPriority == null ? "0" : categoryRepoPriority );
        if (value > 0){
            return true;
        }

        return false;
    }

    @Override
    public boolean confirmPriorityExist(int priority, Long id) {

        String categoryRepoPriority = categoryRepo.findPriorityNotThisId(priority,id);
        int value = Integer.valueOf(categoryRepoPriority == null ? "0" : categoryRepoPriority );
        if (value > 0){
            return true;
        }

        return false;
    }

    @Override
    public boolean toggle(Long id) {

        try {
            Category category = categoryRepo.findOne(id);
            if (category != null) {
                category.setActivated(!category.isActivated());
                categoryRepo.save(category);
                return true;
            }
        } catch (Exception e) {
            logger.error("Unable to toggle the Group id : {}", id);
        }

        return false;
    }

    private List<JobRole> getJobRoleTitle(Set<JobRole> jobRoles){

        if (jobRoles == null){
            return new ArrayList<>();
        }

        List<JobRole> jobRoleTitle = new ArrayList<>();
        jobRoles.stream().forEach(jobRoleEO -> jobRoleTitle.add(new JobRole(jobRoleEO.getJobRoleId(), jobRoleEO.getJobTitle())));
        return jobRoleTitle;

    }
}

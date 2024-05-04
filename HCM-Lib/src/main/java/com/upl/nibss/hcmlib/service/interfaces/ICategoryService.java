package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.Category;

import java.util.List;

/**
 * Created by toyin.oladele on 02/11/2017.
 */
public interface ICategoryService {

    List<Category> getAll() throws Exception;

    List<Category> getAllActivated() throws Exception;

    List<String> getAllDepartmentTypesName() throws Exception;

    Category get(Long id) throws Exception;

    Category getAjaxCategory(Long id) throws Exception;

    Category save(Category category) throws Exception;

    boolean confirmPriorityExist(int priority);

    boolean confirmPriorityExist(int priority, Long id);

    boolean toggle(Long id);

}

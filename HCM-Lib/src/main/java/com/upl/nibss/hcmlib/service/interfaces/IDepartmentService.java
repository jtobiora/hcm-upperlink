package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.Department;

import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 10/11/2017.
 */
public interface IDepartmentService {

    List<Department> getAll() throws Exception;

    List<Department> getAllAjaxDepartment() throws Exception;

    List<Department> getAllActivated() throws Exception;

    List<String> getAllDepartmentNames() throws Exception;

    Department get(Long id) throws Exception;

    void updateDepartmentInEmployeeObject(String name, Long id) throws Exception;

    Long getParentDept(Long id) throws Exception;

    Department save(Department department) throws Exception;

    List<Department> save(List<Department> department) throws Exception;

    int getMaxNumberOfPersorOnPerDept(Long departmentId) throws Exception;

    List<Department> getAllByLocation(Long locationId) throws Exception;

    Set<Long> getDepartmentAndItsChildren(Long departmentId) throws Exception;

}

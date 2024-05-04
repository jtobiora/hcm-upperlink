package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Department;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.repo.DepartmentRepo;
import com.upl.nibss.hcmlib.repo.EmployeeRepo;
import com.upl.nibss.hcmlib.service.interfaces.IDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 10/11/2017.
 */
@Service
public class DepartmentService implements IDepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<Department> getAll() throws Exception {
        return departmentRepo.findAll();
    }
//
    @Override
    public List<Department> getAllAjaxDepartment() throws Exception {
        return departmentRepo.findAllAjaxDepartment();
    }

    @Override
    public List<Department> getAllActivated() throws Exception {
        return departmentRepo.findAllActivated();
    }

    @Override
    public List<String> getAllDepartmentNames() throws Exception {
        return departmentRepo.getAllDepartmentNames();
    }

    @Override
    public Department get(Long id) throws Exception {
        return departmentRepo.findOne(id);
    }

    @Override
    public void updateDepartmentInEmployeeObject(String name, Long id) throws Exception {
        employeeRepo.updateEmployeeDepartmentName(name,id);
    }

    @Override
    public Long getParentDept(Long id) throws Exception {
        return departmentRepo.findParentDept(id);
    }

    @Override
    public Department save(Department department) throws Exception {
        return departmentRepo.save(department);
    }

    @Override
    public List<Department> save(List<Department> department) throws Exception {
        return departmentRepo.save(department);
    }

    @Override
    public int getMaxNumberOfPersorOnPerDept(Long departmentId) throws Exception {
        return departmentRepo.numberOfLeaveDaysPerDepartment(departmentId);
    }

    @Override
    public List<Department> getAllByLocation(Long locationId) throws Exception {
        return departmentRepo.getAllByLocation(locationId);
    }

    @Override
    public Set<Long> getDepartmentAndItsChildren(Long departmentId) throws Exception {
        List<Department> departments = departmentRepo.findAllActivated();

        Set<Long> children = new HashSet<>();
        try {
            children = getChildren(children,departmentId, departments);
        } catch (Exception e) {
            logger.error("Exception occurred while trying to get descendant of department_id "+ departmentId,e);
            children.add(departmentId);
        }

        return children;
    }

    private Set<Long> getChildren(Set<Long> childrenDepartment, Long departmentId, List<Department> departments){

        Set<Long> newChildrenDepartment = new HashSet<>();
        newChildrenDepartment.clear();

        departments.forEach(department -> {
            if (departmentId.equals(department.getParentDepartment())){
                childrenDepartment.add(department.getId());
                newChildrenDepartment.add(department.getId());
            }
        });

        if (newChildrenDepartment.size() > 0 ){
            newChildrenDepartment.forEach(childrenDepartmentId -> {
                System.out.println("Trying to get the children of "+childrenDepartmentId);
                    getChildren(childrenDepartment, childrenDepartmentId, departments);
//                childrenDepartment.addAll(getChildren(childrenDepartment, childrenDepartmentId, departments));
            });
        }

        return childrenDepartment;
    }

}

package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.cache.ISessionManager;
import com.upl.nibss.hcmlib.dto.EmployeeObject;
import com.upl.nibss.hcmlib.embeddables.Address;
import com.upl.nibss.hcmlib.embeddables.NameType;
import com.upl.nibss.hcmlib.embeddables.PhoneNo;
import com.upl.nibss.hcmlib.model.Allowance;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.User;
import com.upl.nibss.hcmlib.proxyClass.AjaxEmployeeList;
import com.upl.nibss.hcmlib.repo.EmployeeRepo;
import com.upl.nibss.hcmlib.service.interfaces.IEmployeeService;
import com.upl.nibss.hcmlib.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by toyin.oladele on 10/11/2017.
 */
@Service
public class EmployeeService implements IEmployeeService{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ISessionManager iSessionManager;

    @Override
    public List<Employee> getAll() throws Exception {
        return employeeRepo.findAll();
    }

//    @Override
//    public Page<Employee> getAll(Pageable pageable) throws Exception {
//        return employeeRepo.findAll(pageable);
//    }

    @Override
    public List<Employee> getAllActivated() throws Exception {
        return employeeRepo.getAllActivated();
    }

    @Override
    public Page<EmployeeObject> getAllAjax(Pageable pageable) throws Exception {
        return employeeRepo.findAllAjax(pageable);
    }

    @Override
    public List<EmployeeObject> getAllAjax() throws Exception {
        return employeeRepo.findAllAjax();
    }

    @Override
    public List<EmployeeObject> getAllAjaxById(Long id) throws Exception {
        if (id == null){
            return new ArrayList<>();
        }
        return employeeRepo.findAllAjaxById(id);
    }

    @Override
    public Employee getEmployeeSupervisor(Long id) throws Exception {
        return employeeRepo.getSupervisor(id);
    }

    @Override
    public Employee getEmployeeSecondLevelSupervisor(Long id) throws Exception {
        return employeeRepo.getSecondLevelSupervisor(id);
    }

    @Override
    public List<Address> getEmployeeAddress(Long id) throws Exception {
        Employee employee = employeeRepo.findOne(id);
        if (employee != null){
            return new ArrayList<>(employee.getAddresses());
        }
        return new ArrayList<>();
    }

    @Override
    public List<PhoneNo> getEmployeePhoneNumber(Long id) throws Exception {
        Employee employee = employeeRepo.findOne(id);
        if (employee != null){
            return new ArrayList<>(employee.getPhoneList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Allowance> getEmployeeAllowance(Long id) throws Exception {
        return employeeRepo.getEmployeeAllowances(id);
    }

    @Override
    public List<NameType> getAllEmployeeNames() throws Exception {
        return employeeRepo.findAllEmployeeName();
    }

    @Override
    public List<String> getAllEmployeeEmails() throws Exception {
        return employeeRepo.findAllEmployeeEmail();
    }

    @Override
    public List<String> getAllEmployeeNumber() throws Exception {
        return employeeRepo.findAllEmployeeNumber();
    }

    @Override
    public Integer countOfEmployees() throws Exception {
        return employeeRepo.countOfEmployees();
    }

    @Override
    public Integer countOfEmployeesWithSameEmailAddress(String emailAddress) throws Exception {
        return employeeRepo.countOfEmployeesWithSimilarEmail(emailAddress);
    }

    @Override
    public Integer countOfEmployeesWithSameEmailAddressNotId(String emailAddress, Long id) throws Exception {
        return employeeRepo.countOfEmployeesWithSimilarEmailNotId(emailAddress,id);
    }

    @Override
    public Employee get(Long id) throws Exception {
        return employeeRepo.findOne(id);
    }

    @Override
    public Employee getAjax(Long id) throws Exception {
        return employeeRepo.findAjax(id);
    }

    @Override
    public Employee getActivated(Long id) throws Exception {
        return employeeRepo.findActivatedEmployeeById(id);
    }

    @Override
    public Employee save(Employee employee) throws Exception {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> saveBulk(List<Employee> employee) throws Exception {
        return employeeRepo.save(employee);
    }

    @Override
    public boolean employeeNumberAlreadyExist(String employeeNo) {

        Integer integer = employeeRepo.countOfEmployeeNumber(employeeNo);
        if (integer != null && integer.intValue() < 1){
            return false;
        }

        return true;
    }

    @Override
    public boolean employeeNumberAlreadyExistExceptId(String employeeNo, Long id) {

        Integer integer = employeeRepo.countOfEmployeeNumberNotId(employeeNo, id);
        if (integer != null && integer.intValue() < 1){
            return false;
        }

        return true;
    }

    @Override
    public boolean toggle(Long id, String reason) throws Exception{

        User userByEmployee = iUserService.getUserByEmployee(id);
        if (userByEmployee != null && userByEmployee.getEmployee() != null){
            userByEmployee.setActivated(!userByEmployee.getEmployee().isActivated());
            userByEmployee.getEmployee().setActivated(!userByEmployee.getEmployee().isActivated());
            userByEmployee.getEmployee().setDisableReason(reason);
            if (!userByEmployee.getEmployee().isActivated()){
                iSessionManager.deleteSession(userByEmployee.getCurrentSessionId());
            }
            iUserService.save(userByEmployee);
            return true;
        }

        return false;
    }

    @Override
    public boolean disableUserAndEmployee(Long employeeId, String reason) throws Exception {

        User userByEmployee = iUserService.getUserByEmployee(employeeId);
        if (userByEmployee != null && userByEmployee.getEmployee() != null){
            userByEmployee.setActivated(false);
            userByEmployee.getEmployee().setActivated(false);
            userByEmployee.getEmployee().setDisableReason(reason);

            iSessionManager.deleteSession(userByEmployee.getCurrentSessionId());

            iUserService.save(userByEmployee);
            return true;
        }

        return false;
    }

    @Override
    public List<AjaxEmployeeList> getAllEmployeeInDepts(List<Long> id) throws Exception {
        if(id.size() > 0){
            return employeeRepo.findAllEmployeesInDept(id);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Employee> getAllNotInIds(List<Long> ids) throws Exception{
        if(ids.size() > 0) {
            return employeeRepo.findAllNotInIds(ids);
        }
        return employeeRepo.getAllActivated();
    }

    @Override
    public List<Employee> getEmployeesUnderASupervisor(Long supervisorId) throws Exception {
        return employeeRepo.findAllByEmployeeSupervisorAndActivated(supervisorId);
    }

    @Override
    public List<Employee> getAllEmployeeByIds(List<Long> ids) throws Exception {
        if (ids.size() > 0) {
            return employeeRepo.findAllEmplyeeByIds(ids);
        }
        return new ArrayList<>();
    }

    /**
     * Get the Employees by Tenor(the number of months spent in the organisation)
     * @param numberOfMonths
     * @return
     * @throws Exception
     */
    @Override
    public List<Employee> getEmployeesByTenor(int numberOfMonths) throws Exception {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusMonths(numberOfMonths);
        return employeeRepo.getEmployeesByTenor(localDate.getMonthValue(),localDate.getYear());
    }

    @Override
    public List<Employee> getAllSupervisors() throws Exception {
        return employeeRepo.getAllSupervisors();
    }

    @Override
    public List<Employee> getAllSupervisorTobeUpdatedAsNotSupervisor() throws Exception {
        return employeeRepo.getAllSupervisorTobeUpdatedAsNotSupervisor();
    }

    @Override
    public Employee getSupervisorById(Long supervisorId) throws Exception {
        return employeeRepo.getSupervisorById(supervisorId);
    }

    @Override
    public List<String> getEmailByJobRoleId(Long jobRoleId) throws Exception {
        return employeeRepo.getEmailByJobRoleId(jobRoleId);
    }
}

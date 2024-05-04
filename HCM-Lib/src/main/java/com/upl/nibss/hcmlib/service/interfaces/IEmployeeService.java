package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.dto.EmployeeObject;
import com.upl.nibss.hcmlib.embeddables.Address;
import com.upl.nibss.hcmlib.embeddables.NameType;
import com.upl.nibss.hcmlib.embeddables.PhoneNo;
import com.upl.nibss.hcmlib.model.Allowance;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.proxyClass.AjaxEmployeeList;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by toyin.oladele on 10/11/2017.
 */
public interface IEmployeeService {

    List<Employee> getAll() throws Exception;

//    Page<Employee> getAll(Pageable pageable) throws Exception;

    List<Employee> getAllActivated() throws Exception;

    Page<EmployeeObject> getAllAjax(Pageable pageable) throws Exception;

    List<EmployeeObject> getAllAjax() throws Exception;

    List<EmployeeObject> getAllAjaxById(Long id) throws Exception;

    //Get Employee's Supervisor
    Employee getEmployeeSupervisor(Long id) throws Exception;

    //Get Employee's SecondLevelSupervisor
    Employee getEmployeeSecondLevelSupervisor(Long id) throws Exception;

    //Get Address
    List<Address> getEmployeeAddress(Long id) throws Exception;

    //Get PhoneNumbers
    List<PhoneNo> getEmployeePhoneNumber(Long id) throws Exception;

    //Get Allowances
    List<Allowance> getEmployeeAllowance(Long id) throws Exception;

    List<NameType> getAllEmployeeNames() throws Exception;

    List<String> getAllEmployeeEmails() throws Exception;

    List<String> getAllEmployeeNumber() throws Exception;

    Integer countOfEmployees() throws Exception;

    Integer countOfEmployeesWithSameEmailAddress(String emailAddress) throws Exception;

    Integer countOfEmployeesWithSameEmailAddressNotId(String emailAddress, Long id) throws Exception;

    Employee get(Long id) throws Exception;

    Employee getAjax(Long id) throws Exception;

    Employee getActivated(Long id) throws Exception;

    Employee save(Employee employee) throws Exception;

    List<Employee> saveBulk(List<Employee> employee) throws Exception;

    boolean employeeNumberAlreadyExist(String employeeNo);

    boolean employeeNumberAlreadyExistExceptId(String employeeNo, Long id);

    boolean toggle(Long id, String reason) throws Exception;

    boolean disableUserAndEmployee(Long employeeId, String reason) throws Exception;

    List<AjaxEmployeeList> getAllEmployeeInDepts(List<Long> id) throws  Exception;

    List<Employee> getAllNotInIds(List<Long> ids) throws Exception;

    List<Employee> getAllEmployeeByIds(List<Long> ids) throws Exception;

    List<Employee> getEmployeesUnderASupervisor(Long supervisorId) throws Exception;

    List<Employee> getEmployeesByTenor(int numberOfMonths) throws Exception;

    List<Employee> getAllSupervisors() throws Exception;

    List<Employee> getAllSupervisorTobeUpdatedAsNotSupervisor() throws Exception;

    Employee getSupervisorById(Long supervisorId) throws Exception;

    List<String> getEmailByJobRoleId(Long jobRoleId) throws Exception;

}

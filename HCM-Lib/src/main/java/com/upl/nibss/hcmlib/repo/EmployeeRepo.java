package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.dto.EmployeeObject;
import com.upl.nibss.hcmlib.embeddables.Address;
import com.upl.nibss.hcmlib.embeddables.NameType;
import com.upl.nibss.hcmlib.embeddables.PhoneNo;
import com.upl.nibss.hcmlib.enums.LeaveStatus;
import com.upl.nibss.hcmlib.model.Allowance;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.EmployeeDetails;
import com.upl.nibss.hcmlib.proxyClass.AjaxEmployeeList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 11/11/2017.
 */
@Repository
@Transactional
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.id = :id and e.activated=true")
    Employee findActivatedEmployeeById(@Param("id") Long id);

    @Query("select e from Employee e where e.activated=true and e.deleted = false ")
    List<Employee> getAllActivated();

    @Query("select new com.upl.nibss.hcmlib.dto.EmployeeObject(e.id, e.employeeNo, e.empName, e.email, e.employeePersonDetail, e.expectedResumptionDate, e.actualResumptionDate, e.expectedConfirmationDate, e.confirmationDate, e.confirmationStatus, e.leaveStatus, e.loanEligibility, e.jobRole, e.salary, e.department, e.employeeType, e.employeeSupervisor.id, e.employeeSecondLevelSupervisor.id, e.gradeLevel, e.activated, e.credentials_approved, e.supervisor, e.hcm, e.updateReason, e.disableReason ) from Employee e")
    Page<EmployeeObject> findAllAjax(Pageable pageable);

    @Query("select new com.upl.nibss.hcmlib.dto.EmployeeObject(e.id, e.employeeNo, e.empName, e.email, e.employeePersonDetail, e.expectedResumptionDate, e.actualResumptionDate, e.expectedConfirmationDate, e.confirmationDate, e.confirmationStatus, e.leaveStatus, e.loanEligibility, e.jobRole, e.salary, e.department, e.employeeType, e.employeeSupervisor.id, e.employeeSecondLevelSupervisor.id, e.gradeLevel, e.activated, e.credentials_approved, e.supervisor, e.hcm, e.updateReason, e.disableReason ) from Employee e")
    List<EmployeeObject> findAllAjax();

    @Query("select new com.upl.nibss.hcmlib.dto.EmployeeObject(e.id, e.employeeNo, e.empName, e.email, e.employeePersonDetail, e.expectedResumptionDate, e.actualResumptionDate, e.expectedConfirmationDate, e.confirmationDate, e.confirmationStatus, e.leaveStatus, e.loanEligibility, e.jobRole, e.salary, e.department, e.employeeType, e.employeeSupervisor.id, e.employeeSecondLevelSupervisor.id, e.gradeLevel, e.activated, e.credentials_approved, e.supervisor, e.hcm, e.updateReason, e.disableReason ) from Employee e where e.id = :id")
    List<EmployeeObject> findAllAjaxById(@Param("id") Long id);

    //Get Employee's Supervisor
    @Query("select e.employeeSupervisor from Employee e where e.id = :id")
    Employee getSupervisor(@Param("id") Long id);

    //Get Employee's SecondLevelSupervisor
    @Query("select e.employeeSecondLevelSupervisor from Employee e where e.id = :id")
    Employee getSecondLevelSupervisor(@Param("id") Long id);

    //Get Allowances
    @Query(value = "select a.* from employees_allowances e, allowance a where e.allowances_id = a.id and e.employee_id = :id",nativeQuery = true)
    List<Allowance> getEmployeeAllowances(@Param("id") Long id);

    @Query("select e.empName from Employee e where e.deleted = false and e.activated = true ")
    List<NameType> findAllEmployeeName();

    @Query("select e.email from Employee e where e.deleted = false and e.activated = true ")
    List<String> findAllEmployeeEmail();

    @Query("select e.employeeNo from Employee e")
    List<String> findAllEmployeeNumber();

    @Query("select e from Employee e where e.id = :id")
    Employee findAjax(@Param("id") Long id);

    @Query("select count(e.id) from Employee e where e.employeeNo = :employeeNo ")
    Integer countOfEmployeeNumber(@Param("employeeNo") String employeeNo);

    @Query("select count(e.id) from Employee e where e.employeeNo = :employeeNo and e.id <> :id")
    Integer countOfEmployeeNumberNotId(@Param("employeeNo") String employeeNo, @Param("id") Long id);

    @Query("select count(e.id) from Employee e")
    Integer countOfEmployees();

    @Query("select count(e.id) from Employee e where e.email = :emailAddress")
    Integer countOfEmployeesWithSimilarEmail(@Param("emailAddress") String emailAddress);

    @Query("select count(e.id) from Employee e where e.email = :emailAddress and e.id <> :id")
    Integer countOfEmployeesWithSimilarEmailNotId(@Param("emailAddress") String emailAddress, @Param("id") Long id);

    @Query("select new com.upl.nibss.hcmlib.proxyClass.AjaxEmployeeList(e.id,e.employeeNo, e.empName,e.email,e.actualResumptionDate) from Employee e where e.department.id in :departmentId and e.activated = true")
    List<AjaxEmployeeList> findAllEmployeesInDept(@Param("departmentId") List<Long> departmentId);

//    @Modifying
//    @Query("UPDATE Employee e SET e.activated = CASE e.activated WHEN true THEN false WHEN e.activated = false THEN true ELSE e.activated END WHERE e.id in :ids")
//    boolean toggleNullable(@Param("ids") Set<Long> ids);

    @Modifying
    @Query("UPDATE Employee e SET e.activated = CASE e.activated WHEN true THEN false ELSE true END WHERE e.id in :ids")
    int toggle(@Param("ids") Set<Long> ids);

    @Modifying
    @Query("UPDATE Employee e SET e.activated = CASE e.activated WHEN true THEN false ELSE true END WHERE e.id = :id")
    int toggle(@Param("id") Long ids);

    @Query("select e from Employee e where e.id not in :ids")
    List<Employee> findAllNotInIds(@Param("ids") List<Long> ids);

    @Query("select e from Employee e where e.id in :ids and e.activated = true")
    List<Employee> findAllEmplyeeByIds(@Param("ids") List<Long> ids);

    @Query("select e from Employee e where e.employeeSupervisor.id = :supervisor_id and e.activated = true")
    List<Employee> findAllByEmployeeSupervisorAndActivated(@Param("supervisor_id") Long supervisor_id);

    @Modifying
    @Query("update Employee e set e.department.name = :deptName where e.department.id = :deptId")
    void updateEmployeeDepartmentName(@Param("deptName") String deptName, @Param("deptId") Long deptId);

    @Query(value = "select e.* from employees e where e.activated = true and  MONTH(e.actual_resumption_date) <= :theMonth and YEAR(e.actual_resumption_date) <= :theYear ",nativeQuery = true)
    List<Employee> getEmployeesByTenor(@Param("theMonth") int month, @Param("theYear") int year);

    @Query("select e.employeeSupervisor from Employee e where e.activated = true and e.deleted = false")
    List<Employee> getAllSupervisors();

    @Query("select e from Employee e where e.supervisor = true and e.id not in (select e.employeeSupervisor.id from Employee e where e.activated = true)")
    List<Employee> getAllSupervisorTobeUpdatedAsNotSupervisor();

    @Query("select e.employeeSupervisor from Employee e where e.employeeSupervisor.id = :id and e.activated = true and e.deleted = false")
    Employee getSupervisorById(@Param("id") Long id);

    @Query(value = "SELECT e.* from employee_leave_days el, employees e, leave_record l WHERE el.leave_record_id = l.id and l.employee_id = e.id and el.leave_days = CURRENT_DATE and l.approval_status = 'APPROVED'", nativeQuery = true)
    List<Employee> getEmployeesOnleaveForToday();

    @Query("select e.email from Employee e where e.jobRole.id = :jobRoleId and e.activated = true and e.deleted = false ")
    List<String> getEmailByJobRoleId(@Param("jobRoleId") Long jobRoleId);

    //Get Employees on leave and not on leave
    @Query("select e.empName, e.leaveStatus, e.department from Employee e where e.activated = true and e.deleted = false and e.leaveStatus = :leaveStatus")
    List<Object> getEmployeesByLeaveStatus(@Param("leaveStatus") LeaveStatus leaveStatus);

    @Query("select e.empName, e.leaveStatus, e.department from Employee e where e.activated = true and e.deleted = false and e.leaveStatus = :leaveStatus and e.department.id = :deptId")
    List<Object> getEmployeesByLeaveStatusAndDepartment(@Param("leaveStatus") LeaveStatus leaveStatus, @Param("deptId") Long deptId);

    @Query("select e.empName, e.leaveStatus, e.department from Employee e where e.activated = true and e.deleted = false and e.leaveStatus = :leaveStatus and e.department.id in :deptIds")
    List<Object> getEmployeesByLeaveStatusAndDepartments(@Param("leaveStatus") LeaveStatus leaveStatus, @Param("deptIds") List<Long> deptIds);
}

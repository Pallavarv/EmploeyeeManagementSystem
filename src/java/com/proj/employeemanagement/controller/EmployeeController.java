package com.proj.employeemanagement.controller;

import java.util.Date;
import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;
import com.proj.employeemanagement.service.EmployeeService;
import com.proj.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * EmployeeController class was created to connect EmployeeService class and
 * EmployeeView class.
 *
 * @version 1.01 
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    /** 
     * Inputs details to store and create employee.
     *
     * @param employeeVO  specfic employee detail
     * @return            int if id of the created employee
     */
    public int createEmployee(EmployeeVO employeeVO) throws EmployeeManagementException {
        return employeeService.createEmployee(employeeVO);
    }

    /**
     * Gets specfic employee details using employee id.
     * 
     * @param employeeId  employee id used to fetch details of employee 
     * @return            EmployeeVO specific employee details 
     */
    public EmployeeVO getEmployeeById(int employeeId) throws EmployeeManagementException {
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * Gets all employees details.
     * 
     * @return  List<EmployeeVO> all employees details 
     */
    public List<EmployeeVO> getAllEmployee() throws EmployeeManagementException {
        return employeeService.getAllEmployee();
    }

    /**
     * Gets all projects details.
     *  
     * @return List<ProjectVO> all projects details
     */
    public List<ProjectVO> getAllProject() throws EmployeeManagementException {
        return employeeService.getAllProject();
    }

    /**
     * Validates id is integer and does not allow id to be 0.
     *
     * @param employeeId  employee id to be validated
     * @return            boolean  true if id matches or false
     */
    public boolean isValidId(int employeeId) {
        return employeeService.validateId(employeeId); 
    }
    
    /**
     * Validates id is integer, allows spaces and comma.
     *
     * @param projectId  project id to be validated
     * @return           boolean  true if id matches or false
     */
    public boolean isValidProjectId(String projectId) {
        return employeeService.isValidProjectId(projectId); 
    }

    /**
     * Validates name is alphabet and allows spaces only between names.
     *
     * @param name  employee name to be validated
     * @return      boolean  true if name matches or false
     */
    public boolean isValidName(String name) {
        return employeeService.validateName(name);
    }

    /**
     * Validates phone number is integer, should have only 10 digits with only
     * prefix 0 or +91 is optional and starting digit should be greater than 5.
     *
     * @param phoneNumber  employee phone number to be validated
     * @return             boolean true if phone number matches or false
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        return employeeService.validatePhoneNumber(phoneNumber);
    }

    /**
     * Validates given phone number is unique or not.
     *
     * @param phoneNumber  employee phone number to be checked whether unique or not
     * @return             boolean true if phone duplicate or false
     */
    public boolean isDuplicatePhoneNumber(String phoneNumber) throws EmployeeManagementException {      
        return employeeService.isDuplicatePhoneNumber(phoneNumber);
    }

    /** 
     * Validates whether date is in standard format.
     *
     * @param date  date to be validated
     * @return      Boolean true if age is correct or false
     */
    public boolean validateDate(String date) {
        return employeeService.validateDate(date);
    }

    /**
     * Checks whether enter age above 18 and below 60.
     *
     * @param dob  employee date of birth to be checked for valid age
     * @return     boolean true if age is valid or false
     */
    public boolean isValidAge(Date dob) {       
        return employeeService.isValidAge(dob);
    }

    /**
     * Validates salary is number or decimal and does not allow salary to be 0.
     *
     * @param salary  employee salary to be validated
     * @return        boolean true if salary matches or false
     */
    public boolean isValidSalary(float salary) {
        return employeeService.validateSalary(salary);
    }

    /**
     * Validates email id is in standard email pattern, allows alphabet,
     * number, hypen, underscore and dot allowed in local-part of mail id,
     * allows alphabet, number and hypen in domain part.
     *
     * @param emailId  employee email id to be validated
     * @return         boolean true if email id matches or false
     */
    public boolean isValidEmailId(String emailId) {
        return employeeService.validateEmailId(emailId);
    }

    /**
     * Validates given email id is unique or not.
     *
     * @param emailId  employee email id to be checked whether unique or not
     * @return         boolean true if email id is duplicate or false 
     */
    public boolean isDuplicateEmailId(String emailId) throws EmployeeManagementException {      
        return employeeService.isDuplicateEmailId(emailId); 
    }

    /** 
     * updates all details of employee.
     *
     * @param employeeVO  employee object to update employee 
     * @return            boolean true if details are updated or false
     */
    public boolean updateEmployee(EmployeeVO employeeVO) throws EmployeeManagementException {
        return employeeService.updateEmployee(employeeVO);
    }
    
    /** 
     * Assign project to employee.
     *
     * @param projectIds  user input of project id
     * @param employeeVO  employee object to update employee 
     * @param projects    all the project availabale
     * @return            List<Integer> if details are assign or null
     */
    public List<Integer> assignProjectToEmployee(String projectIds, 
            EmployeeVO employee, List<ProjectVO> projects) throws EmployeeManagementException {
        return employeeService.assignProjectToEmployee(projectIds, employee, projects);
    }
    
    /** 
     * Deassign project to employee.
     *
     * @param projectIds  user input of project id
     * @param employeeVO  employee object to update employee 
     * @return            List<Integer> if details are deassign or null
     */
    public List<Integer> deAssignProjectToEmployee(String projectIds, 
            EmployeeVO employee) throws EmployeeManagementException {
        return employeeService.deAssignProjectToEmployee(projectIds, employee);
    }
    
    /** 
     * Gets projects yet to be assigned.
     *
     * @param projects  list of all projects availabale
     * @param employee  employee used to get assigned project
     * @return          List<ProjectVO>  list of all projects yet to be assigned
     */
    public List<ProjectVO> getProjectsTobeAssigned(List<ProjectVO> projects, 
            EmployeeVO employee) {
        return employeeService.getProjectsTobeAssigned(projects, employee);
    }

    /**
     * Deletes specfic employee using employee id.
     *
     * @param employeeId  id of employee who is to be removed   
     * @return            boolean true if employee is removed or false
     */
    public boolean deleteEmployeeById(int employeeId) throws EmployeeManagementException {
        return employeeService.removeEmployeeById(employeeId);
    }   

    /**
     * Deletes all employees from record.
     *
     * @return  boolean true if all employees are removed or false
     */
    public boolean deleteAllEmployee() throws EmployeeManagementException {
        return employeeService.removeAllEmployee();
    }
}

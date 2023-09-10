package com.proj.employeemanagement.service;

import java.util.Date;
import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;

/**
 * Implements application to store, return, delete and validate employee
 * details.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public interface EmployeeService {

    /** 
     * Creates employee and stores employee details.
     *
     * @param employeeVO  specfic employee details
     * @return            int if id of the created employee 
     */
    public int createEmployee(EmployeeVO employeeVO) throws EmployeeManagementException;

    /** 
     * Fetches specfic employee details using employee id.
     * 
     * @param employeeId  employee id used to fetch details of employee
     * @return            EmployeeVO specfic employee details 
     */
    public EmployeeVO getEmployeeById(int employeeId) throws EmployeeManagementException;

    /** 
     * Fetches all employees details.
     * 
     * @return  List<EmployeeVO>> all employees details 
     */
    public List<EmployeeVO> getAllEmployee() throws EmployeeManagementException;
    
    /**
     * Gets all projects details.
     *  
     * @return List<ProjectVO> all projects details
     */
    public List<ProjectVO> getAllProject() throws EmployeeManagementException;

    /** 
     * Updates all details of a employee.
     *
     * @param employeeVO  employee object to update employee 
     * @return            boolean true if details are updated or false
     */
    public boolean updateEmployee(EmployeeVO employeeVO) throws EmployeeManagementException;
    
    /** 
     * Assign project to employee.
     *
     * @param projectIds  user input of project id
     * @param employee    employee object to update employee 
     * @param projects    all the project availabale
     * @return            List<Integer> if details are assign or null
     */
    public List<Integer> assignProjectToEmployee(String projectIds, 
            EmployeeVO employee, List<ProjectVO> projects) throws EmployeeManagementException;
    
    /** 
     * deassign project to employee.
     *
     * @param projectIds  user input of project id
     * @param employee    employee object to update employee 
     * @return            List<Integer> if details are deassign or null
     */
    public List<Integer> deAssignProjectToEmployee(String projectIds, 
            EmployeeVO employee) throws EmployeeManagementException;
    
    /** 
     * Gets projects yet to be assigned.
     *
     * @param projects  list of all projects availabale
     * @param employee  employee used to get assigned project
     * @return          List<ProjectVO>  list of all projects yet to be assigned
     */
    public List<ProjectVO> getProjectsTobeAssigned(List<ProjectVO> projects, 
            EmployeeVO employee);

    /** 
     * Removes specific employee using employee id.
     *
     * @param employeeId  id of employee who is to be removed 
     * @return            boolean true if employee removed or false
     */
    public boolean removeEmployeeById(int employeeId) throws EmployeeManagementException;   

    /** 
     * Clears all employees from record.
     *
     * @return boolean true if all employee records are removed
     */
    public boolean removeAllEmployee() throws EmployeeManagementException;

    /**
     * Validates id is integer and does not allow id to be 0.
     *
     * @param employeeId  employee id to be validated 
     * @return            boolean  true if id matches or false
     */
    public boolean validateId(int employeeId);
    
    /**
     * Validates id is integer, allows spaces and comma.
     *
     * @param projectId  project id to be validated
     * @return           boolean  true if id matches or false
     */
    public boolean isValidProjectId(String projectId);

    /** 
     * Validates name is alphabet and allows spaces only between names.
     *
     * @param name  employee name to be validated
     * @return      boolean  true if name matches or false
     */
    public boolean validateName(String name);

    /** 
     * Validates phone number is integer, should have only 10 digits with only
     * prefix 0 or +91 is optional and starting digit should be greater than 5.
     *
     * @param phoneNumber  employee phone number to be validated
     * @return             boolean true if name matches or false
     */
    public boolean validatePhoneNumber(String phoneNumber);

    /** 
     * Checks whether the entered employee phone number already exist or not.
     *
     * @param phoneNumber  employee phone number to be checked whether unique or not
     * @return             boolean true if duplicate number or false
     */
    public boolean isDuplicatePhoneNumber(String phoneNumber) throws EmployeeManagementException;

    /** 
     * Validates whether date is in standard format.
     *
     * @param date  date to be validated
     * @return      Boolean true if age is correct or false
     */
    public boolean validateDate(String date);

    /**
     * Checks whether enter age above 18 and below 60.
     *
     * @param dob  employee date of birth to be checked for valid age
     * @return     boolean false if age is valid or false 
     */
    public boolean isValidAge(Date dob);

    /** 
     * Validates salary is number or decimal, less than 10000000 and does not
     * allow salary to be 0.
     *
     * @param salary  employee salary to be validated
     * @return        boolean true if name matches or false
     */
    public boolean validateSalary(float salary);

    /** 
     * Validates email id is in standard email pattern, allows alphabet,
     * number, hypen, underscore and dot allowed in local-part of mail id,
     * allows alphabet, number and hypen in domain part.
     *
     * @param emailId  employee email id to be validated
     * @return         boolean true if email id matches or false
     */
    public boolean validateEmailId(String emailId);

    /** 
     * Checks whether the entered employee email id already exist or not.
     *
     * @param emailId  employee email id to be checked whether unique or not
     * @return         boolean true if duplicate email id or false
     */
    public boolean isDuplicateEmailId(String emailId) throws EmployeeManagementException;   
}

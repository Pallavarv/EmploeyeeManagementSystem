package com.proj.employeemanagement.service;

import java.util.Date;
import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;

/**
 * Implements application to store, return, delete and validate project
 * details.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public interface ProjectService {

    /** 
     * Creates project and stores project details.
     *
     * @param projectVO  specfic project details
     * @return            int if id of the created project 
     */
    public int createProject(ProjectVO projectVO) throws EmployeeManagementException;

    /** 
     * Fetches specfic project details using project id.
     * 
     * @param projectId  project id used to fetch details of project
     * @return            ProjectVO specfic project details 
     */
    public ProjectVO getProjectById(int projectId) throws EmployeeManagementException;

    /** 
     * Fetches all projects details.
     * 
     * @return  List<ProjectVO>> all projects details 
     */
    public List<ProjectVO> getAllProject() throws EmployeeManagementException;
    
    /**
     * Gets all employees details.
     *  
     * @return List<EmployeeVO> all employees details
     */
    public List<EmployeeVO> getAllEmployee() throws EmployeeManagementException;

    /** 
     * Updates all details of a project.
     *
     * @param projectVO  project object to update project 
     * @return            boolean true if details are updated or false
     */
    public boolean updateProject(ProjectVO projectVO) throws EmployeeManagementException;
    
    /** 
     * Assign employee to project.
     *
     * @param employeeIds  user input of project id
     * @param project      project object to update project 
     * @param employees    all the employee availabale
     * @return             List<Integer> if details are assign or null
     */
    public List<Integer> assignEmployeeToProject(String employeeIds, 
            ProjectVO project, List<EmployeeVO> employees) throws EmployeeManagementException;
    
    /** 
     * Deassign employee to project.
     *
     * @param employeeIds  user input of employee id
     * @param project      project object to update project 
     * @return             List<Integer> if details are deassign or null
     */
    public List<Integer> deAssignEmployeeToProject(String employeeIds, 
            ProjectVO project) throws EmployeeManagementException;
    
    /** 
     * Gets employees yet to be assigned.
     *
     * @param employees  list of all employees availabale
     * @param project    project used to get assigned employee
     * @return           List<EmployeeVO>  list of all employees yet to be assigned
     */
    public List<EmployeeVO> getEmployeesTobeAssigned(List<EmployeeVO> employees,
            ProjectVO project);

    /** 
     * Removes specific project using project id.
     *
     * @param projectId  id of project who is to be removed 
     * @return            boolean true if project removed or false
     */
    public boolean removeProjectById(int projectId) throws EmployeeManagementException;   

    /** 
     * Clears all projects from record.
     *
     * @return boolean true if all project records are removed
     */
    public boolean removeAllProject() throws EmployeeManagementException;

    /**
     * Validates id is integer and does not allow id to be 0.
     *
     * @param projectId  project id to be validated 
     * @return            boolean  true if id matches or false
     */
    public boolean validateId(int projectId);
    
    /**
     * Validates id is integer, allows spaces and comma.
     *
     * @param employeeId  employee id to be validated
     * @return            boolean  true if id matches or false
     */
    public boolean isValidEmployeeId(String employeeId);

    /** 
     * Validates name is alphabet and allows spaces only between names.
     *
     * @param name  project name to be validated
     * @return      boolean  true if name matches or false
     */
    public boolean validateName(String name);

    /** 
     * Validates whether date is in standard format.
     *
     * @param date  date to be validated
     * @return      Boolean true if age is correct or false
     */
    public boolean validateDate(String date);

    /**
     * Checks whether entered date is valid.
     *
     * @param date  project date to be checked for valid date
     * @return      boolean false if date is valid or false 
     */
    public boolean isValidDate(Date date);
}

package com.proj.employeemanagement.controller;

import java.util.Date;
import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;
import com.proj.employeemanagement.service.ProjectService;
import com.proj.employeemanagement.service.impl.ProjectServiceImpl;

/**
 * ProjectController class was created to connect ProjectService class and
 * ProjectView class.
 *
 * @version 1.01 
 * @author  Pallavan
 * @since   1.00
 */
public class ProjectController {

    private ProjectService projectService = new ProjectServiceImpl();

    /** 
     * Inputs details to store and create project.
     *
     * @param projectVO  specfic project detail
     * @return           int if id of the created project
     */
    public int createProject(ProjectVO projectVO) throws EmployeeManagementException {
        return projectService.createProject(projectVO);
    }

    /**
     * Gets specfic project details using project id.
     * 
     * @param projectId  project id used to fetch details of project 
     * @return            ProjectVO specific project details 
     */
    public ProjectVO getProjectById(int projectId) throws EmployeeManagementException {
        return projectService.getProjectById(projectId);
    }

    /**
     * Gets all projects details.
     * 
     * @return  List<ProjectVO> all project details 
     */
    public List<ProjectVO> getAllProject() throws EmployeeManagementException {
        return projectService.getAllProject();
    }
    
    /**
     * Gets all employees details.
     *  
     * @return List<EmployeeVO> all employees details
     */
    public List<EmployeeVO> getAllEmployee() throws EmployeeManagementException {
        return projectService.getAllEmployee();
    }

    /**
     * Validates id is integer and does not allow id to be 0.
     *
     * @param projectId  project id to be validated
     * @return            boolean  true if id matches or false
     */
    public boolean isValidId(int projectId) {
        return projectService.validateId(projectId); 
    }
    
    /**
     * Validates id is integer, allows spaces and comma.
     *
     * @param employeeId  employee id to be validated
     * @return            boolean  true if id matches or false
     */
    public boolean isValidEmployeeId(String employeeId) {
        return projectService.isValidEmployeeId(employeeId); 
    }

    /**
     * Validates name is alphabet and allows spaces only between names.
     *
     * @param name  project name to be validated
     * @return      boolean  true if name matches or false
     */
    public boolean isValidName(String name) {
        return projectService.validateName(name);
    }

    /** 
     * Validates whether date is in standard format.
     *
     * @param date  date to be validated
     * @return      Boolean true if age is correct or false
     */
    public boolean validateDate(String date) {
        return projectService.validateDate(date);
    }

    /**
     * Checks whether entered date is valid.
     *
     * @param date  project date to be checked for valid date
     * @return      boolean true if date is valid or false
     */
    public boolean isValidDate(Date date) {       
        return projectService.isValidDate(date);
    }

    /** 
     * updates all details of project.
     *
     * @param projectVO  project object to update project 
     * @return            boolean true if details are updated or false
     */
    public boolean updateProject(ProjectVO projectVO) throws EmployeeManagementException {
        return projectService.updateProject(projectVO);
    }
    
    /** 
     * Assign employee to project.
     *
     * @param employeeIds  user input of project id
     * @param project      project object to update project 
     * @param employees    all the employee availabale
     * @return             List<Integer> if details are assign or null
     */
    public List<Integer> assignEmployeeToProject(String employeeIds, 
            ProjectVO project, List<EmployeeVO> employees) throws EmployeeManagementException {
        return projectService.assignEmployeeToProject(employeeIds, project, employees);
    }
    
    /** 
     * Deassign employee to project.
     *
     * @param employeeIds  user input of employee id
     * @param project      project object to update project 
     * @return             List<Integer> if details are deassign or null
     */
    public List<Integer> deAssignEmployeeToProject(String employeeIds, 
            ProjectVO project) throws EmployeeManagementException {
        return projectService.deAssignEmployeeToProject(employeeIds, project);
    }
    
    /** 
     * Gets employee yet to be assigned.
     *
     * @param employees  list of all employees availabale
     * @param project  project used to get assigned employee
     * @return           List<EmployeeVO>  list of all projects yet to be assigned
     */
    public List<EmployeeVO> getEmployeesTobeAssigned(List<EmployeeVO> employees, 
            ProjectVO project) {
        return projectService.getEmployeesTobeAssigned(employees, project);
    }

    /**
     * Deletes specfic project using project id.
     *
     * @param projectId  id of project who is to be removed   
     * @return            boolean true if project is removed or false
     */
    public boolean deleteProjectById(int projectId) throws EmployeeManagementException {
        return projectService.removeProjectById(projectId);
    }   

    /**
     * Deletes all projects from record.
     *
     * @return  boolean true if all projects are removed or false
     */
    public boolean deleteAllProject() throws EmployeeManagementException {
        return projectService.removeAllProject();
    }
}

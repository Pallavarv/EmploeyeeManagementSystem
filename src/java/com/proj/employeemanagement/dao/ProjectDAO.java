package com.proj.employeemanagement.dao;

import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.persistence.Project;

/**
 * ProjectDAO class connects service class and databases and perform various 
 * CRUD operation on database.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public interface ProjectDAO {

    /** 
     * Saves project details into table.
     *
     * @param project  project object to get all details
     * @return         int if project details are inserted in table
     */
    public int saveProject(Project project) throws EmployeeManagementException;

    /** 
     * Fetchs a project all detail from table using project id.
     *
     * @param projectId  project id whose detail to be fetched
     * @return           project project object to get all details
     */
    public Project fetchProjectById(int projectId) throws EmployeeManagementException;

    /** 
     * Fetchs all projects details from table.
     *
     * @return List<Project> list of project object 
     */
    public List<Project> fetchAllProject() throws EmployeeManagementException;

    /** 
     * Updates project's all details in the table.
     *
     * @param project  instance of project used to update detail
     * @return         Project if project details are updated 
     */
    public Project updateProject(Project project) throws EmployeeManagementException;

    /** 
     * Deletes single project record from table.
     *
     * @param projectId  project id whose record to be removed
     * @return           int if record is removed
     */
    public int deleteProjectById(int projectId) throws EmployeeManagementException;

    /** 
     * Deletes project's all records from table.
     *
     * @return  int if record is removed
     */
    public int deleteAllProject() throws EmployeeManagementException;      
}

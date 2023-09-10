package com.proj.employeemanagement.dao;

import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.persistence.Employee;

/**
 * EmployeeDAO class connects service class and databases and perform various 
 * CRUD operation on database.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public interface EmployeeDAO {

    /** 
     * Saves employee details into table.
     *
     * @param employee  employee object to get all details
     * @return          int if employee details are inserted in table
     */
    public int saveEmployee(Employee employee) throws EmployeeManagementException;

    /** 
     * Fetchs a employee all detail from table using employee id.
     *
     * @param employeeId  employee id whose detail to be fetched
     * @return            employee employee object to get all details
     */
    public Employee fetchEmployeeById(int employeeId) throws EmployeeManagementException;

    /** 
     * Fetchs all employees details from table.
     *
     * @return List<Employee> list of employee object 
     */
    public List<Employee> fetchAllEmployee() throws EmployeeManagementException;

    /** 
     * Updates employee's all details in the table.
     *
     * @param employee  instance of employee used to update detail
     * @return          Employee if employee details are updated 
     */
    public Employee updateEmployee(Employee employee) throws EmployeeManagementException;

    /** 
     * Deletes single employee record from table.
     *
     * @param employeeId  employee id whose record to be removed
     * @return            int if record is removed
     */
    public int deleteEmployeeById(int employeeId) throws EmployeeManagementException;

    /** 
     * Deletes employee's all records from table.
     *
     * @return  int if record is removed
     */
    public int deleteAllEmployee() throws EmployeeManagementException; 

    /** 
     * Checks whether the entered employee phone number already exist or not.
     *
     * @param phoneNumber  employee phone number to be checked whether unique or not
     * @return             Employee true if duplicate number or false
     */
    public Employee fetchEmployeeByPhoneNumber(String phoneNumber) throws EmployeeManagementException;   

    /** 
     * Checks whether the entered employee email id already exist or not.
     *
     * @param emailId  employee email id to be checked whether unique or not
     * @return         Employee true if duplicate email id or false
     */
    public Employee fetchEmployeeByEmailId(String emailId) throws EmployeeManagementException;     
}

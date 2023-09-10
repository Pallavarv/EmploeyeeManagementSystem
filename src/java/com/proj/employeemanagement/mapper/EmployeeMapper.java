package com.proj.employeemanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.proj.employeemanagement.persistence.Address;
import com.proj.employeemanagement.persistence.Employee;
import com.proj.employeemanagement.persistence.Project;
import com.proj.employeemanagement.model.AddressVO;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;

/**
 * EmployeeMapper class is used to convert EmployeeVO object to Employee
 * object and vice versa.
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeMapper {

    /** 
     * Converts EmployeeVO object to Employee object.
     *
     * @param employeeVO  instance of EmployeeVO to be converted
     * @return            Employee if employeeVO is converted
     */
    public static Employee convertEmployeeVOToEmployee(EmployeeVO employeeVO) {
        Employee employee = new Employee();
        List<Address> addresses = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        
        employee.setId(employeeVO.getId());
        employee.setName(employeeVO.getName());
        employee.setPhoneNumber(employeeVO.getPhoneNumber());
        employee.setDob(employeeVO.getDob());
        employee.setSalary(employeeVO.getSalary());
        employee.setEmailId(employeeVO.getEmailId());
        return employee;
    }

    /**
     * Converts Employee object to EmployeeVO object.
     * 
     * @param employee  instance of Employee to be converted
     * @return          EmployeeVO if employee is converted
     */
    public static EmployeeVO convertEmployeeToEmployeeVO(Employee employee) {
        EmployeeVO employeeVO = new EmployeeVO();  
        List<AddressVO> addressesVO = new ArrayList<>();
        List<ProjectVO> projectsVO = new ArrayList<>();
          
        employeeVO.setId(employee.getId());
        employeeVO.setName(employee.getName());
        employeeVO.setPhoneNumber(employee.getPhoneNumber());
        employeeVO.setDob(employee.getDob());
        employeeVO.setSalary(employee.getSalary());
        employeeVO.setEmailId(employee.getEmailId());
        return employeeVO;
    }
    
    /** 
     * Converts list of EmployeeVO object to list of Employee object.
     *
     * @param employeesVO  list of instance of EmployeeVO to be converted
     * @return             list of Employee if employeeVO is converted
     */
    public static List<Employee> convertEmployeesVOToEmployees(List<EmployeeVO> employeesVO) {
        List<Employee> employees = new ArrayList<>();
        
        for (EmployeeVO employeeVO : employeesVO) {
                employees.add(convertEmployeeVOToEmployee(employeeVO));
        }
        return employees;
    }
    
    /**
     * Converts list of Employee object to list of EmployeeVO object.
     * 
     * @param employees  list of instance of Employee to be converted
     * @return           list of EmployeeVO if employee is converted
     */
    public static List<EmployeeVO> convertEmployeesToEmployeesVO(List<Employee> employees) {
        List<EmployeeVO> employeesVO = new ArrayList<>();
        
        for (Employee employee : employees) {
                employeesVO.add(convertEmployeeToEmployeeVO(employee));
        }
        return employeesVO;
    }
}

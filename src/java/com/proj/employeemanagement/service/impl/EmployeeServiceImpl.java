package com.proj.employeemanagement.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.proj.employeemanagement.dao.EmployeeDAO;
import com.proj.employeemanagement.dao.impl.EmployeeDAOImpl;
import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.mapper.AddressMapper;
import com.proj.employeemanagement.mapper.EmployeeMapper;
import com.proj.employeemanagement.mapper.ProjectMapper;
import com.proj.employeemanagement.persistence.Employee;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;
import com.proj.employeemanagement.service.EmployeeService;
import com.proj.employeemanagement.service.ProjectService;
import com.proj.employeemanagement.util.DateUtil;

/**
 * Implements methods to store, get, update and delete employee detail 
 * by connecting EmployeeDAO.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public int createEmployee(EmployeeVO employeeVO) throws EmployeeManagementException {
        Employee employee = EmployeeMapper.convertEmployeeVOToEmployee(employeeVO);
        employee.setAddresses(AddressMapper.convertAddressesVOToAddresses(employeeVO
                .getAddresses()));
        return (employeeDAO.saveEmployee(employee));
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public EmployeeVO getEmployeeById(int employeeId) throws EmployeeManagementException {
        EmployeeVO employeeVO = null;
        Employee employee = employeeDAO.fetchEmployeeById(employeeId);
        
        if (null != employee) {
            employeeVO = EmployeeMapper.convertEmployeeToEmployeeVO(employee);
            
            if (null != employee.getProjects()) {
                employeeVO.setProjects(ProjectMapper.convertProjectsToProjectsVO(employee
                        .getProjects()));
            }
            if (null != employee.getAddresses()) {
                employeeVO.setAddresses(AddressMapper.convertAddressesToAddressesVO(employee
                        .getAddresses()));
            }
        }
        return employeeVO;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeVO> getAllEmployee() throws EmployeeManagementException {
        List<Employee> employeeRecords = employeeDAO.fetchAllEmployee();
        List<EmployeeVO> employees = new ArrayList<>();

        if (null != employeeRecords) {
            for (Employee employee : employeeRecords) {
                EmployeeVO employeeVO = EmployeeMapper.convertEmployeeToEmployeeVO(employee);
            
                if (null != employee.getAddresses()) {
                    employeeVO.setAddresses(AddressMapper.convertAddressesToAddressesVO(employee
                            .getAddresses()));
                }
                employees.add(employeeVO);
            }
        } else {
            employees = null;
        }
        return employees;
    }  
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<ProjectVO> getAllProject() throws EmployeeManagementException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getAllProject();
    }  
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<Integer> assignProjectToEmployee(String projectIds, 
            EmployeeVO employee, List<ProjectVO> projects) throws EmployeeManagementException {
        boolean isAvailableProject = true;
        String[] idsOfProject = projectIds.replaceAll("\\s", "").split(",");
        List<Integer> unAvailableProject = new ArrayList<>();
        
        for (int index = 0; index < idsOfProject.length; index++) {
            isAvailableProject = false;
            
            for (ProjectVO project : projects) {   
                if (Integer.parseInt(idsOfProject[index]) == project.getId()) {
                    isAvailableProject = true;
                    employee.getProjects().add(project);
                }
            }            
            if (false == isAvailableProject) {
                unAvailableProject.add(Integer.valueOf(idsOfProject[index]));
            }
        }    
        return ((idsOfProject.length > unAvailableProject.size()) 
                ? (true == updateEmployee(employee) ? null : unAvailableProject) 
                : unAvailableProject);
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<Integer> deAssignProjectToEmployee(String projectIds, 
            EmployeeVO employee) throws EmployeeManagementException {
        boolean isAssignedProject = false; 
        String[] idsOfProject = projectIds.replaceAll("\\s", "").split(",");
        List<Integer> unAvailableProject = new ArrayList<>();
        
        for (int index = 0; index < idsOfProject.length; index++) {
            isAssignedProject = false;
            
            for (int listIndex = 0; listIndex < employee.getProjects().size(); listIndex++) {
                if (Integer.parseInt(idsOfProject[index]) == employee.getProjects()
                        .get(listIndex).getId()) {
                    isAssignedProject = true;
                    employee.getProjects().remove(listIndex);
                }
            }
            if (false == isAssignedProject) {
                unAvailableProject.add(Integer.valueOf(idsOfProject[index]));
            }
        }        
        return ((idsOfProject.length > unAvailableProject.size()) 
                ? (true == updateEmployee(employee) ? null : unAvailableProject) 
                : unAvailableProject);
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean updateEmployee(EmployeeVO employeeVO) throws EmployeeManagementException { 
        Employee employee = EmployeeMapper.convertEmployeeVOToEmployee(employeeVO);
        employee.setProjects(ProjectMapper.convertProjectsVOToProjects(employeeVO
                .getProjects()));    
        employee.setAddresses(AddressMapper.convertAddressesVOToAddresses(employeeVO
                .getAddresses()));   
        return (null != employeeDAO.updateEmployee(employee));
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<ProjectVO> getProjectsTobeAssigned(List<ProjectVO> projects, 
            EmployeeVO employee) {
        List<Integer> assignedProjects = new ArrayList();
        
        for (ProjectVO project : projects) {
            for (ProjectVO employeeProject : employee.getProjects()) {
                if (employeeProject.getId() == project.getId()) {
                    assignedProjects.add(projects.indexOf(project));
                }
            }
        }

        for(int index = assignedProjects.size() - 1; index >= 0; index--) {
            projects.remove(projects.get(assignedProjects.get(index)));
        }
        return (projects.isEmpty() ? null : projects);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean removeEmployeeById(int employeeId) throws EmployeeManagementException {
        return (0 != employeeDAO.deleteEmployeeById(employeeId));
    }   

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean removeAllEmployee() throws EmployeeManagementException {
        return (0 != employeeDAO.deleteAllEmployee());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateId(int employeeId) {
        return (0 < employeeId);
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isValidProjectId(String projectId) {
        return validateInput(projectId, "^[1-9\\s\\,]*$"); 
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateName(String name) {
        return validateInput(name, "^[a-zA-Z][a-zA-Z]+([ ]?[a-zA-Z]?[a-zA-Z]*)?"
                + "([ ]?[a-zA-Z]?[a-zA-Z]*)?$");       
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return validateInput(phoneNumber, "^(0|\\+91)?[6-9][0-9]{9}$");
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isDuplicatePhoneNumber(String phoneNumber) throws EmployeeManagementException {
        return (null != employeeDAO.fetchEmployeeByPhoneNumber(phoneNumber));
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateDate(String date) {
        return DateUtil.validateDate(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidAge(Date dob) {
        LocalDate localDate = DateUtil.convertDateToLocalDate(dob);
        Period age = Period.between(localDate, LocalDate.now());
        return DateUtil.validateAge(age);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateSalary(float salary) {
        return (10000000 > salary  && 0 < salary);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateEmailId(String emailId) {
        return validateInput(emailId, "^[a-z][a-zA-Z0-9]{2,38}([_\\-\\.]{0,5}"
                + "[a-zA-Z0-9]{1,20})?[@][a-zA-Z][a-zA-Z0-9\\-]{3,254}[.]"
                + "[a-z]{3,6}([.][a-z]{2,5})?$");
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isDuplicateEmailId(String emailId) throws EmployeeManagementException {
        return (null != employeeDAO.fetchEmployeeByEmailId(emailId)); 
    }

    /**
     * Validates whether given detail matches with given regex pattern.
     *
     * @param detailToValidated  employee detail to be validated 
     * @param regex              regex pattern used for validation
     * @return                   boolean  true if detail matches or false
     */
    private boolean validateInput(String detailToValidated, String regex) {
        return detailToValidated.matches(regex);
    }   
}

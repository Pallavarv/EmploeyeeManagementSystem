package com.proj.employeemanagement.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.proj.employeemanagement.dao.ProjectDAO;
import com.proj.employeemanagement.dao.impl.ProjectDAOImpl;
import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.mapper.EmployeeMapper;
import com.proj.employeemanagement.mapper.ProjectMapper;
import com.proj.employeemanagement.persistence.Project;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;
import com.proj.employeemanagement.service.EmployeeService;
import com.proj.employeemanagement.service.ProjectService;
import com.proj.employeemanagement.util.DateUtil;

/**
 * Implements methods to store, get, update and delete project detail 
 * by connecting ProjectDAO.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public class ProjectServiceImpl implements ProjectService {

    private ProjectDAO projectDAO = new ProjectDAOImpl();
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public int createProject(ProjectVO projectVO) throws EmployeeManagementException {
        return (projectDAO.saveProject(ProjectMapper
                .convertProjectVOToProject(projectVO)));
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public ProjectVO getProjectById(int projectId) throws EmployeeManagementException {
        ProjectVO projectVO = null;
        Project project = projectDAO.fetchProjectById(projectId);
        
        if (null != project) {
            projectVO = ProjectMapper.convertProjectToProjectVO(project);
            if (null != project.getEmployees()) {
                projectVO.setEmployees(EmployeeMapper.convertEmployeesToEmployeesVO(project
                        .getEmployees()));
            }
        }
        return projectVO;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public List<ProjectVO> getAllProject() throws EmployeeManagementException {
        List<Project> projectRecords = projectDAO.fetchAllProject();
        List<ProjectVO> projects = new ArrayList<>();
        
        if (null != projectRecords) {
            for (Project project : projectRecords) {
                ProjectVO projectVO = null;
                
                if (null != project) {
                    projectVO = ProjectMapper.convertProjectToProjectVO(project);
                }
                projects.add(projectVO);
            }
        } else {
            projects = null;
        }
        return projects;
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeVO> getAllEmployee() throws EmployeeManagementException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getAllEmployee();
    }     

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean updateProject(ProjectVO projectVO) throws EmployeeManagementException {
        Project project = ProjectMapper.convertProjectVOToProject(projectVO);
        project.setEmployees(EmployeeMapper.convertEmployeesVOToEmployees(projectVO
                .getEmployees()));  
        return (null != projectDAO.updateProject(project));
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeVO> getEmployeesTobeAssigned(List<EmployeeVO> employees, 
            ProjectVO project) {
        List<Integer> assignedEmployees = new ArrayList();

        for (EmployeeVO employee : employees) {
            for (EmployeeVO projectEmployee : project.getEmployees()) {
                if (projectEmployee.getId() == employee.getId()) {
                    assignedEmployees.add(employees.indexOf(employee));
                }
            }
        }

        for(int index = assignedEmployees.size() - 1; index >= 0; index--) {
            employees.remove(employees.get(assignedEmployees.get(index)));
        }
        return (employees.isEmpty() ? null : employees);
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<Integer> assignEmployeeToProject(String employeeIds, 
            ProjectVO project, List<EmployeeVO> employees) throws EmployeeManagementException {
        boolean isAvailableEmployee = true;
        String[] idsOfEmployee = employeeIds.replaceAll("\\s", "").split(",");
        List<Integer> unAvailableEmployee = new ArrayList<>();
        
        for (int index = 0; index < idsOfEmployee.length; index++) {
            isAvailableEmployee = false;
            for (EmployeeVO employee : employees) {   
                if (Integer.parseInt(idsOfEmployee[index]) == employee.getId()) {
                    isAvailableEmployee = true;
                    project.getEmployees().add(employee);
                }
            }            
            if (false == isAvailableEmployee) {
                unAvailableEmployee.add(Integer.valueOf(idsOfEmployee[index]));
            }
        }    
        return ((idsOfEmployee.length > unAvailableEmployee.size()) 
                ? (true == updateProject(project) ? null : unAvailableEmployee) 
                : unAvailableEmployee);
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<Integer> deAssignEmployeeToProject(String employeeIds, 
            ProjectVO project) throws EmployeeManagementException {
        boolean isAssignedEmployee = false; 
        String[] idsOfEmployee = employeeIds.replaceAll("\\s", "").split(",");
        List<Integer> unAvailableEmployee = new ArrayList<>();
        
        for (int index = 0; index < idsOfEmployee.length; index++) {
            isAssignedEmployee = false;
            for (int listIndex = 0; listIndex < project.getEmployees().size(); listIndex++) {
                if (Integer.parseInt(idsOfEmployee[index]) == project.getEmployees()
                        .get(listIndex).getId()) {
                    isAssignedEmployee = true;
                    project.getEmployees().remove(listIndex);
                }
            }
            if (false == isAssignedEmployee) {
                unAvailableEmployee.add(Integer.valueOf(idsOfEmployee[index]));
            }
        }                
        return  ((idsOfEmployee.length > unAvailableEmployee.size()) 
                ? (true == updateProject(project) ? null : unAvailableEmployee) 
                : unAvailableEmployee);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean removeProjectById(int projectId) throws EmployeeManagementException {
        return (0 != projectDAO.deleteProjectById(projectId));
    }   

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean removeAllProject() throws EmployeeManagementException {
        return (0 != projectDAO.deleteAllProject());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateId(int projectId) {
        return (0 < projectId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEmployeeId(String employeeId) {
        return validateInput(employeeId, "^[1-9\\s\\,]*$"); 
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
    public boolean validateDate(String date) {
        return DateUtil.validateDate(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidDate(Date date) {
        LocalDate localDate = DateUtil.convertDateToLocalDate(date);
        Period startDate = Period.between(localDate, LocalDate.now());
        return DateUtil.validateProjectDate(startDate);
    }

    /**
     * Validates whether given detail matches with given regex pattern.
     *
     * @param detailToValidated  project detail to be validated 
     * @param regex              regex pattern used for validation
     * @return                   boolean  true if detail matches or false
     */
    private boolean validateInput(String detailToValidated, String regex) {
        return detailToValidated.matches(regex);
    }   
}

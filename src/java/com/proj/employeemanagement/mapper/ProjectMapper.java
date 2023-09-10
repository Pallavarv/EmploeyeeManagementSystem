package com.proj.employeemanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.proj.employeemanagement.persistence.Employee;
import com.proj.employeemanagement.persistence.Project;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;

/**
 * ProjectMapper class is used to convert ProjectVO object to Project
 * object and vice versa.
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class ProjectMapper {

    /** 
     * Converts ProjectVO object to Project object.
     *
     * @param projectVO  instance of ProjectVO to be converted
     * @return           Project if projectVO is converted
     */
    public static Project convertProjectVOToProject(ProjectVO projectVO) {
        Project project = new Project();
        List<Employee> employees = new ArrayList<>();
        
        project.setId(projectVO.getId());
        project.setName(projectVO.getName());
        project.setDomain(projectVO.getDomain());
        project.setStartDate(projectVO.getStartDate());
        project.setManagerName(projectVO.getManagerName());
        return project;
    }

    /**
     * Converts Project object to ProjectVO object.
     * 
     * @param project  instance of Project to be converted
     * @return         ProjectVO if project is converted
     */
    public static ProjectVO convertProjectToProjectVO(Project project) {
        ProjectVO projectVO = new ProjectVO();  
        List<EmployeeVO> employeesVO = new ArrayList<>();
          
        projectVO.setId(project.getId());
        projectVO.setName(project.getName());
        projectVO.setDomain(project.getDomain());
        projectVO.setStartDate(project.getStartDate());
        projectVO.setManagerName(project.getManagerName());
        return projectVO;
    }
    
    /** 
     * Converts list of ProjectVO object to list of Project object.
     *
     * @param projectsVO  list of instance of ProjectVO to be converted
     * @return            list of Project if projectVO is converted
     */
    public static List<Project> convertProjectsVOToProjects(List<ProjectVO> projectsVO) {
        List<Project> projects = new ArrayList<>();
        
        for (ProjectVO projectVO : projectsVO) {
                projects.add(convertProjectVOToProject(projectVO));
        }
        return projects;
    }
    
    /**
     * Converts list of Project object to list of ProjectVO object.
     * 
     * @param projects  list of instance of Project to be converted
     * @return          list of ProjectVO if project is converted
     */
    public static List<ProjectVO> convertProjectsToProjectsVO(List<Project> projects) {
        List<ProjectVO> projectsVO = new ArrayList<>();

        for (Project project : projects) {
                projectsVO.add(convertProjectToProjectVO(project));
        }
        return projectsVO;
    }
}

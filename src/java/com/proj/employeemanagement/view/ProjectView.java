package com.proj.employeemanagement.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.proj.employeemanagement.controller.ProjectController;
import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.logger.EmployeeManagementLogger;
import com.proj.employeemanagement.model.ProjectVO;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.util.DateUtil;

/**
 * Implements a application for inputting, validating and viewing all project 
 * details to perform create, view, update and delete operation on it.
 *
 * @version 1.03
 * @author  Pallavan
 * @since   1.00
 */
public class ProjectView {

    private Scanner scanner = new Scanner(System.in);
    private ProjectController projectController = new ProjectController();
    
    /**
     * Asks inputs from the user to create project and prints message   
     * whether project is created or not. 
     */
    private void createProject() {
        int projectId = 0;
        String name = getAndValidateName();   
        String domain = getAndValidateDomain();
        Date startDate = getAndValidateStartDate();
        String managerName = getAndValidateManagerName();
        
        try {            
            projectId = projectController.createProject(new ProjectVO(name,
                    domain, startDate, managerName));
        
            if (0 != projectId) {
                System.out.println("Project was created\nProject id is: " 
                + projectId);
            } else {
                System.out.println("Project was not created!!!");
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }

    /**
     * Asks user select a option continuously for displaying specific project
     * detail or all project details till user chooses to exit.
     */
    private void viewProject() {
        boolean isValidChoice = true;
        int userChoice;
        StringBuilder preference = new StringBuilder();
        preference.append("\n1. single project\n2. all projects\n")
                .append("3. exit\n");

        while (isValidChoice) {
            System.out.print(preference);
            userChoice = getAndValidateChoiceOfUser();
            isValidChoice = executeViewOperation(userChoice);
        }
    }

    /**
     * Displays detail of specfic project using project id or displays
     * detail of all projects.
     * 
     * @param userChoice  choice selected by user to display project detail
     * @return            boolean false if choose is 3 or true 
     */
    private boolean executeViewOperation(int userChoice) {
        boolean isValidChoice = true;

        switch (userChoice) {
            case 1: 
                viewProjectById();
                break;
            case 2:
                viewAllProject();
                break;
            case 3:
                isValidChoice = false;
                break;
            default:
                System.out.println("Enter a valid choose!!!\n");
                break;
        }
        return isValidChoice;
    }
    
    /*
     * Displays specific project details.
     */
    private void viewProjectById() {
        int projectId = getAndValidateId();
        
        try {
            ProjectVO project = projectController.getProjectById(projectId); 
                  
            if (null != project) {
                List<EmployeeVO> employees = project.getEmployees();
                if (null != employees) {             
                    System.out.println(project);
                    for (EmployeeVO employee : employees) {
                        System.out.println(employee);
                    }
                }
            } else {
                getProjectId();
                System.out.println("Enter valid project id!!!");
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        } 
    }

    /*
     * Displays all project details.
     */    
    private void viewAllProject() {
    
        try {
            List<ProjectVO> projects = projectController.getAllProject();
            if (null != projects) {
                for (ProjectVO projectDetail : projects) {                          
                    System.out.println(projectDetail);                
                }
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }
    
    /**
     * Displays all project id and its corresponding project name.
     */    
    private void getProjectId() {
        StringBuilder projectId = new StringBuilder();
        
        try {
            System.out.println("---List of projects---\np.id p.name");
            for (ProjectVO project : projectController.getAllProject()) {
                projectId.append(project.getId()).append("    ")
                        .append(project.getName()).append("\n");
            } 
            System.out.println(projectId);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }
       
    /**
     * Displays employees yet to be assigned to project.
     *
     * @param project  project used to get assigned employee
     * @return         List<EmployeeVO> employees yet to be assigned
     */    
    private List<EmployeeVO> getEmployee(ProjectVO project) {
        StringBuilder employeeId = new StringBuilder();
        List<EmployeeVO> employees = null;
        
        try {
            employees = projectController.getAllEmployee();        
            
            if (project.getEmployees().isEmpty()) {        
                System.out.println("---List of employee---\ne.id e.name");
                for (EmployeeVO employee : employees) {
                    employeeId.append(employee.getId()).append("     " )
                            .append(employee.getName()).append("\n");
                }
            } else {
                System.out.println("---List of employee---\ne.id e.name");
                
                for (EmployeeVO employee : projectController
                        .getEmployeesTobeAssigned(employees, project)) {
                    employeeId.append(employee.getId()).append("     " )
                            .append(employee.getName()).append("\n");
                }
            }        
            System.out.println(employeeId);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
        return employees;
    }     

    
    /**
     * Asks user select a option continuously for updating specific project 
     * detail or all project details till user chooses to exit.
     */
    private void updateProject() {
        int projectId = getAndValidateId();
        
        try {
            ProjectVO project = projectController.getProjectById(projectId);  
            
            if (null != project) {
                boolean isValidChoice = true;
                int userChoice;
                StringBuilder preference = new StringBuilder();
                preference.append("\n1. to update individual project detail")
                        .append("\n2. to update all project details\n3. assign")
                        .append(" employee to project\n4. deassign employee from")
                        .append(" from project\n5. exit\n");

                while (isValidChoice) {
                    System.out.print(preference);
                    userChoice = getAndValidateChoiceOfUser();
                    isValidChoice = executeUpdateOperation(userChoice, project);
                } 
            } else {
                getProjectId();
                System.out.println("Enter valid project id!!!");
            }  
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }       
    }

    /**
     * Updates specific detail or all details of the project using id.
     *
     * @param userChoice  choose of the user to update project detail
     * @param project     project as input to update operation
     * @return            boolean true if choose is 3 or false 
     */
    private boolean executeUpdateOperation(int userChoice, ProjectVO project) {
        boolean isValidChoice = true;

        switch (userChoice) {
            case 1:
                updateSpecificDetail(project);  
                break;                
            case 2:
                updateProject(project);
                break;
            case 3:
                assignEmployee(project);
                break;
            case 4:
                deAssignEmployee(project);
                break;
            case 5:
                isValidChoice = false;
                break;
            default:
                System.out.println("Enter a valid choose!!!\n");
                break;
        }
        return isValidChoice;
    }

    /**
     * Asks user to choose which specific detail to update and updates it.
     *
     * @param projectVO  project used to update specific detail of that project
     */
    private void updateSpecificDetail(ProjectVO projectVO) {
        int userChoice;
        boolean isDetailUpdated = false;
        StringBuilder preference = new StringBuilder();
        preference.append("1. project name\n2. project domain\n")
                .append("3. project start date\n4. project manager name\n");
        
        System.out.print(preference);
        userChoice = getAndValidateChoiceOfUser();
        isDetailUpdated = updateDetail(projectVO, userChoice);
        System.out.println(true == isDetailUpdated ? "Project detail updated!!!" 
                : "Project detail not updated!!!");
    }
    
    /*
     * Updates individual detail of a project.
     *
     * @param projectVO  project object used to update detail
     * @param userChoice  user choice of which detail to be updated
     * @param projectId  project id of project whose detail to be updated
     * @return            Boolean true if detail updated or false
     */
    private boolean updateDetail(ProjectVO projectVO, int userChoice) {
        boolean isDetailUpdated = false;
        
        switch (userChoice) {
            case 1:
                projectVO.setName(getAndValidateName());
                break;
            case 2:
                projectVO.setDomain(getAndValidateDomain());
                break;
            case 3:
                projectVO.setStartDate(getAndValidateStartDate());
                break;
            case 4:
                projectVO.setManagerName(getAndValidateManagerName());
                break;                
            default:
                System.out.println("\nPlease enter a valid choice!!!");
                break;
        }
        
        try {
            isDetailUpdated = (4 >= userChoice && 0 < userChoice &&
                    projectController.updateProject(projectVO)) ? true : false;
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
        return isDetailUpdated;
    }

    /**
     * Asks user for input to update all project details using project id.
     *
     * @param projectVO  project to update all details of that project 
     */
    private void updateProject(ProjectVO projectVO) {
        boolean isDetailsUpdated;
   
        projectVO.setName(getAndValidateName());
        projectVO.setDomain(getAndValidateDomain());
        projectVO.setStartDate(getAndValidateStartDate());
        projectVO.setManagerName(getAndValidateManagerName());
        
        try {               
            isDetailsUpdated =  projectController.updateProject(projectVO);

            System.out.println(true == isDetailsUpdated 
                   ? "Project details updated!!!" 
                   : "Project details not updated!!!");
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    } 
    
    /**
     * Asks user to choose which employee to assign to project.
     *
     * @param project  project used to assign employee to project
     */    
    public void assignEmployee(ProjectVO project) {
        String employeeIds;
        String[] idsOfEmployee;
        List<EmployeeVO> employees = getEmployee(project);
        List<Integer> unAvailableEmployee;
        
        if (null != employees) {
            System.out.println("Enter employee ids from the employees above.....");
            employeeIds = getAndValidateEmployeeId();
            try {
                unAvailableEmployee = projectController
                        .assignEmployeeToProject(employeeIds, project, employees);
                
                if (null != unAvailableEmployee ) {        
                    for (Integer employeeId : unAvailableEmployee) {
                        System.out.println(employeeId + " is not available employee");
                    }
                    System.out.println("Employee has not been assigned to project!!!");
                } else {
                    System.out.println("Employee has been assigned to project!!!");
                } 
            } catch (EmployeeManagementException exception) {
                EmployeeManagementLogger.LOGGER.error(exception);
                System.out.println(exception);
            }    
        } else {
            System.out.println("Nothing to assign!!!");
        }      
    }

    /**
     * Asks user to choose which employee to deassign from project.
     *
     * @param project  project used to assign employee
     */    
    public void deAssignEmployee(ProjectVO project) {
        String employeeIds;
        List<Integer> unAssignedEmployee;

        if (!(project.getEmployees().isEmpty())) {
            System.out.println("List of assigned projects to employee:\n e.id  e.name");
            for (EmployeeVO employee : project.getEmployees()) {
                System.out.println(employee.getId() + "    " + employee.getName());
            }
            
            System.out.println("Enter employee ids from the employees above.....");
            employeeIds = getAndValidateEmployeeId();
            try {
                unAssignedEmployee = projectController
                        .deAssignEmployeeToProject(employeeIds, project);
                        
                if (null != unAssignedEmployee ) {        
                    for (Integer employeeId : unAssignedEmployee) {
                        System.out.println(employeeId + " is not assigned employee");
                    }
                    System.out.println("Employee has not been deassigned to project!!!");
                } else {
                    System.out.println("Employee has been deassigned to project!!!");
                }
            } catch (EmployeeManagementException exception) {
                EmployeeManagementLogger.LOGGER.error(exception);
                System.out.println(exception);
            }
        } else {
            System.out.println("Nothing to deassign!!!");
        }   
    }

    /**
     * Asks user select a option continuously for deleting specific project or all 
     * projects till user chooses to exit.
     */
    private void deleteProject() { 
        boolean isValidChoice = true;
        int userChoice;
        StringBuilder preference = new StringBuilder();
        preference.append("1. to delete individual project\n")
                .append("2. to delete all project\n3. exit\n");
            
        while (isValidChoice) {
            System.out.print(preference);
            userChoice = getAndValidateChoiceOfUser();
            isValidChoice = executeDeleteOperation(userChoice);
        }             
    }

    /**
     * Deletes specfic project using project id or all projects.
     * 
     * @param userChoice  choose of the user to delete project
     * @return            boolean true if choose is 3 or false
     */
    private boolean executeDeleteOperation(int userChoice) {
        boolean isValidChoice = true;

        switch (userChoice) {
            case 1: 
                deleteProjectById();
                break;
            case 2:
                deleteAllProject();
                break;
            case 3:
                isValidChoice = false;
                break;
            default:
                System.out.println("Enter a valid choose!!!\n");
                break;
        }
        return isValidChoice;
    }
    
    /**
     * Deletes specific project details.
     */
    private void deleteProjectById() {
        int projectId = getAndValidateId();
        
        try {
            boolean isProjectRemoved = projectController.deleteProjectById(projectId);
            
            if (isProjectRemoved) {  
                System.out.println(true == isProjectRemoved 
                        ? "Project removed!!!"
                        : "Project was not removed!!!"); 
            } else {
                getProjectId();
                System.out.println("Enter valid project id!!!");
            } 
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        } 
    }

    /**
     * Deletes all project details.
     */    
    private void deleteAllProject() {
        try {
            boolean isProjectsRemoved = projectController.deleteAllProject();
            
            if (!isProjectsRemoved) {
                System.out.println("Database is empty!!!");
            } else {
                System.out.println(true == isProjectsRemoved 
                        ? "All projects removed!!!"
                        : "No project was removed!!!"); 
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }    

    
    /**
     * Asks user to select a preference from given option and catches  
     * exception when invalid choice is given.
     */
    public void choosePreferredOperation() {
        int option = 0;
        StringBuilder optionErrorMessage = new StringBuilder();
        StringBuilder preference = new StringBuilder();

        optionErrorMessage.append("Option must be integer without special")
                .append(" character, spaces and must enter option");
        preference.append("\n1. Create Project\n2. View Project\n")
                .append("3. Update Project\n4. Delete Project\n5. Exit")
                .append("\nEnter your choice: ");

        System.out.println("\nWelcome to Project Management System: ");
        do {
            try {
                System.out.print(preference);
                option = Integer.parseInt(scanner.nextLine());
                executeOperation(option);
            } catch (NumberFormatException exception) {
                EmployeeManagementLogger.LOGGER.error(optionErrorMessage);
                System.out.println(optionErrorMessage);
            }
        } while (5 != option);
    }

    /**
     * Operations are excecuted as per selected option.
     *
     * @param option  option selected by user to perform crud operation
     */
    private void executeOperation(int option) {

        switch (option) {
            case 1:
                createProject();
                break;
            case 2:
                viewProject();
                break;
            case 3:
                updateProject();
                break;
            case 4:
                deleteProject();
                break;
            case 5:
                System.out.println("Thank you for using PMS!!!");
                break;
            default:
                System.out.println("Please enter a valid choice!!!");
                break;
        }
    }

    /**
     * Gets choice from user and catches exception when invalid choice is given.
     *
     * @return  int if valid choice is inputted
     */
    private int getAndValidateChoiceOfUser() {
        boolean isValidChoice = true;
        int userChoice = 0;
        StringBuilder userChoiceErrorMessage = new StringBuilder();
        userChoiceErrorMessage.append("Choice must be integer without special")
                .append(" character or spaces and must enter choice");

        while (isValidChoice) {
            System.out.print("Enter your choose: ");
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                System.out.print("\n");
                isValidChoice = false;
            } catch (NumberFormatException exception) {
                EmployeeManagementLogger.LOGGER.error(userChoiceErrorMessage);
                System.out.println(userChoiceErrorMessage);
            }
        }
        return userChoice;
    }

    /**
     * Asks user for project id, validates id is integer and does not allow id 
     * to be 0. 
     *
     * @return  int if valid project id is inputted
     */
    private int getAndValidateId() {
        boolean isCorrectId = false;        
        int projectId = 0;
        StringBuilder idErrorMessage = new StringBuilder();
        idErrorMessage.append("Project id must be integer without spaces")
                .append(" or special character and must enter id");

        while (!isCorrectId) {        
            System.out.print("Enter the project id: ");

            try {
                projectId = Integer.parseInt(scanner.nextLine());
                isCorrectId = projectController.isValidId(projectId);
                
                if (!isCorrectId) {
                    System.out.println("Project id must be not 0");
                }      
            } catch (NumberFormatException exception) {
                EmployeeManagementLogger.LOGGER.error(idErrorMessage);
                System.out.println(idErrorMessage);
            }
        }
        return projectId;
    }
    
    /**
     * Asks user for employee id, validates id is number, only contains spaces 
     * and comma. 
     *
     * @return  String if valid employee id is inputted
     */
    private String getAndValidateEmployeeId() {
        boolean isCorrectEmployeeId = false;        
        String employeeId = null;
        StringBuilder idErrorMessage = new StringBuilder();
        idErrorMessage.append("Employee id must be integer, contains spaces")
                .append(" and only comma as special character and must enter id");

        while (!isCorrectEmployeeId) {        
            System.out.print("Enter the project id: ");
            employeeId = scanner.nextLine();
            isCorrectEmployeeId = projectController.isValidEmployeeId(employeeId);
                
            if (!isCorrectEmployeeId) {
                System.out.println(idErrorMessage);
            }      
        }
        return employeeId;
    }   
    
    /**
     * Asks user for project name, validates name is alphabet and   
     * allows spaces only between names.
     *
     * @return  String if valid project name is inputted
     */
    private String getAndValidateName() {
        boolean isCorrectName = false;
        String name = null;
        StringBuilder nameErrorMessage = new StringBuilder();
        nameErrorMessage.append("Project name should only contain alphabet with")
                .append(" only spaces allowed between names\nmust enter name"); 

        while (!isCorrectName) {
            System.out.print("Enter the project name: ");
            name = scanner.nextLine();
            isCorrectName = projectController.isValidName(name);

            if (!isCorrectName) {
                System.out.println(nameErrorMessage);
            } 
        }
        return name;
    }
    
    /**
     * Asks user for manager name, validates name is alphabet and   
     * allows spaces only between names.
     *
     * @return  String if valid manager name is inputted
     */
    private String getAndValidateManagerName() {
        boolean isCorrectName = false;
        String name = null;
        StringBuilder nameErrorMessage = new StringBuilder();
        nameErrorMessage.append("Manager name should only contain alphabet with ")
                .append("only spaces allowed between names\nmust enter name"); 

        while (!isCorrectName) {
            System.out.print("Enter the manager name: ");
            name = scanner.nextLine();
            isCorrectName = projectController.isValidName(name);

            if (!isCorrectName) {
                System.out.println(nameErrorMessage);
            } 
        }
        return name;
    }
    
    /**
     * Asks user for project domain name, validates name is alphabet and   
     * allows spaces only between names.
     *
     * @return  String if valid project domain is inputted
     */
    private String getAndValidateDomain() {
        boolean isCorrectDomain = false;
        String domain = null;
        StringBuilder domainErrorMessage = new StringBuilder();
        domainErrorMessage.append("Project domain should only contain alphabet ")
                .append("with only spaces allowed between names\nmust enter name"); 

        while (!isCorrectDomain) {
            System.out.print("Enter the domain name: ");
            domain = scanner.nextLine();
            isCorrectDomain = projectController.isValidName(domain);

            if (!isCorrectDomain) {
                System.out.println(domainErrorMessage);
            } 
        }
        return domain;
    }  
    
    /**
     * Asks user for project starting date in dd/mm/yyyy format, validates if date
     * is in correct format, day must not greater than 31, month must not be 
     * greater than 12, date should be integer without spaces and  special  
     * characters and catches exception when invalid date format is entered.
     *
     * @return  Date if project starting date of correct format is inputted
     */
    private Date getAndValidateStartDate() {
        boolean isValidDate = false;
        Date startingDate = null;
        String date;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder dateErrorMessage = new StringBuilder();
        dateErrorMessage.append("Enter correct date format(dd/mm/yyyy), day ")
                .append("must not be greater than 31 and month must not be ")
                .append("greater than 12\ndate should be integer without ")
                .append("spaces and  special characters and must enter date");
    
        while (!isValidDate) {
            System.out.print("Enter the project starting date(dd/mm/yyyy): ");
            date = scanner.nextLine();
        
            try {
                if (projectController.validateDate(date)) {
                    startingDate = DateUtil.convertStringToDate(date, dateFormatter);
                    isValidDate = true;

                    if (!projectController.isValidDate(startingDate)) {
                        isValidDate = false;
                        System.out.println("Starting should not future date");
                    }
                } else {
                    System.out.println(dateErrorMessage);
                }
            } catch (ParseException exception) {
                EmployeeManagementLogger.LOGGER.error(dateErrorMessage);
                System.out.println(dateErrorMessage);
            }
        }
        return startingDate;
    }    
}

package com.proj.employeemanagement.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.proj.employeemanagement.controller.AddressController;
import com.proj.employeemanagement.controller.EmployeeController;
import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.logger.EmployeeManagementLogger;
import com.proj.employeemanagement.model.AddressVO;
import com.proj.employeemanagement.model.EmployeeVO;
import com.proj.employeemanagement.model.ProjectVO;
import com.proj.employeemanagement.util.DateUtil;

/**
 * Implements a application for inputting, validating and viewing all employee 
 * details to perform create, view, update and delete operation on it.
 *
 * @version 1.03
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeView {

    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeController employeeController = new EmployeeController();
    private final AddressController addressController = new AddressController();

    /**
     * Asks inputs from the user whether to add employee or only 
     * employee's address. 
     */
    private void createEmployee() {        
        int userChoice = 0;
               
        while (3 != userChoice) {
            System.out.println("1. Add employee\n2. Add address\n3. Exit");
            userChoice = getAndValidateChoiceOfUser();
            
            switch (userChoice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    try {
                        EmployeeVO employee = employeeController
                                .getEmployeeById(getAndValidateId());
                        
                        if (null != employee) {
                            addAddress(employee);
                        } else {
                            getEmployee();
                            System.out.println("Enter valid employee id!!!");
                        }
                    } catch (EmployeeManagementException exception) {
                        EmployeeManagementLogger.LOGGER.error(exception);
                        System.out.println(exception);
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Enter a valid choose!!!\n");
                    break;
            }
        }
    }

    /**
     * Asks inputs from the user to create employee and prints message   
     * whether employee is created or not. 
     */
    private void addEmployee() {
        int employeeId = 0;
        String name = getAndValidateName();   
        String phoneNumber = getAndValidatePhoneNumber();
        Date dob = getAndValidateDate();        
        float salary = getAndValidateSalary();
        String emailId = getAndValidateEmailId();
        
        try {            
            employeeId = employeeController.createEmployee(new EmployeeVO(name,
                    phoneNumber, dob, salary, emailId));
                    
            if (0 != employeeId) {
                addAddress(employeeController.getEmployeeById(employeeId));
                System.out.println("Employee was created\nEmployee id is: " 
                + employeeId);
            } else {
                System.out.println("Employee was not created!!!");
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }
    
    /**
     * Asks inputs from the user to add employee address and prints message   
     * whether address was added or not. 
     *
     * @param employee  employee whose address needs to be added
     */
    private void addAddress(EmployeeVO employee) {
        int addressId = 0;
        String doorNumber = getAndValidateDoorNumber();   
        String street = getAndValidateStreet();
        String district = getAndValidateDistrict();        
        String state = getAndValidateState();
        String country = getAndValidateCountry();
        int pincode = getAndValidatePincode(); 
        
        try {            
            addressId = addressController.addAddress(new AddressVO(employee,
                    doorNumber, street, district, state, country, pincode));
            System.out.println(0 == addressId ? "Address was not added!!!" 
                    : "Address was added\nAddress id is: " + addressId);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }                  
    }    
           
    /**
     * Asks user select a option continuously for displaying specific employee
     * detail or all employee details till user chooses to exit.
     */
    private void viewEmployee() {
        boolean isValidChoice = true;
        int userChoice;
        StringBuilder preference = new StringBuilder();
        preference.append("\n1. single employee\n2. all employees\n")
                .append("3. single employee project\n4. exit\n");

        while (isValidChoice) {
            System.out.print(preference);
            userChoice = getAndValidateChoiceOfUser();
            isValidChoice = executeViewOperation(userChoice);
        }
    }

    /**
     * Displays detail of specfic employee using employee id or displays
     * detail of all employees.
     * 
     * @param userChoice  choice selected by user to display employee detail
     * @return            boolean false if choose is 3 or true 
     */
    private boolean executeViewOperation(int userChoice) {
        boolean isValidChoice = true;

        switch (userChoice) {
            case 1: 
                viewEmployeeById();
                break;
            case 2:
                viewAllEmployee();
                break;
            case 3: 
                viewProjectByEmployeeId();
                break;
            case 4:
                isValidChoice = false;
                break;
            default:
                System.out.println("Enter a valid choose!!!\n");
                break;
        }
        return isValidChoice;
    }
    
    /**
     * Displays specific employee details.
     */
    private void viewEmployeeById() {
        EmployeeVO employee = null;
        int employeeId = getAndValidateId();
        
        try {            
            employee = employeeController.getEmployeeById(employeeId);  
            
            if (null != employee) {
                List<AddressVO> addresses = employee.getAddresses();
                
                if (null != addresses) { 
                    System.out.println(employee);
                    for (AddressVO address : addresses) {
                        System.out.println(address);
                    }
                }
            } else {
                getEmployee();
                System.out.println("Enter valid employee id!!!");
            } 
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }      
              
    }

    /**
     * Displays all employee details.
     */    
    private void viewAllEmployee() {
        List<EmployeeVO> employees = null;
        
        try {            
            employees = employeeController.getAllEmployee();
            
            if (null != employees) {
                for (EmployeeVO employeeDetail : employees) {
                    List<AddressVO> addresses = employeeDetail.getAddresses();
                    
                    if (null != addresses) {          
                        System.out.println(employeeDetail);
                        for (AddressVO address : addresses) {
                            System.out.println(address);
                        }
                    }                
                }
            } else {
                System.out.println("Database is empty!!!");
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }           
    }
    
    /**
     * Displays specific project details.
     */
    private void viewProjectByEmployeeId() {
        int employeeId = getAndValidateId();
        EmployeeVO employee = null;
        
        try {            
            employee = employeeController.getEmployeeById(employeeId);
            
            if (null != employee) {
                List<ProjectVO> projects = employee.getProjects();
                 
                if (null != projects) {         
                    System.out.println(employee);
                    for (ProjectVO project : projects) {
                        System.out.println(project);
                    }
                }
            } else {
                getEmployee();
                System.out.println("Enter valid employee id!!!");
            } 
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }                  
    }
    
    /**
     * Displays all employee id and its corresponding employee name.
     */    
    private void getEmployee() {
        StringBuilder employeeIds = new StringBuilder();
        
        try {            
            System.out.println("---List of employees---\ne.id e.name");
            for (EmployeeVO employee : employeeController.getAllEmployee()) {
                employeeIds.append(employee.getId()).append("    ")
                        .append(employee.getName()).append("\n");
            }    
            System.out.println(employeeIds);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }   
    }
    
    /**
     * Displays all address id and its corresponding employee id.
     */    
    private void getAddress() {
        StringBuilder addressIds = new StringBuilder();
        
        try {            
            System.out.println("---List of addresses---\na.id e.id");
            for (AddressVO address : addressController.getAddressId()) {
                addressIds.append(address.getId()).append("     " )
                        .append(address.getEmployee().getId()).append("\n");
            }
            System.out.println(addressIds);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }   
    }   
    
    /**
     * Displays all project id and its corresponding project name.
     *
     * @param employee  employee used to get assigned project
     * @return          List<ProjectVO> list of project not yet assigned
     */    
    private List<ProjectVO> getProject(EmployeeVO employee) {
        StringBuilder projectIds = new StringBuilder();
        List<ProjectVO> projects = null;
        
        try {            
            projects = employeeController.getAllProject();
            
            if (null != projects) {
                if (employee.getProjects().isEmpty()) {        
                    System.out.println("---List of projects---\np.id p.name");
                    for (ProjectVO project : projects) {
                        projectIds.append(project.getId()).append("     " )
                                .append(project.getName()).append("\n");
                    }
                } else {
                    System.out.println("---List of projects---\np.id p.name");
                    for (ProjectVO project : employeeController
                            .getProjectsTobeAssigned(projects, employee)) {
                        projectIds.append(project.getId()).append("     " )
                                .append(project.getName()).append("\n");
                    }
                }           
                System.out.println(projectIds);
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }          
        return projects;
    }     

    /**
     * Asks user select a option continuously for updating specific employee 
     * detail or all employee details till user chooses to exit.
     */
    private void updateEmployee() {
        int employeeId = getAndValidateId();
        try {
            EmployeeVO employee = employeeController.getEmployeeById(employeeId);
        
            if (null != employee) {
                boolean isValidChoice = true;
                int userChoice;
                StringBuilder preference = new StringBuilder();
                preference.append("\n1. to update individual detail of ")
                        .append("employee\n2. to update employee address\n")
                        .append("3. to update all employee details\n4. assign")
                        .append(" project to employee\n5. deassign project for")
                        .append(" employee\n6. exit\n");
    
                while (isValidChoice) {
                    System.out.print(preference);
                    userChoice = getAndValidateChoiceOfUser();
                    isValidChoice = executeUpdateOperation(userChoice, employee);
                } 
            } else {
                getEmployee();
                System.out.println("Enter valid employee id!!!");
            } 
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }        
    }

    /**
     * Updates specific detail or all details of the employee using id.
     *
     * @param userChoice  choose of the user to update employee detail
     * @param employee    employee as input to update operation
     * @return            boolean true if choose is 3 or false 
     */
    private boolean executeUpdateOperation(int userChoice, EmployeeVO employee) {
        boolean isValidChoice = true;

        switch (userChoice) {
            case 1:
                updateSpecificDetail(employee);  
                break;                
            case 2:
                updateAddress(employee);
                break;
            case 3:
                updateEmployee(employee);
                break;
            case 4:
                assignProject(employee);
                break;
            case 5:
                deAssignProject(employee);
                break;
            case 6:
                isValidChoice = false;
                break;
            default:
                System.out.println("Enter a valid choose!!!\n");
                break;
        }
        return isValidChoice;
    }

    /**
     * Asks user to choose which project to assign to employee.
     *
     * @param employee  employee for whom need to assign project
     */    
    public void assignProject(EmployeeVO employee) {
        String projectIds;
        List<ProjectVO> projects = getProject(employee);
        List<Integer> unAvailableProject;
        
        if (null != projects) {
            System.out.println("Enter project ids from the projects above.....");
            projectIds = getAndValidateProjectId();
            try {
                unAvailableProject = employeeController
                        .assignProjectToEmployee(projectIds, employee, projects);
            
                if (null != unAvailableProject ) {        
                    for (Integer projectId : unAvailableProject) {
                    System.out.println(projectId + " is not available project");
                    }
                    System.out.println("Project has not been assigned to employee!!!");
                } else {
                    System.out.println("Project has been assigned to employee!!!");
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
     * Asks user to choose which project to deassign from employee.
     *
     * @param employee  employee from whom need to de-assign project
     */    
    public void deAssignProject(EmployeeVO employee) {
        String projectIds;
        List<Integer> unAssignedProject;
        
        if (!(employee.getProjects().isEmpty())) {
            System.out.println("List of assigned projects to employee:\n p.id  p.name");
            for (ProjectVO project : employee.getProjects()) {
             System.out.println(project.getId() + " " + project.getName());
            }
        
            System.out.println("Enter project ids from the projects above.....");
            projectIds = getAndValidateProjectId();  
            try {
                unAssignedProject = employeeController
                        .deAssignProjectToEmployee(projectIds, employee);
                    
                if (null != unAssignedProject ) {        
                    for (Integer projectId : unAssignedProject) {
                        System.out.println(projectId + " is not assigned project");
                    }
                    System.out.println("Project has not been deassigned to employee!!!");
                } else {
                    System.out.println("Project has been deassigned to employee!!!");
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
     * Asks user to choose which specific detail to update and updates it.
     *
     * @param employee  employee used to update specific detail of that employee
     */
    private void updateSpecificDetail(EmployeeVO employee) {
        int userChoice;
        boolean isDetailUpdated = false;
        StringBuilder preference = new StringBuilder();
        preference.append("1. employee name\n2. employee phone number\n")
                .append("3. employee date of birth\n4. employee salary\n")
                .append("5. employee email id\n6. employee address\n");
        
        System.out.print(preference);
        userChoice = getAndValidateChoiceOfUser();
        isDetailUpdated = updateDetail(employee, userChoice);
        System.out.println(true == isDetailUpdated ? "Employee detail updated!!!" 
                : "Employee detail not updated!!!");
    }
    
    /**
     * Updates individual detail of a employee.
     *
     * @param employeeVO  employee object used to update detail
     * @param userChoice  user choice of which detail to be updated
     * @return            Boolean true if detail updated or false
     */
    private boolean updateDetail(EmployeeVO employeeVO, int userChoice) {
        boolean isDetailUpdated = false;
        
        try {
            switch (userChoice) {
                case 1:
                    employeeVO.setName(getAndValidateName());
                    isDetailUpdated = employeeController.updateEmployee(employeeVO);
                    break;
                case 2:
                    employeeVO.setPhoneNumber(getAndValidatePhoneNumber());
                    isDetailUpdated = employeeController.updateEmployee(employeeVO);
                    break;
                case 3:
                    employeeVO.setDob(getAndValidateDate());
                    isDetailUpdated = employeeController.updateEmployee(employeeVO);
                    break;
                case 4:
                    employeeVO.setSalary(getAndValidateSalary());
                    isDetailUpdated = employeeController.updateEmployee(employeeVO);
                    break;
                case 5:
                    employeeVO.setEmailId(getAndValidateEmailId());
                    isDetailUpdated = employeeController.updateEmployee(employeeVO);
                    break;
                case 6:
                    isDetailUpdated = updateSpecificAddressDetail();
                    break;                
                default:
                    System.out.println("\nPlease enter a valid choice!!!");
                    break;
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
        return isDetailUpdated;
    }

    /**
     * Asks user for input to update all employee details using employee id.
     *
     * @param employeeVO  employee to update all details of that employee 
     */
    private void updateEmployee(EmployeeVO employeeVO) {
        boolean isDetailsUpdated;

        employeeVO.setName(getAndValidateName());
        employeeVO.setPhoneNumber(getAndValidatePhoneNumber());
        employeeVO.setDob(getAndValidateDate());
        employeeVO.setSalary(getAndValidateSalary());
        employeeVO.setEmailId(getAndValidateEmailId());   
        
        try {              
            isDetailsUpdated =  employeeController.updateEmployee(employeeVO);

            System.out.println(true == isDetailsUpdated 
                   ? "Employee details updated!!!" 
                   : "Employee details not updated!!!");
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }     
    }

    /**
     * Asks user to choose which specific detail to update and updates it.
     *
     * @param employeeId  employee id used to update specific detail of that
     *                    employee
     * @return            Boolean true if detail updated or false
     */
    private boolean updateSpecificAddressDetail() {
        int userChoice;
        int addressId;
        boolean isDetailUpdated = false;
        AddressVO addressVO = null;
        StringBuilder preference = new StringBuilder();
        preference.append("1. door number\n2. street\n3. district\n")
                .append("4. state\n5. country\n6. pincode\n");
        
        System.out.print(preference);
        addressId = getAndValidateAddressId();
        userChoice = getAndValidateChoiceOfUser();
        
        try {
            addressVO = addressController.getAddress(addressId);
            
            if (null != addressVO) {
                isDetailUpdated = updateAddressDetail(addressVO, userChoice);
            } else {
                getAddress();
                System.out.println("Enter valid address id!!!");
            }
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }          
        return isDetailUpdated;   
    }

    /*
     * Updates individual detail of a employee.
     *
     * @param userChoice  user choice of which detail to be updated
     * @param employeeId  employee id of employee whose detail to be updated
     * @return            Boolean true if detail updated or false
     */
    private boolean updateAddressDetail(AddressVO addressVO, int userChoice) {
        boolean isDetailUpdated = false;
        
        switch (userChoice) {
            case 1:
                addressVO.setDoorNumber(getAndValidateDoorNumber());
                break;
            case 2:
                addressVO.setStreet(getAndValidateStreet());
                break;
            case 3:
                addressVO.setDistrict(getAndValidateDistrict());
                break;
            case 4:
                addressVO.setState(getAndValidateState());
                break;
            case 5:
                addressVO.setCountry(getAndValidateCountry());
                break;    
             case 6:
                addressVO.setPincode(getAndValidatePincode());
                break;            
            default:
                System.out.println("\nPlease enter a valid choice!!!");
                break;
        }
        
        try {
            isDetailUpdated = (6 >= userChoice && 0 < userChoice &&
                    addressController.updateAddress(addressVO)) ? true : false;
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
        return isDetailUpdated;
    }

    /**
     * Asks user for input to update address of employee.
     *
     * @param employee  employee of employee whose address needs to be updated
     */    
    private void updateAddress(EmployeeVO employee) {
        boolean isDetailUpdated = false;
        
        try {
            AddressVO addressVO = addressController.getAddress(getAndValidateAddressId());
            
            if (null != addressVO) {
                isDetailUpdated = addressController.updateAddress(getAddress(addressVO, employee));
            } else {
                getAddress();
                System.out.println("Enter valid address id!!!");
            }
            System.out.println(true == isDetailUpdated ? "Address updated" 
                    : "Address not updated");
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }
   
    /**
     * Asks inputs from the user to update address.
     *
     * @param addressVO   address object to be updated
     * @param employee    employee of employee to be update
     * @return            AddressVO if all the value are inputted
     */
    private AddressVO getAddress(AddressVO addressVO, EmployeeVO employee) {
 
        addressVO.setEmployee(employee);
        addressVO.setDoorNumber(getAndValidateDoorNumber());   
        addressVO.setStreet(getAndValidateStreet());
        addressVO.setDistrict(getAndValidateDistrict());        
        addressVO.setState(getAndValidateState());
        addressVO.setCountry(getAndValidateCountry());
        addressVO.setPincode(getAndValidatePincode());            
        return addressVO;
    }  

    /**
     * Asks inputs from the user whether to delete employee or only 
     * employee's address. 
     */
    private void deleteEmployee() {
        int userChoice = 0;
        StringBuilder preference = new StringBuilder();

        preference.append("\n1. delete all employee detail\n2. delete employee")
                .append(" address\n3. Exit");
   
        while (3 != userChoice) {
        
            System.out.println(preference);
            userChoice = getAndValidateChoiceOfUser();
            switch (userChoice) {
                case 1:
                    deleteAllDetail();
                    break;
                case 2:
                    deleteAddress();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Enter a valid choose!!!\n");
                    break;
            }
        }
    }

    /**
     * Asks user select a option continuously for deleting specific employee or all 
     * employees till user chooses to exit.
     */
    private void deleteAllDetail() {
        boolean isValidChoice = true;
        int userChoice;
        StringBuilder preference = new StringBuilder();
        preference.append("1. to delete individual employee\n")
                .append("2. to delete all employee\n3. exit\n");
           
        while (isValidChoice) {
            System.out.print(preference);
            userChoice = getAndValidateChoiceOfUser();
            isValidChoice = executeDeleteOperation(userChoice);
        }             
    }

    /**
     * Deletes specfic employee using employee id or all employees.
     * 
     * @param userChoice  choose of the user to delete employee
     * @return            boolean true if choose is 3 or false
     */
    private boolean executeDeleteOperation(int userChoice) {
        boolean isValidChoice = true;

        switch (userChoice) {
            case 1: 
                deleteEmployeeById();
                break;
            case 2:
                deleteAllEmployee();
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
     * Deletes specific employee details.
     */
    private void deleteEmployeeById() {
        try {
            boolean isEmployeeRemoved = employeeController
                    .deleteEmployeeById(getAndValidateId());
            
            if (isEmployeeRemoved) {  
                System.out.println(true == isEmployeeRemoved 
                        ? "Employee removed!!!"
                        : "Employee was not removed!!!"); 
            } else {
                getEmployee();
                System.out.println("Enter valid employee id!!!");
            }  
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }

    /*
     * Deletes all employee details.
     */    
    private void deleteAllEmployee() {
        try {
            boolean isEmployeesRemoved = employeeController.deleteAllEmployee();
            
            if (!isEmployeesRemoved) {
                System.out.println("Database is empty!!!");
            } else {
                System.out.println(true == isEmployeesRemoved 
                        ? "All employees removed!!!"
                        : "No employee was removed!!!"); 
            } 
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }    

    /**
     * Asks user select a option continuously for deleting specific employee or 
     * all employees till user chooses to exit.
     */
    private void deleteAddress() {
        boolean isValidChoice = true;
        int userChoice;
        StringBuilder preference = new StringBuilder();
        preference.append("\n1. to delete individual employee address\n")
                .append("2. to delete all employee address\n3. exit\n");
           
        while (isValidChoice) {
            System.out.print(preference);
            userChoice = getAndValidateChoiceOfUser();
            isValidChoice = deleteAddressOperation(userChoice);
        }             
    }

    /**
     * Deletes specfic address using address id or all addresses.
     * 
     * @param userChoice  choose of the user to delete address
     * @return            boolean true if choose is 3 or false
     */
    private boolean deleteAddressOperation(int userChoice) {
        boolean isValidChoice = true;

        switch (userChoice) {
            case 1: 
                deleteAddressById();
                break;
            case 2: 
                deleteAllAddress();
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
     * Deletes specific employee adress details.
     */
    private void deleteAddressById() {
        try {
            boolean isAddressRemoved = addressController
                    .deleteAddressById(getAndValidateAddressId());
                    
            if (isAddressRemoved) {  
                System.out.println(true == isAddressRemoved 
                        ? "Address removed!!!"
                        : "Address was not removed!!!"); 
            } else {
                getAddress();
                System.out.println("Enter valid address id!!!");
            }  
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.LOGGER.error(exception);
            System.out.println(exception);
        }
    }

    /*
     * Deletes all employee address details.
     */    
    private void deleteAllAddress() {
        try {
            boolean isAddressesRemoved = addressController.deleteAllAddress();
            
            if (!isAddressesRemoved) {
                System.out.println("Database is empty!!!");
            } else {
                System.out.println(true == isAddressesRemoved 
                        ? "All Addresses removed!!!"
                        : "No Address was removed!!!"); 
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
        preference.append("\n1. Create Employee\n2. View Employee\n")
                .append("3. Update Employee\n4. Delete Employee\n5. Exit")
                .append("\nEnter your choice: ");

        System.out.println("\nWelcome to Employee Management System: ");
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
                createEmployee();
                break;
            case 2:
                viewEmployee();
                break;
            case 3:
                updateEmployee();
                break;
            case 4:
                deleteEmployee();
                break;
            case 5:
                System.out.println("Thank you for using EMS!!!");
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
     * Asks user for employee id, validates id is integer and does not allow id 
     * to be 0. 
     *
     * @return  int if valid employee id is inputted
     */
    private int getAndValidateId() {
        boolean isCorrectId = false;        
        int employeeId = 0;
        StringBuilder idErrorMessage = new StringBuilder();
        idErrorMessage.append("Employee id must be integer without spaces")
                .append(" or special character and must enter id");

        while (!isCorrectId) {        
            System.out.print("Enter the employee id: ");

            try {
                employeeId = Integer.parseInt(scanner.nextLine());
                isCorrectId = employeeController.isValidId(employeeId);
                
                if (!isCorrectId) {
                    System.out.println("Employee id must be not 0");
                }      
            } catch (NumberFormatException exception) {
                EmployeeManagementLogger.LOGGER.error(idErrorMessage);
                System.out.println(idErrorMessage);
            }
        }
        return employeeId;
    }
    
    /**
     * Asks user for address id, validates id is integer, does not allow id 
     * to be 0 and must have exactly 6 digits. 
     *
     * @return  int if valid address id is inputted
     */
    private int getAndValidateAddressId() {
        boolean isCorrectId = false;        
        int addressId = 0;
        StringBuilder idErrorMessage = new StringBuilder();
        idErrorMessage.append("Address id must be integer without spaces")
                .append(" or special character and must enter id");

        while (!isCorrectId) {        
            System.out.print("Enter the address id: ");

            try {
                addressId = Integer.parseInt(scanner.nextLine());
                isCorrectId = addressController.isValidId(addressId);
                
                if (!isCorrectId) {
                    System.out.println("Address id must be not 0");
                }      
            } catch (NumberFormatException exception) {
                EmployeeManagementLogger.LOGGER.error(idErrorMessage);
                System.out.println(idErrorMessage);
            }
        }
        return addressId;
    } 
    
    /**
     * Asks user for project id, validates id is number, only contains spaces 
     * and comma. 
     *
     * @return  String if valid project id is inputted
     */
    private String getAndValidateProjectId() {
        boolean isCorrectProjectId = false;        
        String projectId = null;
        StringBuilder idErrorMessage = new StringBuilder();
        idErrorMessage.append("Project id must be integer, contains spaces")
                .append(" and only comma as special character and must enter id");

        while (!isCorrectProjectId) {        
            System.out.print("Enter the project id: ");
            projectId = scanner.nextLine();
            isCorrectProjectId = employeeController.isValidProjectId(projectId);
                
            if (!isCorrectProjectId) {
                System.out.println(idErrorMessage);
            }      
        }
        return projectId;
    }   

    /**
     * Asks user for employee name, validates name is alphabet and   
     * allows spaces only between names.
     *
     * @return  String if valid employee name is inputted
     */
    private String getAndValidateName() {
        boolean isCorrectName = false;
        String name = null;
        StringBuilder nameErrorMessage = new StringBuilder();
        nameErrorMessage.append("Name should only contain alphabet with ")
                .append("only spaces allowed between names\nmust enter name"); 

        while (!isCorrectName) {
            System.out.print("Enter the employee name: ");
            name = scanner.nextLine();
            isCorrectName = employeeController.isValidName(name);

            if (!isCorrectName) {
                System.out.println(nameErrorMessage);
            } 
        }
        return name;
    }

    /**
     * Asks user for employee phone number, validates phone number is integer,
     * should have only 10 digits with only prefix of 0 or +91 is optional,
     * starting digit should be greater than 5 and check if phone number is 
     * unique.  
     *
     * @return  String if valid employee phone number is inputted
     */
    private String getAndValidatePhoneNumber() {
        boolean isCorrectPhoneNumber = false;
        String phoneNumber = null;
        StringBuilder numberErrorMessage = new StringBuilder();
        numberErrorMessage.append("Phone number should 10 digit only containing ")
                .append("number without spaces or special characters\nonly ")
                .append("prefix of +91 & 0 allowed and must enter phone number");

        while (!isCorrectPhoneNumber) {
            System.out.print("Enter the employee phone number: ");
            phoneNumber = scanner.nextLine();
            
            try {
                if (!employeeController.isDuplicatePhoneNumber(phoneNumber)) {
                    isCorrectPhoneNumber = employeeController
                            .isValidPhoneNumber(phoneNumber);

                    if (!isCorrectPhoneNumber) {
                        System.out.println(numberErrorMessage);
                    } 
                } else {
                    System.out.println("Enter unique phone number");
                }
            } catch (EmployeeManagementException exception) {
                EmployeeManagementLogger.LOGGER.error(exception);
                System.out.println(exception);
            }
        }
        return phoneNumber;
    }

    /**
     * Asks user for date of birth in dd/mm/yyyy format, validates if date is
     * in correct format, day must not greater than 31, month must not be greater
     * than 12, date should be integer without spaces and  special characters and 
     * catches exception when invalid date format is entered.
     *
     * @return  Date if date of birth of correct format is inputted
     */
    private Date getAndValidateDate() {
        boolean isValidDate = false;
        Date dob = null;
        String dateOfBirth;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder dateErrorMessage = new StringBuilder();
        dateErrorMessage.append("Enter correct date format(dd/mm/yyyy), day ")
                .append("must not be greater than 31 and month must not be ")
                .append("greater than 12\ndate should be integer without ")
                .append("spaces and  special characters and must enter date");
    
        while (!isValidDate) {
            System.out.print("Enter the employee date of birth(dd/mm/yyyy): ");
            dateOfBirth = scanner.nextLine();
        
            try {
                if (employeeController.validateDate(dateOfBirth)) {
                    dob = DateUtil.convertStringToDate(dateOfBirth, dateFormatter);
                    isValidDate = true;

                    if (!employeeController.isValidAge(dob)) {
                        isValidDate = false;
                        System.out.println("Age must be between 18 and 60");
                    }
                } else {
                    System.out.println(dateErrorMessage);
                }
            } catch (ParseException exception) {
                EmployeeManagementLogger.LOGGER.error(dateErrorMessage);
                System.out.println(dateErrorMessage);
            }
        }
        return dob;
    }

    /**
     * Asks user for salary, validates salary is number or decimal, does not
     * allow salary to be 0 and catches exception when salary is not an integer
     * or decimal.
     *
     * @return  float if valid employee salary is inputted
     */
    private float getAndValidateSalary() {
        boolean isCorrectSalary = false;
        float salary = 0;
        StringBuilder salaryErrorMessage = new StringBuilder();
        salaryErrorMessage.append("Employee salary must be integer/decimal")
                .append(" value\nsalary should not contain any special ")
                .append("character or space and must enter salary");

        while (!isCorrectSalary) {
            System.out.print("Enter the employee salary: ");
        
            try {
                salary = Float.parseFloat(scanner.nextLine());
                isCorrectSalary = employeeController.isValidSalary(salary);

                if (!isCorrectSalary) {
                    System.out.println("Salary must not be 0");
                } 
            } catch (NumberFormatException exception) {
                EmployeeManagementLogger.LOGGER.error(salaryErrorMessage);
                System.out.println(salaryErrorMessage);
            }
        }
        return salary;
    }

    /**
     * Asks user for employee email id and validates email id is in standard 
     * email pattern, allows alphabet, number, hypen, underscore and dot allowed 
     * in local-part of mail id, allows alphabet, number and hypen in domain part 
     * and check if email id is unique.  
     *
     * @return  String if valid employee email id is inputted
     */
    private String getAndValidateEmailId() {
        boolean isCorrectEmailId = false;
        String emailId = null;
        StringBuilder emailIdErrorMessage = new StringBuilder();
        emailIdErrorMessage.append("Email id should be in standard format with ")
                .append("character, numbers, underscore, hypen and dot allowed")
                .append(" in local-part without spaces.\nNumber, character and ")
                .append("hypen allowed in domain name and must enter email id");

        while (!isCorrectEmailId) {
            System.out.print("Enter the employee email id: ");
            emailId = scanner.nextLine();
            
            try {
                if (!employeeController.isDuplicateEmailId(emailId)) {
                    isCorrectEmailId = employeeController.isValidEmailId(emailId);

                    if (!isCorrectEmailId) {
                        System.out.println(emailIdErrorMessage);
                    }  
                } else {
                    System.out.println("Enter unique email id");
                }
            } catch (EmployeeManagementException exception) {
                EmployeeManagementLogger.LOGGER.error(exception);
                System.out.println(exception);
            }
        }
        return emailId;
    }
    
    /**
     * Asks user for door number, validates it whether contains alphabet, number   
     * , slash and allows spaces only between names.
     *
     * @return  String if valid door number is inputted
     */
    private String getAndValidateDoorNumber() {
        boolean isCorrectDoorNo = false;
        String doorNumber = null;
        StringBuilder doorNumberErrorMessage = new StringBuilder();
        doorNumberErrorMessage.append("Door number should only not contain any")
                .append(" spaces\nmust enter door number"); 

        while (!isCorrectDoorNo) {
            System.out.print("Enter the door number: ");
            doorNumber = scanner.nextLine();
            isCorrectDoorNo = addressController.isValidDoorNumber(doorNumber);

            if (!isCorrectDoorNo) {
                System.out.println(doorNumberErrorMessage);
            } 
        }
        return doorNumber;
    }

    /**
     * Asks user for street, validates street name is alphabet and   
     * allows spaces only between names.
     *
     * @return  String if valid street is inputted
     */
    private String getAndValidateStreet() {
        boolean isCorrectStreetName = false;
        String street = null;
        StringBuilder streetNameErrorMessage = new StringBuilder();
        streetNameErrorMessage.append("Street name should only contain alphabet")
                .append(" with spaces allowed between names\nmust enter street"); 

        while (!isCorrectStreetName) {
            System.out.print("Enter the street: ");
            street = scanner.nextLine();
            isCorrectStreetName = addressController.isValidStreet(street);

            if (!isCorrectStreetName) {
                System.out.println(streetNameErrorMessage);
            } 
        }
        return street;
    }

    /**
     * Asks user for district, validates district name is alphabet and   
     * allows spaces only between names.
     *
     * @return  String if valid employee name is inputted
     */
    private String getAndValidateDistrict() {
        boolean isCorrectDistrictName = false;
        String district = null;
        StringBuilder districtNameErrorMessage = new StringBuilder();
        districtNameErrorMessage.append("District name should only contain ")
                .append("alphabet with only spaces allowed between names")
                .append("\nmust enter district");

        while (!isCorrectDistrictName) {
            System.out.print("Enter the district: ");
            district = scanner.nextLine();
            isCorrectDistrictName = addressController.isValidDistrict(district);

            if (!isCorrectDistrictName) {
                System.out.println(districtNameErrorMessage);
            } 
        }
        return district;
    }

    /**
     * Asks user for state, validates state name is alphabet and   
     * allows spaces only between names.
     *
     * @return  String if valid employee name is inputted
     */
    private String getAndValidateState() {
        boolean isCorrectStateName = false;
        String state = null;
        StringBuilder stateNameErrorMessage = new StringBuilder();
        stateNameErrorMessage.append("State name should only contain alphabet ")
                .append("with spaces allowed between names\nmust enter state"); 

        while (!isCorrectStateName) {
            System.out.print("Enter the state: ");
            state = scanner.nextLine();
            isCorrectStateName = addressController.isValidState(state);

            if (!isCorrectStateName) {
                System.out.println(isCorrectStateName);
            } 
        }
        return state;
    }

    /**
     * Asks user for country, validates country name is alphabet and   
     * allows spaces only between names.
     *
     * @return  String if valid employee name is inputted
     */
    private String getAndValidateCountry() {
        boolean isCorrectCountryName = false;
        String country = null;
        StringBuilder countryNameErrorMessage = new StringBuilder();
        countryNameErrorMessage.append("Country name should only contain alphabet")
                .append(" with spaces allowed between names\nmust enter country"); 

        while (!isCorrectCountryName) {
            System.out.print("Enter the country: ");
            country = scanner.nextLine();
            isCorrectCountryName = addressController.isValidCountry(country);

            if (!isCorrectCountryName) {
                System.out.println(countryNameErrorMessage);
            } 
        }
        return country;
    }     

    /**
     * Asks user for pincode, validates id is integer and only allow pincode with
     * 6 digits. 
     *
     * @return  int if valid pincode is inputted
     */
    private int getAndValidatePincode() {
        boolean isCorrectPincode = false;        
        int pincode = 0;
        StringBuilder pincodeErrorMessage = new StringBuilder();
        pincodeErrorMessage.append("Pincode must be integer without spaces")
                .append(" or special characters and must enter pincode");

        while (!isCorrectPincode) {        
            System.out.print("Enter the pincode: ");

            try {
                pincode = Integer.parseInt(scanner.nextLine());
                isCorrectPincode = addressController.isValidPincode(pincode);
                
                if (!isCorrectPincode) {
                    System.out.println("Pincode must be not 6 digits");
                }      
            } catch (NumberFormatException exception) {
                EmployeeManagementLogger.LOGGER.error(pincodeErrorMessage);
                System.out.println(pincodeErrorMessage);
            }
        }
        return pincode;
    }    
}

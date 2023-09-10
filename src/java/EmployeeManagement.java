import java.util.Scanner;

import com.proj.employeemanagement.view.EmployeeView;
import com.proj.employeemanagement.view.ProjectView;

/**
 * Implements application for validating the given detail and perform
 * create, view, update and delete operation on it.
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeManagement {

    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.executeOperation();    
    }

    /**
     * Asks user to select a preference from given option.
     */    
    public void executeOperation() {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        StringBuilder optionErrorMessage = new StringBuilder();
        StringBuilder preference = new StringBuilder();
        EmployeeView employeeView = new EmployeeView();
        ProjectView projectView = new ProjectView();
        
        optionErrorMessage.append("Option must be integer without special")
                .append(" character, spaces and must enter option");
        preference.append("\n1. Employee Management System\n")
                .append("2. Project Management System\n3. Exit")
                .append("\nEnter your choice: ");

        System.out.println("\n---------  Welcome  ---------");
        selectOperation(option, preference, optionErrorMessage, scanner, 
                employeeView, projectView);
    }

    /**
     * Operations are excecuted as per selected option.
     *
     * @param option              choose to be selected by user
     * @param preference          displays list of option to be selected by user 
     * @param optionErrorMessage  error message when invalid option is entered
     * @param scanner             scanner object to get input from user
     * @param employeeView        instances of EmployeeView class
     * @param projectView         instances of ProjectView class
     */    
    public void selectOperation(int option, StringBuilder preference,
            StringBuilder optionErrorMessage, Scanner scanner,
            EmployeeView employeeView, ProjectView projectView) {
        
        do {
            try {
                System.out.print(preference);
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        employeeView.choosePreferredOperation();
                        break;
                    case 2:
                        projectView.choosePreferredOperation(); 
                        break;
                    case 3:
                        System.out.println("Thank you for using EPMS!!!");
                        break;
                    default:
                        System.out.println("Please enter a valid choice!!!");
                        break;
                }
            } catch (NumberFormatException exception) {
                System.out.println(optionErrorMessage);
            }  
        } while(3 != option);    
    }
}

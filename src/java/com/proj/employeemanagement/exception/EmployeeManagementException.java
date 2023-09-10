package com.proj.employeemanagement.exception;

import java.lang.Exception;

/**
 * Implements application for handling excpetion in the employee management system 
 * application.
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeManagementException extends Exception {

    public EmployeeManagementException() {
        super();
    }

    public EmployeeManagementException(String errorMessage) {
        super(errorMessage);
    }
}

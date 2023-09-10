package com.proj.employeemanagement.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logger class used to provide instance of logger to employee management 
 * application
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeManagementLogger {

    public static final Logger LOGGER = LogManager
            .getLogger(EmployeeManagementLogger.class.getName());
}

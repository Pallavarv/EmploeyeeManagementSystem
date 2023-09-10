package com.proj.employeemanagement.util;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/**
 * Stores all the constant values for employee management application.
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class ConstantUtil { 

    public static final Map<String, String> ERROR = getError();
    public static final String ERROR_CODE_001 = "ERROR_CODE_001";
    public static final String ERROR_CODE_002 = "ERROR_CODE_002";
    public static final String ERROR_CODE_003 = "ERROR_CODE_003";
    public static final String ERROR_CODE_004 = "ERROR_CODE_004";
    public static final String ERROR_CODE_005 = "ERROR_CODE_005";
    public static final String ERROR_CODE_006 = "ERROR_CODE_006";
    public static final String ERROR_CODE_007 = "ERROR_CODE_007";
    public static final String ERROR_CODE_008 = "ERROR_CODE_008";
    public static final String ERROR_CODE_009 = "ERROR_CODE_009";
    public static final String ERROR_CODE_010 = "ERROR_CODE_010";
    public static final String ERROR_CODE_011 = "ERROR_CODE_011";
    public static final String ERROR_CODE_012 = "ERROR_CODE_012";
    public static final String ERROR_CODE_013 = "ERROR_CODE_013";
    public static final String ERROR_CODE_014 = "ERROR_CODE_014";
    public static final String ERROR_CODE_015 = "ERROR_CODE_015";
    public static final String ERROR_CODE_016 = "ERROR_CODE_016";
    public static final String ERROR_CODE_017 = "ERROR_CODE_017";
    public static final String ERROR_CODE_018 = "ERROR_CODE_018";
    public static final String ERROR_CODE_019 = "ERROR_CODE_019";
    public static final String ERROR_CODE_020 = "ERROR_CODE_020";
    
    /**
     * Gets list of error message and its coressponding code.
     *
     * @return  Map<String, String>  group containing pair of error code and its
     *                               corresponding message 
     */
    private static Map<String, String> getError() {
        Map<String, String> collectionOfErrorCodes = new HashMap<String, String>();
        
        collectionOfErrorCodes.put(ERROR_CODE_001, "Employee was not created");
        collectionOfErrorCodes.put(ERROR_CODE_002, "Employee could not displayed");
        collectionOfErrorCodes.put(ERROR_CODE_003, "Employees could not displayed");
        collectionOfErrorCodes.put(ERROR_CODE_004, "Employee was not updated");
        collectionOfErrorCodes.put(ERROR_CODE_005, "Employee was not deleted");
        collectionOfErrorCodes.put(ERROR_CODE_006, "Employees were not deleted");
        collectionOfErrorCodes.put(ERROR_CODE_007, "Could not check duplicate phone number");
        collectionOfErrorCodes.put(ERROR_CODE_008, "Could not check duplicate email");
        collectionOfErrorCodes.put(ERROR_CODE_009, "Address was not created");
        collectionOfErrorCodes.put(ERROR_CODE_010, "Address was not displayed");
        collectionOfErrorCodes.put(ERROR_CODE_011, "Addresses were not displayed");
        collectionOfErrorCodes.put(ERROR_CODE_012, "Address was not updated");
        collectionOfErrorCodes.put(ERROR_CODE_013, "Address was not deleted");
        collectionOfErrorCodes.put(ERROR_CODE_014, "Addresses were not deleted");
        collectionOfErrorCodes.put(ERROR_CODE_015, "Project was not created");
        collectionOfErrorCodes.put(ERROR_CODE_016, "Project was not displayed");
        collectionOfErrorCodes.put(ERROR_CODE_017, "Projects were not displayed");
        collectionOfErrorCodes.put(ERROR_CODE_018, "Project was not updated");
        collectionOfErrorCodes.put(ERROR_CODE_019, "Project was not delete");
        collectionOfErrorCodes.put(ERROR_CODE_020, "Projects were not deleted");
        return Collections.unmodifiableMap(collectionOfErrorCodes);
    }
}

package com.proj.employeemanagement.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Implements application for converting and validating date input.
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class DateUtil { 

    /**
     * Converts String to Local date format.
     *
     * @param date           date to be converted 
     * @param dateFormatter  specify in format in which date should inputted
     * @return               Date if string is converted date correctly
     */
    public static Date convertStringToDate(String date,
             SimpleDateFormat dateFormatter) throws ParseException {
        return dateFormatter.parse(date);
    }
    
    /**
     * Converts date to Local date format.
     *
     * @param date  date to be converted
     * @return      LocalDate if Date is converted LocalDate correctly
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    /** 
     * Validates whether age is in standard format.
     *
     * @param date  date to be validated
     * @return      Boolean true if age is correct or false
     */
    public static boolean validateDate(String date) {
        return date.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
    }
    
    /**
     * Validates whether age is between 18 and 60.
     *
     * @param age  age to be validated
     * @return     Boolean true if age is correct or false
     */
    public static boolean validateAge(Period age) {
        return (18 <= age.getYears() || 60 > age.getYears());
    }   
    
    /**
     * Validates whether age is between 18 and 60.
     *
     * @param date  date to be validated
     * @return      Boolean true if date is correct or false
     */
    public static boolean validateProjectDate(Period date) {
        return (0 <= date.getYears() || 50 > date.getYears());
    }     
}

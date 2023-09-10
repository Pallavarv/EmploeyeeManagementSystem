package com.proj.employeemanagement.service;

import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.model.AddressVO;

/**
 * Implements methods to store, get, update and delete employee address detail 
 * by connecting to AddressDAO.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public interface AddressService {

    /** 
     * Inputs details to store address of employee.
     *
     * @param addressVO  address to be stored
     * @return           int if id of the address stored
     */
    public int addAddress(AddressVO addressVO) throws EmployeeManagementException;
    
    /**
     * Updates address of employee.
     *
     * @param addressVO   employee id to be validated 
     * @return            boolean  true if id matches or false
     */
    public boolean updateAddress(AddressVO addressVO) throws EmployeeManagementException;
    
    /**
     * Gets specfic address details using address id.
     * 
     * @param addressId  address id used to fetch details of employee 
     * @return           AddressVO specific address details 
     */
    public AddressVO getAddress(int addressId) throws EmployeeManagementException;
    
    /** 
     * Gets address id and employee id for the purpose of reference for user.
     *
     * @return List<Address> list of address id and employee id
     */
    public List<AddressVO> getAddressId() throws EmployeeManagementException;
    
    /** 
     * Removes specific employee using employee id.
     *
     * @param addressId  id of employee who is to be removed 
     * @return           boolean true if employee removed or false
     */
    public boolean removeAddressById(int addressId) throws EmployeeManagementException;   

    /** 
     * Clears all employees from record.
     *
     * @return boolean true if all employee records are removed
     */
    public boolean removeAllAddress() throws EmployeeManagementException;   
     
    /**
     * Validates id is integer and does not allow id to be 0.
     *
     * @param addressId  address id to be validated 
     * @return           boolean  true if id matches or false
     */
    public boolean validateId(int addressId);

    /**
     * Validates door number contains alphabet, number or slashes and does not 
     * allow spaces.
     *
     * @param doorNumber  door number to be validated
     * @return            boolean  true if doorNumber matches or false
     */
    public boolean validateDoorNumber(String doorNumber);

    /**
     * Validates street is alphabet and allows spaces only between names.
     *
     * @param street street to be validated
     * @return       boolean  true if street matches or false
     */
    public boolean validateStreet(String street);

    /**
     * Validates district is alphabet and allows spaces only between names.
     *
     * @param district  district to be validated
     * @return          boolean  true if district matches or false
     */
    public boolean validateDistrict(String district);

    /**
     * Validates state is alphabet and allows spaces only between names.
     *
     * @param state  state to be validated
     * @return       boolean  true if state matches or false
     */
    public boolean validateState(String state);

    /**
     * Validates country is alphabet and allows spaces only between names.
     *
     * @param country  country to be validated
     * @return         boolean  true if country matches or false
     */
    public boolean validateCountry(String country);
    
    /**
     * Validates pincode is integer, between 110000 to 999999 and must contain
     * only 6 digits.
     *
     * @param pincode  pincode to be validated
     * @return         boolean  true if pincode matches or false
     */
    public boolean validatePincode(int pincode);
}

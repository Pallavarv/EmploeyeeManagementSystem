package com.proj.employeemanagement.controller;

import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.model.AddressVO;
import com.proj.employeemanagement.service.AddressService;
import com.proj.employeemanagement.service.impl.AddressServiceImpl;

/**
 * AddressController class was created to connect AddressService class and
 * EmployeeView class.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public class AddressController {

    private AddressService addressService = new AddressServiceImpl();

    /** 
     * Inputs details to store address of employee.
     *
     * @param addressVO  address to be stored
     * @return           int if id of the address stored
     */
    public int addAddress(AddressVO addressVO) throws EmployeeManagementException {
        return addressService.addAddress(addressVO);
    }
    
    /**
     * Updates address of employee using id.
     *
     * @param addressVO   address object used to update address
     * @return            boolean true if address is updated or false
     */
    public boolean updateAddress(AddressVO addressVO) throws EmployeeManagementException {
        return addressService.updateAddress(addressVO);
    }
    
    /**
     * Gets specfic address details using address id.
     * 
     * @param addressId  address id used to fetch details of employee 
     * @return           AddressVO specific address details 
     */
    public AddressVO getAddress(int addressId) throws EmployeeManagementException {
        return addressService.getAddress(addressId);
    }    
    
    /** 
     * Gets address id and employee id for the purpose of reference for user.
     *
     * @return List<Address> list of address id and employee id
     */
    public List<AddressVO> getAddressId() throws EmployeeManagementException {
        return addressService.getAddressId();
    }
    
    /**
     * Deletes specfic employee address using address id.
     *
     * @param addressId  address id of address to be removed   
     * @return           boolean true if address is removed or false
     */
    public boolean deleteAddressById(int addressId) throws EmployeeManagementException {
        return addressService.removeAddressById(addressId);
    }   

    /**
     * Deletes all employees address from record.
     *
     * @return  boolean true if all addresses are removed or false
     */
    public boolean deleteAllAddress() throws EmployeeManagementException {
        return addressService.removeAllAddress();
    } 
     
    /**
     * Validates id is integer and does not allow id to be 0.
     *
     * @param addressId  employee id to be validated
     * @return           boolean  true if id matches or false
     */
    public boolean isValidId(int addressId) {
        return addressService.validateId(addressId); 
    }
      
    /**
     * Validates door number contains alphabet, number or slashes and does not 
     * allow spaces.
     *
     * @param doorNumber  door number to be validated
     * @return            boolean  true if doorNumber matches or false
     */
    public boolean isValidDoorNumber(String doorNumber) {
        return addressService.validateDoorNumber(doorNumber);
    }
    
    /**
     * Validates street is alphabet and allows spaces only between names.
     *
     * @param street street to be validated
     * @return       boolean  true if street matches or false
     */
    public boolean isValidStreet(String street) {
        return addressService.validateStreet(street);
    }

    /**
     * Validates district is alphabet and allows spaces only between names.
     *
     * @param district  district to be validated
     * @return          boolean  true if district matches or false
     */
    public boolean isValidDistrict(String district) {
        return addressService.validateDistrict(district);
    }
    
    /**
     * Validates state is alphabet and allows spaces only between names.
     *
     * @param state  state to be validated
     * @return       boolean  true if state matches or false
     */
    public boolean isValidState(String state) {
        return addressService.validateState(state);
    }    

    /**
     * Validates country is alphabet and allows spaces only between names.
     *
     * @param country  country to be validated
     * @return         boolean  true if country matches or false
     */
    public boolean isValidCountry(String country) {
        return addressService.validateCountry(country);
    }
    
    /**
     * Validates pincode is integer and must contain only 6 digits.
     *
     * @param pincode  pincode to be validated
     * @return         boolean  true if pincode matches or false
     */
    public boolean isValidPincode(int pincode) {
        return addressService.validatePincode(pincode); 
    }              
}

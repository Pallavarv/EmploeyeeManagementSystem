package com.proj.employeemanagement.dao;

import java.util.List;

import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.persistence.Address;

/**
 * Implements application to insert, fetch, update, delete and validate record
 * from database.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public interface AddressDAO {

    /** 
     * Saves employee address into table.
     *
     * @param address  address object to get all details
     * @return         int if address is inserted in table
     */    
    public int saveAddress(Address address) throws EmployeeManagementException;
    
    /** 
     * Fetchs only address detail from table using address id.
     *
     * @param addressId  address id whose detail to be fetched
     * @return           Address address object to get all details
     */    
    public Address fetchAddress(int addressId) throws EmployeeManagementException;
    
    /** 
     * Gets address id and employee id for the purpose of reference for user.
     *
     * @return List<Address> list of address id and employee id
     */   
    public List<Address> fetchAllAddress() throws EmployeeManagementException;
       
    /**
     * Updates address of employee.
     *
     * @param address  address used to update the employee address
     * @return         Address if employee detail is updated  
     */
    public Address updateAddressById(Address address) throws EmployeeManagementException;
    
    /** 
     * Deletes single employee address from table.
     *
     * @param addressId  address id of record to be removed
     * @return           int if record is removed
     */
    public int deleteAddressById(int addressId) throws EmployeeManagementException;

    /** 
     * Deletes addresses all records from table.
     *
     * @return  int if record is removed
     */
    public int deleteAllAddress() throws EmployeeManagementException;        
}

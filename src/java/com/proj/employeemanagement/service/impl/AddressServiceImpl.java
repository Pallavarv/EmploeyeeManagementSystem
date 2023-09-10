package com.proj.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.proj.employeemanagement.dao.AddressDAO;
import com.proj.employeemanagement.dao.impl.AddressDAOImpl;
import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.mapper.AddressMapper;
import com.proj.employeemanagement.mapper.EmployeeMapper;
import com.proj.employeemanagement.persistence.Address;
import com.proj.employeemanagement.model.AddressVO;
import com.proj.employeemanagement.service.AddressService;

/**
 * Implements methods to store, get, update and delete employee address detail 
 * by connecting AddressDAO.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public class AddressServiceImpl implements AddressService {

    private AddressDAO addressDAO = new AddressDAOImpl();

    /** 
     * {@inheritDoc}
     */
    @Override
    public int addAddress(AddressVO addressVO) throws EmployeeManagementException {
        Address address = AddressMapper.convertAddressVOToAddress(addressVO);
        address.setEmployee(EmployeeMapper.convertEmployeeVOToEmployee(addressVO
                .getEmployee()));  
        return (addressDAO.saveAddress(address));
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public AddressVO getAddress(int addressId) throws EmployeeManagementException {
        AddressVO addressVO = null;
        Address address = addressDAO.fetchAddress(addressId); 
        
        if (null != address) {
            addressVO = AddressMapper.convertAddressToAddressVO(address);
        }
        return addressVO;
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean updateAddress(AddressVO addressVO) throws EmployeeManagementException {
        return (null != addressDAO.updateAddressById(AddressMapper
                .convertAddressVOToAddress(addressVO)));
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<AddressVO> getAddressId() throws EmployeeManagementException {
        List<Address> addressList = addressDAO.fetchAllAddress();
        List<AddressVO> addresses = new ArrayList<>();
        
        if (null != addressList) {
            for (Address address : addressList) {
                 addresses.add(AddressMapper.convertAddressToAddressVO(address));
            }
        }
        return addresses;
    }   
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean removeAddressById(int addressId) throws EmployeeManagementException {
        return (0 != addressDAO.deleteAddressById(addressId));
    }   

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean removeAllAddress() throws EmployeeManagementException {
        return (0 != addressDAO.deleteAllAddress());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateId(int addressId) {
        return (0 < addressId);
    }
            
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validatePincode(int pincode) {
        return (110000 < pincode && 999999 > pincode);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateDoorNumber(String doorNumber) {
        return validateInput(doorNumber, "^[1-9][0-9A-Za-z/]{0,5}$");       
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateStreet(String street) {
        return validateInput(street, "^[a-zA-Z][a-zA-Z]+([ ]?[a-zA-Z]?[a-zA-Z]*)?"
                + "([ ]?[a-zA-Z]?[a-zA-Z]*)?$");       
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateDistrict(String district) {
        return validateInput(district, "^[a-zA-Z][a-zA-Z]+([ ]?[a-zA-Z]?[a-zA-Z]*)?"
                + "([ ]?[a-zA-Z]?[a-zA-Z]*)?$");       
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateState(String state) {
        return validateInput(state, "^[a-zA-Z][a-zA-Z]+([ ]?[a-zA-Z]?[a-zA-Z]*)?"
                + "([ ]?[a-zA-Z]?[a-zA-Z]*)?$");       
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean validateCountry(String country) {
        return validateInput(country, "^[a-zA-Z][a-zA-Z]+([ ]?[a-zA-Z]?[a-zA-Z]*)?"
                + "([ ]?[a-zA-Z]?[a-zA-Z]*)?$");       
    }

    /**
     * Validates whether given detail matches with given regex pattern.
     *
     * @param detailToValidated  employee detail to be validated 
     * @param regex              regex pattern used for validation
     * @return                   boolean  true if detail matches or false
     */
    private boolean validateInput(String detailToValidated, String regex) {
        return detailToValidated.matches(regex);
    }   
}

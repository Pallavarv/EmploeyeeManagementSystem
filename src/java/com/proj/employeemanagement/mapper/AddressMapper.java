package com.proj.employeemanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import com.proj.employeemanagement.persistence.Address;
import com.proj.employeemanagement.model.AddressVO;

/**
 * AddressMapper class is used to convert AddressVO object to Address
 * object and vice versa.
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class AddressMapper {

    /** 
     * Converts AddressVO object to Address object.
     *
     * @param AddressVO  instance of AddressVO to be converted
     * @return           Address if addressVO is converted
     */
    public static Address convertAddressVOToAddress(AddressVO addressVO) {
        Address address = new Address();
        
        address.setId(addressVO.getId());
        address.setEmployee(EmployeeMapper.convertEmployeeVOToEmployee(addressVO
                .getEmployee()));
        address.setDoorNumber(addressVO.getDoorNumber());
        address.setStreet(addressVO.getStreet());
        address.setDistrict(addressVO.getDistrict());
        address.setState(addressVO.getState());
        address.setCountry(addressVO.getCountry());
        address.setPincode(addressVO.getPincode());
        return address;
    }

    /**
     * Converts Address object to AddressVO object.
     * 
     * @param address  instance of Address to be converted
     * @return          AddressVO if address is converted
     */
    public static AddressVO convertAddressToAddressVO(Address address) {
        AddressVO addressVO = new AddressVO();  
          
        addressVO.setId(address.getId());
        addressVO.setEmployee(EmployeeMapper.convertEmployeeToEmployeeVO(address
                .getEmployee()));
        addressVO.setDoorNumber(address.getDoorNumber());
        addressVO.setStreet(address.getStreet());
        addressVO.setDistrict(address.getDistrict());
        addressVO.setState(address.getState());
        addressVO.setCountry(address.getCountry());
        addressVO.setPincode(address.getPincode());
        return addressVO;
    }
    
    /** 
     * Converts list of AddressVO object to list of Address object.
     *
     * @param addressesVO  list of instance of AddressVO to be converted
     * @return             list of Addresses if AddressesVO is converted
     */
    public static List<Address> convertAddressesVOToAddresses(List<AddressVO> addressesVO) {
        List<Address> addresses = new ArrayList<>();
        
        for (AddressVO addressVO : addressesVO) {
                addresses.add(convertAddressVOToAddress(addressVO));
        }
        return addresses;
    }
    
    /**
     * Converts list of Address object to list of AddressVO object.
     * 
     * @param addresses  list of instance of Address to be converted
     * @return           list of AddressesVO if Addresses is converted
     */
    public static List<AddressVO> convertAddressesToAddressesVO(List<Address> addresses) {
        List<AddressVO> addressesVO = new ArrayList<>();
        
        for (Address address : addresses) {
                addressesVO.add(convertAddressToAddressVO(address));
        }
        return addressesVO;
    }
}

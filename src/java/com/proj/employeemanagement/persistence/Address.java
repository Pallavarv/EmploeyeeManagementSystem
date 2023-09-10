package com.proj.employeemanagement.persistence;

/**
 * An instance of class can save, get, set and print various value using fields
 * and methods of class.
 *
 * @version 1.00 
 * @author  Pallavan
 * @since   1.00
 */
public class Address {
    
    private int id;
    private int pincode;
    private String doorNumber;
    private String street;
    private String district;
    private String state;
    private String country;
    private Employee employee;

    public Address() {
    }

    public Address(Employee employee, String doorNumber, String street,
             String district, String state, String country, int pincode) {
   
        this.employee = employee;
        this.doorNumber = doorNumber;
        this.street = street;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public int getId() {
        return id;
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public int getPincode() {
        return pincode;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nDoor number: ").append(doorNumber)
                .append("\nStreet: ").append(street)
                .append("\nDistrict: ").append(district)
                .append("\nState: ").append(state)
                .append("\nCountry: ").append(country)
                .append("\nPincode: ").append(pincode)
                .append("\n");

        return stringBuilder.toString();
    }
}

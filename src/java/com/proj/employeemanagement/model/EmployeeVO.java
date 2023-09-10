package com.proj.employeemanagement.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An instance of class can save, get, set and print value using fields
 * and methods of class.
 *
 * @version 1.03 
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeVO {

    private float salary;
    private int id;
    private Date dob;
    private String emailId;
    private String name;
    private String phoneNumber;
    private List<AddressVO> addresses = new ArrayList<>();
    private List<ProjectVO> projects = new ArrayList<>();

    public EmployeeVO() {
    }

    public EmployeeVO(String name, String phoneNumber, Date dob, float salary, 
            String emailId) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.salary = salary;
        this.emailId = emailId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public float getSalary() {
        return salary;
    }

    public String getEmailId() {
        return emailId;
    }
  
    public List<AddressVO> getAddresses() {
        return addresses;
    } 
    
    public List<ProjectVO> getProjects() {
        return projects;
    }    

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public void setAddresses(List<AddressVO> addresses) {
        this.addresses = addresses;
    }
    
    public void setProjects(List<ProjectVO> projects) {
        this.projects = projects;
    }        

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Employee id: ").append(id)
                .append("\nEmployee name: ").append(name)
                .append("\nEmployee Phone number: ").append(phoneNumber)
                .append("\nEmployee date of birth: ").append(dateFormatter.format(dob))
                .append("\nEmployee salary: ").append(salary)
                .append("\nEmployee email id: ").append(emailId)
                .append("\n");

        return stringBuilder.toString();
    }
}

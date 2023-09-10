package com.proj.employeemanagement.persistence;

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
public class Project {

    private int id;
    private Date startDate;
    private String name;
    private String managerName;
    private String domain;
    private List<Employee> employees = new ArrayList<>();

    public Project() {
    }

    public Project(String name, String domain, Date startDate, String managerName) {

        this.name = name;
        this.domain = domain;
        this.startDate = startDate;
        this.managerName = managerName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getManagerName() {
        return managerName;
    }
  
    public List<Employee> getEmployees() {
        return employees;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }    

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Project id: ").append(id)
                .append("\nProject name: ").append(name)
                .append("\nProject domain: ").append(domain)
                .append("\nProject start date: ").append(dateFormatter.format(startDate))
                .append("\nProject manager name: ").append(managerName)
                .append("\n");

        return stringBuilder.toString();
    }
}

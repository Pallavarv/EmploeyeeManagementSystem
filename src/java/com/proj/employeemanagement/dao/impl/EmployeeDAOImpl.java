package com.proj.employeemanagement.dao.impl;

import java.util.List;

import com.proj.employeemanagement.connection.ConnectionFactory;
import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.persistence.Employee;
import com.proj.employeemanagement.util.ConstantUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.proj.employeemanagement.dao.EmployeeDAO;

/**
 * Implements application to insert, fetch, update, delete and validate record
 * from database.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    /** 
     * {@inheritDoc}
     */
    @Override     
    public int saveEmployee(Employee employee) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Integer employeeId = null;
      
        try {
            transaction = session.beginTransaction();
            employeeId = (Integer) session.save(employee); 
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            exception.printStackTrace();
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_001));
        } finally {
            ConnectionFactory.close(session); 
        }
        return employeeId;
    }

    /** 
     * {@inheritDoc}
     */
    @Override     
    public Employee fetchEmployeeById(int employeeId) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Employee employee = null;
      
        try {
            transaction = session.beginTransaction();
            employee = (Employee) session.get(Employee.class, employeeId);  
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_002)); 
        } finally {
            ConnectionFactory.close(session); 
        }
        return employee;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public List<Employee> fetchAllEmployee() throws EmployeeManagementException { 
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        List<Employee> employees = null;
         
        try {
            transaction = session.beginTransaction();
            employees = session.createQuery("FROM Employee").list(); 
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_003)); 
        } finally {
            ConnectionFactory.close(session); 
        }
        return employees;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeManagementException {    
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Employee detailUpdated = null;
         
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            detailUpdated = employee;
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_004));
        } finally {
            ConnectionFactory.close(session); 
        }
        return detailUpdated;
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public int deleteEmployeeById(int employeeId) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        int recordDeleted = 0;
      
        try {
            Query query = null;
            
            transaction = session.beginTransaction();
            query = session.createQuery("DELETE FROM Employee WHERE id = :id");
            recordDeleted = query.setParameter("id", employeeId).executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_005)); 
        } finally {
            ConnectionFactory.close(session);  
        }
        return recordDeleted;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int deleteAllEmployee() throws EmployeeManagementException { 
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        int recordDeleted = 0;
      
        try {
            transaction = session.beginTransaction();
            recordDeleted = session.createQuery("DELETE FROM Employee")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_006));
        } finally {
            ConnectionFactory.close(session);  
        }
        return recordDeleted;
    } 

    /** 
     * {@inheritDoc}
     */
    @Override
    public Employee fetchEmployeeByPhoneNumber(String phoneNumber) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Employee employee = null;
      
        try {
            Query query = null;
            StringBuilder hqlQuery = new StringBuilder();
            hqlQuery.append("FROM Employee WHERE phone_number = ")
            .append(":phone_number");
            
            transaction = session.beginTransaction();
            query = session.createQuery(hqlQuery.toString());
            employee = (Employee) query.setParameter("phone_number", phoneNumber).uniqueResult();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            exception.printStackTrace();
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_007));

        } finally {
            ConnectionFactory.close(session);  
        }
        return employee;      
    }   

    /** 
     * {@inheritDoc}
     */
    @Override 
    public Employee fetchEmployeeByEmailId(String emailId) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Employee employee = null;
      
        try {
            Query query = null;
            StringBuilder hqlQuery = new StringBuilder();
            hqlQuery.append("FROM Employee WHERE email_id = ")
            .append(":email_id");
            
            transaction = session.beginTransaction();
            query = session.createQuery(hqlQuery.toString());
            employee = (Employee) query.setParameter("email_id", emailId).uniqueResult();         
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_008));
        } finally {
            ConnectionFactory.close(session);  
        }
        return employee;      
    }          
}

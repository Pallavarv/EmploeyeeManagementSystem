package com.proj.employeemanagement.dao.impl;

import java.util.List;

import com.proj.employeemanagement.connection.ConnectionFactory;
import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.persistence.Address;
import com.proj.employeemanagement.util.ConstantUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.proj.employeemanagement.dao.AddressDAO;

/**
 * Implements application to insert, fetch, update, delete and validate record
 * from database.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public class AddressDAOImpl implements AddressDAO {

    /** 
     * {@inheritDoc}
     */
    @Override     
    public int saveAddress(Address address) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Integer addressId = null;
      
        try {
            transaction = session.beginTransaction();
            addressId = (Integer) session.save(address); 
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_009));
        } finally {
            ConnectionFactory.close(session);  
        }
        return addressId;
    }

    /** 
     * {@inheritDoc}
     */
    @Override     
    public Address fetchAddress(int addressId) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Address address = null;
      
        try {
            transaction = session.beginTransaction();
            address = (Address) session.get(Address.class, addressId);  
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_010));
        } finally {
           ConnectionFactory.close(session);  
        }
        return address;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Address updateAddressById(Address address) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Address detailUpdated = null;
         
        try {
            transaction = session.beginTransaction();
            detailUpdated = (Address) session.merge(address); 
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_012));
        } finally {
            ConnectionFactory.close(session); ; 
        }
        return detailUpdated;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int deleteAddressById(int addressId) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        int recordDeleted = 0;
      
        try {
            Query query = null;
            
            transaction = session.beginTransaction();
            query = session.createQuery("DELETE FROM Address WHERE id = :id");
            recordDeleted = query.setParameter("id", addressId).executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_013));
        } finally {
            ConnectionFactory.close(session);  
        }
        return recordDeleted;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int deleteAllAddress() throws EmployeeManagementException { 
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        int recordDeleted = 0;
      
        try {
            transaction = session.beginTransaction();
            recordDeleted = session.createQuery("DELETE FROM Address")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_014));
        } finally {
            ConnectionFactory.close(session);  
        }
        return recordDeleted;
    } 

    /** 
     * {@inheritDoc}
     */
    @Override    
    public List<Address> fetchAllAddress() throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        List<Address> address = null;
         
        try {
            transaction = session.beginTransaction(); 
            address = session.createQuery("SELECT address FROM Address address JOIN FETCH address.employee").list();  
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_011));
        } finally {
            ConnectionFactory.close(session); 
        }
        return address;     
    }      
}

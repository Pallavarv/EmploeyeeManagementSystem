package com.proj.employeemanagement.dao.impl;

import java.util.List;

import com.proj.employeemanagement.connection.ConnectionFactory;
import com.proj.employeemanagement.exception.EmployeeManagementException;
import com.proj.employeemanagement.persistence.Project;
import com.proj.employeemanagement.util.ConstantUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.proj.employeemanagement.dao.ProjectDAO;

/**
 * Implements application to insert, fetch, update, delete and validate record
 * from database.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public class ProjectDAOImpl implements ProjectDAO {

    /** 
     * {@inheritDoc}
     */
    @Override     
    public int saveProject(Project project) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Integer projectId = null;
      
        try {
            transaction = session.beginTransaction();
            projectId = (Integer) session.save(project); 
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_015));
        } finally {
            ConnectionFactory.close(session); 
        }
        return projectId;
    }

    /** 
     * {@inheritDoc}
     */
    @Override     
    public Project fetchProjectById(int projectId) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Project project = null;
      
        try {
            transaction = session.beginTransaction();
            project = (Project) session.get(Project.class, projectId);  
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_016));
        } finally {
            ConnectionFactory.close(session); 
        }
        return project;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public List<Project> fetchAllProject() throws EmployeeManagementException { 
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        List<Project> projects = null;
         
        try {
            transaction = session.beginTransaction();
            projects = session.createQuery("FROM Project").list(); 
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_017));
        } finally {
            ConnectionFactory.close(session); 
        }
        return projects;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Project updateProject(Project project) throws EmployeeManagementException {    
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        Project detailUpdated = null;
         
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(project);
            detailUpdated = project;
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_018));
        } finally {
            ConnectionFactory.close(session); 
        }
        return detailUpdated;
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public int deleteProjectById(int projectId) throws EmployeeManagementException {
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        int recordDeleted = 0;
      
        try {
            Query query = null;
            
            transaction = session.beginTransaction();
            query = session.createQuery("DELETE FROM Project WHERE id = :id");
            recordDeleted = query.setParameter("id", projectId).executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_019));
        } finally {
            ConnectionFactory.close(session);  
        }
        return recordDeleted;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int deleteAllProject() throws EmployeeManagementException { 
        Session session = ConnectionFactory.getSession();
        Transaction transaction = null;
        int recordDeleted = 0;
      
        try {
            transaction = session.beginTransaction();
            recordDeleted = session.createQuery("DELETE FROM Project")
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            if (null != transaction) { 
                transaction.rollback();
            }
            throw new EmployeeManagementException(ConstantUtil.ERROR.get(ConstantUtil.ERROR_CODE_020));
        } finally {
            ConnectionFactory.close(session);  
        }
        return recordDeleted;
    }           
}

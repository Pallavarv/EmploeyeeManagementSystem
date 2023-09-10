package com.proj.employeemanagement.connection;

import java.io.File;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * ConnectionFactory class sets-up connection with the database.
 *
 * @version 1.00
 * @author  Pallavan
 * @since   1.00
 */
public class ConnectionFactory {

    private static SessionFactory sessionFactory;

    private ConnectionFactory() {
    }

    /** 
     * Sets-up connection to the database.
     *
     * @return SessionFactory  used to create session
     */
    public static SessionFactory getSessionFactory() {
    

        try {
            if (null == sessionFactory || sessionFactory.isClosed()) {                                                
                sessionFactory = new Configuration().configure()
                        .buildSessionFactory(); 
            } 
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return sessionFactory;
    }
    
    /**
     * Get session.
     *
     * @return Session used to get a physical connection with a database
     */        
    public static Session getSession() {
        return getSessionFactory().openSession();
    }
    
    /**
     * Closes session.
     *
     * @param session  session to be closed
     */    
    public static void close(Session session) {
        try {
            if (null != session) {
                session.close();
            }
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }       
}

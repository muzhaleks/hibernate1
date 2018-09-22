package com.infopulse.factory;

import com.infopulse.dao.CustomerDAO;
import com.infopulse.dao.HibernateCustomerDAO;
import org.hibernate.SessionFactory;

import javax.persistence.Persistence;

public class Factory {
    public final static Factory INSTANCE = new Factory();

    private final SessionFactory sessionFactory;

    private Factory(){
        sessionFactory = (SessionFactory) Persistence
                .createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
    }

    public static Factory getInstance(){
        return INSTANCE;
    }

    public CustomerDAO getCustomerDAO(){
         return new HibernateCustomerDAO(sessionFactory);
    }
}

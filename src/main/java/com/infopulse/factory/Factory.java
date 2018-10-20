package com.infopulse.factory;

import com.infopulse.dao.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import javax.persistence.Persistence;

public class Factory {
    public final static Factory INSTANCE = new Factory();

    private final SessionFactory sessionFactory;

    private Factory(){
        sessionFactory = (SessionFactory) Persistence
                .createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
        Statistics stat = sessionFactory.getStatistics();
        stat.setStatisticsEnabled(true);
    }

    public static Factory getInstance(){
        return INSTANCE;
    }

    public CustomerDAO getCustomerDAO(){
         return new HibernateCustomerDAO(sessionFactory);
    }
    public GoodsDAO getGoodsDAO(){
        return new GoodsDAOImpl(sessionFactory);
    }

    public DepositDAO getDepositDAO(){
        return new HibernateDepositDAO(sessionFactory);
    }
}

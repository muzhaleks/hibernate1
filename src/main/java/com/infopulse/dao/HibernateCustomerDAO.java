package com.infopulse.dao;

import com.infopulse.entity.Count;
import com.infopulse.entity.Customer;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateCustomerDAO implements CustomerDAO{
    private final SessionFactory sessionFactory;

    public HibernateCustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public void insertCustomer(Customer customer) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateCustomer(Customer customer) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Customer> result = entityManager.createQuery( "from Customer", Customer.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public void deleteAll(){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("DELETE FROM customers");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Customer findCustomer(String name, String surename) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager
                .createQuery( "from Customer c where c.name = :name AND c.surename = :surename", Customer.class)
                .setParameter("name", name)
                .setParameter("surename", surename)
                .getResultList()
                .get(0);
        entityManager.getTransaction().commit();
        entityManager.close();
        return customer;

    }

    @Override
    public long customers(){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Long count = entityManager
                .createQuery("SELECT COUNT(c) FROM Customer c", Long.class).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return count;
    }

    @Override
    public Count numberCustomers(){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Count count = entityManager
                .createQuery("SELECT new com.infopulse.entity.Count(COUNT(c)) FROM Customer c", Count.class).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return count;
    }

}

package com.infopulse.dao;

import com.infopulse.entity.*;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
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

    @Override
    public Customer findCustomerCriteria(String name, String surename){
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);

        ParameterExpression<String> nameParam = builder.parameter( String.class );
        ParameterExpression<String> surenameParam = builder.parameter( String.class );
        criteria = criteria.select(root).where(builder
                .and(
                        builder.equal(root.get("name"), nameParam),
                        builder.equal(root.get("surename"),surenameParam)
                )
        );

        Customer customer = entityManager
                .createQuery(criteria)
                .setParameter(nameParam, name)
                .setParameter(surenameParam, surename)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return customer;


    }

    @Override
    public List<String> findPhoneByCustomerName(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteria = builder.createQuery(String.class);

        Root<Phone> root = criteria.from(Phone.class);
        Join<Phone, Customer> phoneCustomer
                = root.join(Phone_.customer);

       // ParameterExpression<Customer> customerParam = builder.parameter( Customer.class );
        criteria = criteria
                .select(root.get(Phone_.PHONE_NUMBER))
                .where(builder.equal(root.get(Phone_.customer).get(Customer_.NAME), name));
        List<String> phones= entityManager
                .createQuery(criteria)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return phones;
    }

}

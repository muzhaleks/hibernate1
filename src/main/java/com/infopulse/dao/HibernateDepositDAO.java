package com.infopulse.dao;

import com.infopulse.entity.Bank;
import com.infopulse.entity.Customer;
import com.infopulse.entity.Deposit;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class HibernateDepositDAO implements DepositDAO {
    private final SessionFactory sessionFactory;

    public HibernateDepositDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public List<Deposit> findDepositByCustomer(String name) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Deposit> deposits = entityManager
                .createQuery("select d FROM Deposit d JOIN d.customerDeposit cd WHERE cd.customer.name=:name")
                .setParameter("name", name)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return deposits;

    }

    @Override
    public void insertDeposit(Deposit deposit) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(deposit);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

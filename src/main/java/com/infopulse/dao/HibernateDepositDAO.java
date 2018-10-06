package com.infopulse.dao;

import com.infopulse.entity.Bank;
import com.infopulse.entity.Customer;
import com.infopulse.entity.Deposit;
import com.infopulse.entity.Phone;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
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
    public List<Phone> findPhoneByDepositValue(BigDecimal dep) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Phone> phones = entityManager
                .createQuery("SELECT ph FROM Phone ph JOIN ph.customer cst " +
                        "JOIN cst.customerDeposits cd WHERE cd.deposit.dep = :value")
                .setParameter("value", dep)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return phones;

    }

    @Override
    public List<String> findPhoneAsStringByDepositValue(BigDecimal dep) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<String> phones = entityManager
                .createQuery("SELECT " +
                        "CASE " +
                        "WHEN ph.phoneNumber IS NULL " +
                        "THEN 'This field is null'" +
                        "ELSE ph.phoneNumber " +
                        "END " +
                        "FROM Phone ph JOIN ph.customer cst " +
                        "JOIN cst.customerDeposits cd " +
                        "WHERE cd.deposit.dep = :value")
                .setParameter("value", dep)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return phones;

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

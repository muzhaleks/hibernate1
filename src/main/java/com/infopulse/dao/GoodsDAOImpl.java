package com.infopulse.dao;

import com.infopulse.entity.Goods;
import com.infopulse.entity.OtherGoods;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class GoodsDAOImpl implements GoodsDAO {
    private final SessionFactory sessionFactory;

    public GoodsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public void insertGoods(Goods goods) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(goods);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void insertOtherGoods(OtherGoods otherGoods) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(otherGoods);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

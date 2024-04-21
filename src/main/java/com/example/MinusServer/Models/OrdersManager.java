package com.example.MinusServer.Models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class OrdersManager {
    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }
    public Orders getOrders(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Orders> query = session.createQuery("from Orders where Id = "+id, Orders.class);
            return query.list().get(0);
        }
    }
    public void addOrders(Orders orders) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(orders);
            transaction.commit();
        }
    }

    public void updateOrders(Orders orders) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(orders);
            transaction.commit();
        }
    }

    public void deleteOrders(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(getOrders(id));
            transaction.commit();
        }
    }
}
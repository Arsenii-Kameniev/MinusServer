package com.example.MinusServer.Models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class OrderStatusManager {
    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }
    public OrderStatus getOrderStatus(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<OrderStatus> query = session.createQuery("from OrderStatus where Id = "+id, OrderStatus.class);
            return query.list().get(0);
        }
    }

    public void addOrderStatus(OrderStatus orderStatus) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(orderStatus);
            transaction.commit();
        }
    }

    public void updateOrderStatus(OrderStatus orderStatus) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(orderStatus);
            transaction.commit();
        }
    }

    public void deleteOrderStatus(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(getOrderStatus(id));
            transaction.commit();
        }
    }
}
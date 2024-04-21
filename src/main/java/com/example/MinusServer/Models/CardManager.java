package com.example.MinusServer.Models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CardManager {
    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public List<MyUser> getAllEmployes() {
        try (Session session = sessionFactory.openSession()) {
            Query<MyUser> query = session.createQuery("from MyUser", MyUser.class);
            return query.list();
        }
    }

    public void addEmployee(MyUser employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public void updateEmployee(MyUser employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    public void deleteEmployee(MyUser employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    public List<Card> getAllCards() {
        try (Session session = sessionFactory.openSession()) {
            Query<Card> query = session.createQuery("from Card", Card.class);
            return query.list();
        }
    }

    public Card getCard(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Card> query = session.createQuery("from Card where Id = "+id, Card.class);
            return query.list().get(0);
        }
    }

    public void addCard(Card card) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(card);
            transaction.commit();
        }
    }

    public void updateCard(Card card) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(card);
            transaction.commit();
        }
    }

    public void deleteCard(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(getCard(id));
            transaction.commit();
        }
    }

    public void addProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }
    }

    public void updateProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }
    }

    public void deleteProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }
    }

    public void addOrderStatus(OrderStatus orderStatus) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(orderStatus);
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

    public void deleteOrderStatus(OrderStatus orderStatus) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(orderStatus);
            transaction.commit();
        }
    }

    public void addOrders(Orders orders) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(orders);
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

    public void deleteOrders(Orders orders) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(orders);
            transaction.commit();
        }
    }

    public void addCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        }
    }

    public void updateCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        }
    }

    public void deleteCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(category);
            transaction.commit();
        }
    }
}

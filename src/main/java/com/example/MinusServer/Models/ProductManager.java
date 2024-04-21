package com.example.MinusServer.Models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ProductManager {
    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public Product getProduct(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("from Product where Id = "+id, Product.class);
            return query.list().get(0);
        }
    }

    public void addProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(product);
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

    public void deleteProduct(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(getProduct(id));
            transaction.commit();
        }
    }
}

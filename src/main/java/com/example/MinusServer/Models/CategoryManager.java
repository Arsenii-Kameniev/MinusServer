package com.example.MinusServer.Models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class CategoryManager {
    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }
    public Category getCategory(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Category> query = session.createQuery("from Category where Id = "+id, Category.class);
            return query.list().get(0);
        }
    }
    public void addCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(category);
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

    public void deleteCategory(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(getCategory(id));
            transaction.commit();
        }
    }
}
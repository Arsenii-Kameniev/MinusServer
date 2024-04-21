package com.example.MinusServer.Models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserManager {
    private SessionFactory sessionFactory;

    public void  init() {
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public List<MyUser> getAllEmployes() {
        try (Session session = sessionFactory.openSession()) {
            Query<MyUser> query = session.createQuery("from MyUser",MyUser.class);
            return query.list();
        }
    }
    public MyUser getEmployee(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<MyUser> query = session.createQuery("from MyUser where Id = "+id, MyUser.class);
            return query.list().get(0);
        }
    }
    public void addEmployee(MyUser employee ) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        }
    }

    public void updateEmployee( MyUser employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    public void deleteEmployee( int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(getEmployee(id));
            transaction.commit();
        }
    }

}

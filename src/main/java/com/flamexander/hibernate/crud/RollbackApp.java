package com.flamexander.hibernate.crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RollbackApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("configs/crud/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            SimpleItem newSimpleItem = new SimpleItem("Chair", 1000);
            System.out.println("Before save: " + newSimpleItem);
            session.save(newSimpleItem);
            System.out.println("After save: " + newSimpleItem);
            session.getTransaction().rollback();
            System.out.println("After rollback: " + newSimpleItem);

            session = factory.getCurrentSession();
            session.beginTransaction();
//            System.out.println(session.get(SimpleItem.class, newSimpleItem.getId()));
            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}

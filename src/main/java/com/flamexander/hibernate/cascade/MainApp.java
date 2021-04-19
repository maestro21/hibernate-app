package com.flamexander.hibernate.cascade;

import com.flamexander.hibernate.PrepareDataApp;
import com.flamexander.hibernate.crud.SimpleItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/cascade/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Order order = session.get(Order.class, 1L);
            order.getProducts().get(0).setPrice(1000);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}

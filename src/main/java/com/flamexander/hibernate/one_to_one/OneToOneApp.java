package com.flamexander.hibernate.one_to_one;

import com.flamexander.hibernate.PrepareDataApp;
import com.flamexander.hibernate.crud.SimpleItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToOneApp {
    public static void main(String[] args) throws Exception {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/one_to_one/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, 1L);
            System.out.println(employee);
//            System.out.println(employee.getDetails());
            session.getTransaction().commit();
            System.out.println(employee.getDetails());
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}

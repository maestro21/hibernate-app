package com.flamexander.hibernate.one_to_many_and_back;

import com.flamexander.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class OneToManyAndBackApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/one_to_many_and_back/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            University university = session.get(University.class, 1L);
////            session.detach(university);
//
            session.getTransaction().commit();
            System.out.println(university.getClass().getName());
            System.out.println(university);
            System.out.println("Students: ");
            for (Student s : university.getStudents()) {
                System.out.println(s.getName());
            }
////            session.getTransaction().commit();
//
//            university.getStudents().clear();
//
////            Student student = new Student("Zahar", university);
////            session.save(student);
////            university.getStudents().add(student);
//            session.getTransaction().commit();

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            University newUniversity = new University();
//            newUniversity.setTitle("ABC");
//            newUniversity.setStudents(new ArrayList<>());
//
//            Student s1 = new Student("Bob1", newUniversity);
//            Student s2 = new Student("Bob2", newUniversity);
//            newUniversity.getStudents().add(s1);
//            newUniversity.getStudents().add(s2);
//
//            session.save(newUniversity);
//            session.getTransaction().commit();


//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            University universityFetch = (University)session.getNamedQuery("withStudents")
//                    .setParameter("id", 2L)
//                    .getSingleResult();
//            session.getTransaction().commit();
//            System.out.println(universityFetch.getStudents());
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}

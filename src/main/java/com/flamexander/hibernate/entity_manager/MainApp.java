package com.flamexander.hibernate.entity_manager;

import com.flamexander.hibernate.PrepareDataApp;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MainApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        EntityManager em = null;
        try {
            em = EMF.getEntityManager();
            em.getTransaction().begin();
            SimpleEntity se1 = em.find(SimpleEntity.class, 1L);

            System.out.println(se1);
            em.getTransaction().commit();

            em.getTransaction().begin();
            SimpleEntity se2 = em.find(SimpleEntity.class, 1L);
            System.out.println(se2);
            em.getTransaction().commit();

            em.getTransaction().begin();
            em.remove(se2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
            EMF.close();
        }
    }
}

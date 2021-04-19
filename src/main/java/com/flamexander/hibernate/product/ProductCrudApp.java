package com.flamexander.hibernate.product;

import com.flamexander.hibernate.PrepareDataApp;
import com.flamexander.hibernate.crud.SimpleItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCrudApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/crud/hibernate.cfg.xml")
                .buildSessionFactory();
        initDb();
        create("Water", 1);
        create("Beer", 2);
        create("Phone", 100);
        create("TV", 3000);
        create("Car", 65000);
    }

    public static void initDb() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            String sql = Files.lines(Paths.get("products_new.sql")).collect(Collectors.joining(" "));
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void list() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> items = session.createQuery("from Product").getResultList();
            System.out.println("List of products\n");
            System.out.println(items + "\n");
            session.getTransaction().commit();
        }
    }

    public static void shutdown() {
        factory.close();
    }

    public static void create(String title, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = new Product(title, cost);
            session.save(product);
            session.getTransaction().commit();
            System.out.println("Created product: " + product);
        }
    }

    public static void read(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println("Reading product with id " + id + ":" + product);
            session.getTransaction().commit();
        }
    }

    public static void update(Long id, int newCost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println("Product before update: " + product);
            product.setCost(newCost);
            session.getTransaction().commit();
            System.out.println("Product after update: " + product);
        }
    }

    public static void delete(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
            System.out.println("Deleted product: " + product);
        }
    }

    public static void main(String[] args) {
        try {
            init();
            read(2L);
            create("Test Item", 999);
            update(3L, 333);
            delete(4L);
            list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}

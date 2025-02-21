package com.humber.menu.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.humber.menu.model.Category;
import com.humber.menu.model.MenuItem;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(MenuItem.class).addAnnotatedClass(Category.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
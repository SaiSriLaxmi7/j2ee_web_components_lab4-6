package com.humber.menu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.humber.menu.model.MenuItem;
import com.humber.menu.util.HibernateUtil;

public class MenuItemDAO {

	public void save(MenuItem menuItem) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(menuItem);
		transaction.commit();
		session.close();
	}
	
	public void delete(int id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		MenuItem menuItem = session.get(MenuItem.class, id);
		session.remove(menuItem);
		transaction.commit();
		session.close();
	}
	
	public void update(int id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		MenuItem menuItem = session.get(MenuItem.class, id);
		session.merge(menuItem);
		transaction.commit();
		session.close();
	}
	
	public List<MenuItem> getAllMenuItems() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<MenuItem> menuItems = session.createQuery("FROM MenuItem", MenuItem.class).list();
		session.close();
		return menuItems;
	}
	

}

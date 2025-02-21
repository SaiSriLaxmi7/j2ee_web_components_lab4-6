package com.humber.menu.dao;

import com.humber.menu.model.Category;
import com.humber.menu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CategoryDAO {

	public void save(Category category) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(category);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Category> getAllCategories() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Category", Category.class).list();
		}
	}
}

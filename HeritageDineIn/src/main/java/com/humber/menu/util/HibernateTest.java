package com.humber.menu.util;

import com.humber.menu.model.Category;
import com.humber.menu.model.MenuItem;
import com.humber.menu.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Create three categories
            Category category1 = new Category();
            category1.setName("Desserts");

            Category category2 = new Category();
            category2.setName("Beverages");

            Category category3 = new Category();
            category3.setName("Appetizers");

            // Save categories first (so we can use them for menu items)
            session.save(category1);
            session.save(category2);
            session.save(category3);
            session.flush();  // Ensures the category IDs are generated
            
            // Create menu items for category 1 (Desserts)
            MenuItem item1 = new MenuItem("Chocolate Cake", "Rich dark chocolate cake", 5.99, category1);
            MenuItem item2 = new MenuItem("Cheesecake", "Classic New York cheesecake", 6.49, category1);
            MenuItem item3 = new MenuItem("Tiramisu", "Coffee-flavored Italian dessert", 7.99, category1);

            // Create menu items for category 2 (Beverages)
            MenuItem item4 = new MenuItem("Cappuccino", "Espresso with steamed milk and foam", 3.99, category2);
            MenuItem item5 = new MenuItem("Iced Tea", "Cold brewed iced tea", 2.49, category2);

            // Create menu items for category 3 (Appetizers)
            MenuItem item6 = new MenuItem("Spring Rolls", "Crispy rolls filled with vegetables", 4.99, category3);
            MenuItem item7 = new MenuItem("Bruschetta", "Grilled bread topped with tomatoes and garlic", 5.49, category3);

            // Save all menu items
            session.save(item1);
            session.save(item2);
            session.save(item3);
            session.save(item4);
            session.save(item5);
            session.save(item6);
            session.save(item7);

            transaction.commit();
            System.out.println("Records inserted successfully!");

            // Fetch and print records
            List<Category> categories = session.createQuery("FROM Category", Category.class).list();
            System.out.println("Categories:");
            for (Category cat : categories) {
                System.out.println("ID: " + cat.getId() + ", Name: " + cat.getName());
            }

            List<MenuItem> menuItems = session.createQuery("FROM MenuItem", MenuItem.class).list();
            System.out.println("Menu Items:");
            for (MenuItem menuItem : menuItems) {
                System.out.println("ID: " + menuItem.getId() + ", Name: " + menuItem.getName() + ", Price: " + menuItem.getPrice() + ", Category: " + menuItem.getCategory().getName());
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

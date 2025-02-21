package com.humber.menu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price", nullable = false)
	private double price;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	private Category category;
	
	public MenuItem() {}

	// Constructor for new menu items (without ID)
	public MenuItem(String name, String description, double price, Category category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	// Constructor for existing menu items (with ID)
	public MenuItem(int id, String name, String description, double price, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	// Getter for ID
	public int getId() {
		return id;
	}

	// Setter for ID
	public void setId(int id) {
		this.id = id;
	}

	// Other getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}

package com.sample.app.model;

public class Order {
	private int id;
	private String description;

	public Order(int id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", description=" + description + "]";
	}

}
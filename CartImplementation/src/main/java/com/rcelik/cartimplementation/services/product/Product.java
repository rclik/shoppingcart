package com.rcelik.cartimplementation.services.product;

import com.rcelik.cartimplementation.services.category.Category;

public class Product {
	private Category category;
	private String title;
	private Double price;

	private Product() {
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Builder class is a helper class to generate the Product object
	 * <ul>
	 * <li>Uses {@link Builder#withPrice(Double)} method to add product price</li>
	 * <li>Uses {@link Builder#build()}} method to create new instance of
	 * {@link Product} class</li>
	 * </ul>
	 */
	public static class Builder {
		private Category category;
		private String title;
		private Double price;

		public Builder(Category category, String title) {
			this.category = category;
			this.title = title;
			this.price = Double.valueOf(0);
		}

		public Product build() {
			Product p = new Product();
			p.setPrice(this.price);
			p.setTitle(this.title);
			p.setCategory(this.category);
			return p;
		}

		public Builder withPrice(Double price) {
			this.price = price;
			return this;
		}
	}
}

package com.rcelik.cartimplementation.services.cart;

import com.rcelik.cartimplementation.services.product.Product;

/**
 * This class is used for cart item. It stands a product object and its quantity
 * amount
 */
public class CartItem {

	private Product product;
	private int quantity = 0;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

}

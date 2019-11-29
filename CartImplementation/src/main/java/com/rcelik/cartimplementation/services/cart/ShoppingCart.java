package com.rcelik.cartimplementation.services.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stands for holding CartItems
 */
public class ShoppingCart {

	private List<CartItem> list;

	public ShoppingCart() {
		this.list = new ArrayList<CartItem>();
	}

	public ShoppingCart(CartItem item) {
		this();
		this.list.add(item);
	}

	public List<CartItem> getAllItems() {
		return this.list;
	}

	public void addItem(CartItem item) {
		this.list.add(item);
	}

}

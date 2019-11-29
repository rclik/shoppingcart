package com.rcelik.cartimplementation.services.cart;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.product.Product;

public class ShoppingCartTest {

	@Test
	public final void createChartTest() {
		Product apple = new Product.Builder(new Category.Builder("Fruit").build(), "Apple").build();
		CartItem item1 = new CartItem(apple, 1);

		ShoppingCart cart = new ShoppingCart(item1);

		List<CartItem> result = cart.getAllItems();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(item1, result.get(0));
	}

	@Test
	public final void addChartTest() {
		Product apple = new Product.Builder(new Category.Builder("Fruit").build(), "Apple").build();
		CartItem item1 = new CartItem(apple, 1);

		ShoppingCart cart = new ShoppingCart();
		cart.addItem(item1);

		List<CartItem> result = cart.getAllItems();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(item1, result.get(0));
	}

}

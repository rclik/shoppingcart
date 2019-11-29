package com.rcelik.cartimplementation.services.cart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.product.Product;

public class ShoppingCartItemTest {
	private static Category fruit;
	private static String appleName;
	private static Double applePrice;
	private static Product apple;
	private static CartItem item;

	@Before
	public final void createCardItem() {
		fruit = new Category.Builder("Fruit").withParentCategory(new Category.Builder("Food").build()).build();
		appleName = "Apple";
		applePrice = Double.valueOf(5.5);
		apple = new Product.Builder(fruit, appleName).withPrice(applePrice).build();

		item = new CartItem(apple, 2);
	}

	@Test
	public final void createCardItemTest() {
		// check that item product is apple
		Assert.assertEquals(apple, item.getProduct());

		// check quantity is 2
		Assert.assertEquals(2, item.getQuantity());
	}

}

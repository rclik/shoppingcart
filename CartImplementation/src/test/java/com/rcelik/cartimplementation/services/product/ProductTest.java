package com.rcelik.cartimplementation.services.product;

import org.junit.Assert;
import org.junit.Test;

import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.product.Product;

public class ProductTest {

	@Test
	public final void testProductItem() {
		Category fruit = new Category.Builder("Fruit").withParentCategory(new Category.Builder("Food").build()).build();
		String appleName = "Apple";
		Double price = Double.valueOf(5.5);

		Product apple = new Product.Builder(fruit, appleName).withPrice(price).build();

		// check product title is right
		Assert.assertEquals(appleName, apple.getTitle());

		// check product price is right
		Assert.assertEquals(price, apple.getPrice());

		// check product category is right
		Assert.assertEquals(fruit, apple.getCategory());

	}

}

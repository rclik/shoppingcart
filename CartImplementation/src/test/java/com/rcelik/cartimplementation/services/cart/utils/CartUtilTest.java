package com.rcelik.cartimplementation.services.cart.utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.cart.utils.orderer.CartOrderer;
import com.rcelik.cartimplementation.services.cart.utils.printer.CartPrinter;
import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.product.Product;

public class CartUtilTest {

	@Test
	public final void orderCartTest() {
		Category fruitCategory = new Category.Builder("Fruit").build();
		Product apple = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(10)).build();
		Product apple2 = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(15)).build();
		Product banana = new Product.Builder(fruitCategory, "Banana").withPrice(Double.valueOf(15)).build();
		Product orange = new Product.Builder(fruitCategory, "Orange").withPrice(Double.valueOf(15)).build();

		ShoppingCart cart = new ShoppingCart(apple, 3, 4);
		cart.addProduct(apple2, 4);
		cart.addProduct(banana, 10);
		cart.addProduct(orange);

		Category electronicCategory = new Category.Builder("Electornic").build();
		Product laptop = new Product.Builder(electronicCategory, "Laptop").withPrice(Double.valueOf(1000)).build();
		cart.addProduct(laptop);

		// orders products with respect to category title
		CartOrderer orderer = CartUtilFactory.getInstance().getOrderer();
		List<Product> orderedList = orderer.order(cart);

		Assert.assertEquals(electronicCategory, orderedList.get(0).getCategory());
	}

	@Test
	public final void printCartTest() {
		Category fruitCategory = new Category.Builder("Fruit").build();
		Product apple = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(10)).build();
		Product apple2 = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(15)).build();
		Product banana = new Product.Builder(fruitCategory, "Banana").withPrice(Double.valueOf(15)).build();
		Product orange = new Product.Builder(fruitCategory, "Orange").withPrice(Double.valueOf(15)).build();

		ShoppingCart cart = new ShoppingCart(apple, 3, 4);
		cart.addProduct(apple2, 4);
		cart.addProduct(banana, 10);
		cart.addProduct(orange);

		Category electronicCategory = new Category.Builder("Electornic").build();
		Product laptop = new Product.Builder(electronicCategory, "Laptop").withPrice(Double.valueOf(1000)).build();
		cart.addProduct(laptop);

		// orders products with respect to category title
		CartPrinter printer = CartUtilFactory.getInstance().getPrinter();
		CartOrderer orderer = CartUtilFactory.getInstance().getOrderer();
		printer.printOrderly(cart, orderer);
	}
}

package com.rcelik.cartimplementation.services.delivery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.product.Product;

public class DeliveryCostCalculatorTest {

	private Double costPerDelivery;
	private Double costPerProduct;
	private Double fixedCost;
	private DeliveryCostCalculator deliveryCalculator;

	@Before
	public final void initialize() {
		costPerDelivery = Double.valueOf(2.0);
		costPerProduct = Double.valueOf(3.0);
		fixedCost = Double.valueOf(2.99);
		deliveryCalculator = new DeliveryCostCalculator(costPerDelivery, costPerProduct, fixedCost);
	}

	@Test
	public final void createDeliveryCostCalculator() {
		Assert.assertNotNull(deliveryCalculator);
		Assert.assertEquals(costPerDelivery, deliveryCalculator.getCostPerDelivery());
		Assert.assertEquals(costPerProduct, deliveryCalculator.getCostPerProduct());
		Assert.assertEquals(fixedCost, deliveryCalculator.getFixedCost());
	}

	@Test
	public final void calculaForTest() {
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

		Double result = deliveryCalculator.calculateFor(cart);
		Assert.assertEquals(Double.valueOf(21.99), result, 0.001);
	}

}

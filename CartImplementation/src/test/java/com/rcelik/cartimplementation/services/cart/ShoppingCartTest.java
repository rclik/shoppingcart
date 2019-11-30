package com.rcelik.cartimplementation.services.cart;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.discounts.campaign.Campaign;
import com.rcelik.cartimplementation.services.discounts.campaign.Coupon;
import com.rcelik.cartimplementation.services.discounts.campaign.DiscountFactory;
import com.rcelik.cartimplementation.services.discounts.campaign.DiscountType;
import com.rcelik.cartimplementation.services.product.Product;

public class ShoppingCartTest {

	@Test
	public final void createChartTest() {
		int maxDiscountNumber = 3;
		Integer quantity = 2;
		Category fruitCategory = new Category.Builder("Fruit").build();
		Product apple = new Product.Builder(fruitCategory, "Apple").build();
		Product apple2 = new Product.Builder(fruitCategory, "Apple").build();

		ShoppingCart cart = new ShoppingCart(apple, quantity, maxDiscountNumber);
		List<Product> result = cart.getProducts();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(maxDiscountNumber, cart.getMaxDiscountNumber());

		Product comingProduct = result.get(0);
		Assert.assertEquals(apple, comingProduct);
		Assert.assertEquals(quantity, cart.getProductQuantity(comingProduct));

		cart.addProduct(apple2);
		cart.addProduct(apple2, 2);

		Integer distProductNumber = 2;
		Integer distCategoryNumber = 1;
		Assert.assertEquals(distProductNumber, cart.getDistinctProductNumber());
		Assert.assertEquals(distCategoryNumber, cart.getDistinctCategoryNumber());
	}

	@Test
	public final void applyDiscountsTest() {
		Integer quantity = 2;
		Category fruitCategory = new Category.Builder("Fruit").build();
		Product apple = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(10)).build();
		Product apple2 = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(15)).build();
		ShoppingCart cart = new ShoppingCart(apple, quantity, 4);
		cart.addProduct(apple2);

		Campaign rateCampaign = DiscountFactory.getInstance().getDiscount(DiscountType.AMOUNT, fruitCategory, 1,
				Double.valueOf(2)); // will remove 2 if it has at least two fruit category item

		Campaign amountCampaign = DiscountFactory.getInstance().getDiscount(DiscountType.RATE, fruitCategory, 1,
				Double.valueOf(2)); // will remove 2% if it has at least two fruit category item

		Coupon rateCoupon = DiscountFactory.getInstance().getDiscount(DiscountType.RATE, Double.valueOf(2),
				Double.valueOf(2)); // will remove 2% if it has total price

		Coupon amountCoupon = DiscountFactory.getInstance().getDiscount(DiscountType.AMOUNT, Double.valueOf(2),
				Double.valueOf(2)); // will remove 2% if it has total price

		cart.applyDiscounts(rateCampaign, amountCampaign, rateCoupon, amountCoupon);

		Double totalCampaignDiscount = Double.valueOf(2.7);
		Double totalCouponDiscount = Double.valueOf(2.7);

		Assert.assertEquals(totalCampaignDiscount, cart.getCampaignDiscount());
		Assert.assertEquals(totalCouponDiscount, cart.getCouponDiscount());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void applyDiscountsFailTest() {
		Integer quantity = 2;
		Category fruitCategory = new Category.Builder("Fruit").build();
		Product apple = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(10)).build();
		Product apple2 = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(15)).build();
		ShoppingCart cart = new ShoppingCart(apple, quantity, 2);
		cart.addProduct(apple2);

		Campaign rateCampaign = DiscountFactory.getInstance().getDiscount(DiscountType.AMOUNT, fruitCategory, 1,
				Double.valueOf(2)); // will remove 2 if it has at least two fruit category item

		Campaign amountCampaign = DiscountFactory.getInstance().getDiscount(DiscountType.RATE, fruitCategory, 1,
				Double.valueOf(2)); // will remove 2% if it has at least two fruit category item

		Coupon rateCoupon = DiscountFactory.getInstance().getDiscount(DiscountType.RATE, Double.valueOf(2),
				Double.valueOf(2)); // will remove 2% if it has total price

		Coupon amountCoupon = DiscountFactory.getInstance().getDiscount(DiscountType.AMOUNT, Double.valueOf(2),
				Double.valueOf(2)); // will remove 2 if it has total price

		cart.applyDiscounts(rateCampaign, amountCampaign, rateCoupon, amountCoupon);

	}

	@Test
	public final void applyDiscountTest() {
		Integer quantity = 2;
		Category fruitCategory = new Category.Builder("Fruit").build();
		Product apple = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(10)).build();
		Product apple2 = new Product.Builder(fruitCategory, "Apple").withPrice(Double.valueOf(15)).build();
		ShoppingCart cart = new ShoppingCart(apple, quantity, 2);
		cart.addProduct(apple2);

		Coupon amountCoupon = DiscountFactory.getInstance().getDiscount(DiscountType.AMOUNT, Double.valueOf(2),
				Double.valueOf(2)); // will remove 2 if it has total price

		cart.applyCoupon(amountCoupon);

		Double totalCouponDiscount = Double.valueOf(2);
		Assert.assertEquals(totalCouponDiscount, cart.getCouponDiscount());

	}

}

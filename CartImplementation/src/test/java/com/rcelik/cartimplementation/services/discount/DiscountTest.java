package com.rcelik.cartimplementation.services.discount;

import org.junit.Assert;
import org.junit.Test;

import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.discounts.Campaign;
import com.rcelik.cartimplementation.services.discounts.Coupon;
import com.rcelik.cartimplementation.services.discounts.DiscountFactory;
import com.rcelik.cartimplementation.services.discounts.DiscountPriority;
import com.rcelik.cartimplementation.services.discounts.DiscountType;

/**
 * There are discounts: Campaign and Coupon
 * 
 * Each discount has discount types, minimum amount and discount amount But
 * campaign is applicable for a category.
 * 
 * 
 */
public class DiscountTest {

	@Test
	public final void createAmountCampaignTest() {
		int minPurchaseAmount = 2;
		double discountAmount = Double.valueOf(20);
		Category food = new Category.Builder("Food").build();
		checkForCampaign(DiscountType.AMOUNT, minPurchaseAmount, discountAmount, food);
	}

	@Test
	public final void createRateCampaignTest() {
		Integer minPurchaseAmount = 1;
		Double discountAmount = Double.valueOf(10);
		Category food = new Category.Builder("Food").build();
		checkForCampaign(DiscountType.RATE, minPurchaseAmount, discountAmount, food);
	}

	@Test
	public final void createNullCampaignTest() {
		Integer minPurchaseAmount = 1;
		Double discountAmount = Double.valueOf(10);
		Category food = new Category.Builder("Food").build();

		Campaign camp = DiscountFactory.getInstance().getDiscount(DiscountType.DEFAULT, food, minPurchaseAmount,
				discountAmount);
		Assert.assertNull(camp);
	}

	private void checkForCampaign(DiscountType type, Integer minPurchaseAmount, Double discountAmount,
			Category category) {
		Campaign camp = DiscountFactory.getInstance().getDiscount(type, category, minPurchaseAmount, discountAmount);
		// check type
		Assert.assertEquals(type, camp.getType());
		// check category
		Assert.assertEquals(category, camp.getCategory());
		// check minPurchaseAmount
		Assert.assertEquals(minPurchaseAmount, camp.getMinPurchasedItemNumber());
		// check discountAmount
		Assert.assertEquals(discountAmount, camp.getDiscountAmount());
	}

	@Test
	public final void createAmountCouponTest() {
		double minPurchaseAmount = Double.valueOf(10);
		double discountAmount = Double.valueOf(10);
		checkForCoupon(DiscountType.AMOUNT, minPurchaseAmount, discountAmount);
	}

	@Test
	public final void createRateCouponTest() {
		double minPurchaseAmount = Double.valueOf(10);
		double discountAmount = Double.valueOf(10);
		checkForCoupon(DiscountType.RATE, minPurchaseAmount, discountAmount);
	}

	@Test
	public final void createNullCouponTest() {
		double minPurchaseAmount = Double.valueOf(10);
		double discountAmount = Double.valueOf(10);
		Coupon discount = DiscountFactory.getInstance().getDiscount(DiscountType.DEFAULT, minPurchaseAmount,
				discountAmount);
		Assert.assertNull(discount);
	}

	private void checkForCoupon(DiscountType type, double minPurchaseAmount, double discountAmount) {
		Coupon camp = DiscountFactory.getInstance().getDiscount(type, minPurchaseAmount, discountAmount);
		// check type
		Assert.assertEquals(type, camp.getType());
		// check minPurchaseAmount
		Assert.assertEquals(minPurchaseAmount, camp.getMinPurchasedAmount(), 0.0001);
		// check discountAmount
		Assert.assertEquals(discountAmount, camp.getDiscountAmount(), 0.0001);
	}

	@Test
	public final void getPaymentOrderTest() {
		Double minPurchaseAmount = Double.valueOf(10);
		Double discountAmount = Double.valueOf(10);
		Coupon discount = DiscountFactory.getInstance().getDiscount(DiscountType.RATE, minPurchaseAmount,
				discountAmount);
		Assert.assertEquals(DiscountPriority.SECOND, discount.getDiscountPriority());
	}

}

package com.rcelik.cartimplementation.services.discount.campaign;

import org.junit.Assert;
import org.junit.Test;

import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.discounts.campaign.Campaign;
import com.rcelik.cartimplementation.services.discounts.campaign.Coupon;
import com.rcelik.cartimplementation.services.discounts.campaign.DiscountFactory;
import com.rcelik.cartimplementation.services.discounts.campaign.DiscountType;

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
		Double minPurchaseAmount = Double.valueOf(10);
		Double discountAmount = Double.valueOf(10);
		Category food = new Category.Builder("Food").build();
		checkForCampaign(DiscountType.AMOUNT, minPurchaseAmount, discountAmount, food);
	}

	@Test
	public final void createRateCampaignTest() {
		Double minPurchaseAmount = Double.valueOf(10);
		Double discountAmount = Double.valueOf(10);
		Category food = new Category.Builder("Food").build();
		checkForCampaign(DiscountType.RATE, minPurchaseAmount, discountAmount, food);
	}

	private void checkForCampaign(DiscountType type, Double minPurchaseAmount, Double discountAmount,
			Category category) {
		Campaign camp = DiscountFactory.getInstance().getDiscount(type, category, minPurchaseAmount, discountAmount);
		// check type
		Assert.assertEquals(type, camp.getType());
		// check category
		Assert.assertEquals(category, camp.getCategory());
		// check minPurchaseAmount
		Assert.assertEquals(minPurchaseAmount, camp.getMinPurchaseAmount());
		// check discountAmount
		Assert.assertEquals(discountAmount, camp.getDiscountAmount());
	}

	@Test
	public final void createAmountCouponTest() {
		Double minPurchaseAmount = Double.valueOf(10);
		Double discountAmount = Double.valueOf(10);
		checkForCoupon(DiscountType.AMOUNT, minPurchaseAmount, discountAmount);
	}

	@Test
	public final void createRateCouponTest() {
		Double minPurchaseAmount = Double.valueOf(10);
		Double discountAmount = Double.valueOf(10);
		checkForCoupon(DiscountType.RATE, minPurchaseAmount, discountAmount);
	}

	private void checkForCoupon(DiscountType type, Double minPurchaseAmount, Double discountAmount) {
		Coupon camp = DiscountFactory.getInstance().getDiscount(type, minPurchaseAmount, discountAmount);
		// check type
		Assert.assertEquals(type, camp.getType());
		// check minPurchaseAmount
		Assert.assertEquals(minPurchaseAmount, camp.getMinPurchaseAmount());
		// check discountAmount
		Assert.assertEquals(discountAmount, camp.getDiscountAmount());
	}

}

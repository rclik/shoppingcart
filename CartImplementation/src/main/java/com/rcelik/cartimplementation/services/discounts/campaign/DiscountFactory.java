package com.rcelik.cartimplementation.services.discounts.campaign;

import com.rcelik.cartimplementation.services.category.Category;

public class DiscountFactory {

	private static DiscountFactory instance;

	private DiscountFactory() {
	}

	public static DiscountFactory getInstance() {
		if (instance == null)
			instance = new DiscountFactory();
		return instance;
	}

	/**
	 * @return {@link Campaign}
	 * @param type               specifies discount type {@link DiscountType}
	 * @param category           category of the Campaign, since a campaign can be
	 *                           applied to a category
	 * @param minPurchuaseAmount minimum purchase amount
	 * @param discountAmount     discount amount Creates a Campaign object
	 * 
	 */
	public Campaign getDiscount(DiscountType type, Category category, int minPurchasedItemNumber,
			Double discountAmount) {
		switch (type) {
		case RATE:
			return new RateCampaign(category, minPurchasedItemNumber, discountAmount);

		case AMOUNT:
			return new AmountCampaign(category, minPurchasedItemNumber, discountAmount);
		default:
			return null;
		}
	}

	/**
	 * @return {@link Coupon}
	 * @param type              specifies discount type {@link DiscountType}
	 * @param minPurchaseAmount minimum purchase amount
	 * @param discountAmount    discount amount Creates a Campaign object
	 * 
	 */
	public Coupon getDiscount(DiscountType type, Double minPurchaseAmount, Double discountAmount) {
		switch (type) {
		case AMOUNT:
			return new AmountCoupon(minPurchaseAmount, discountAmount);
		case RATE:
			return new RateCoupon(minPurchaseAmount, discountAmount);
		default:
			return null;
		}
	}

}

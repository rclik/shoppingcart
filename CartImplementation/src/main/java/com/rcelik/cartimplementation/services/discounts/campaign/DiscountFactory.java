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
	public Campaign getDiscount(DiscountType type, Category category, Double minPurchuaseAmount,
			Double discountAmount) {
		return new Campaign(type, category, minPurchuaseAmount, discountAmount);
	}

	/**
	 * @return {@link Coupon}
	 * @param type               specifies discount type {@link DiscountType}
	 * @param minPurchuaseAmount minimum purchase amount
	 * @param discountAmount     discount amount Creates a Campaign object
	 * 
	 */
	public Coupon getDiscount(DiscountType type, Double minPurchuaseAmount, Double discountAmount) {
		return new Coupon(type, minPurchuaseAmount, discountAmount);
	}

}

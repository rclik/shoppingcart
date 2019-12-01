package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.category.Category;

/**
 * This class is a factory class that generates discounts.
 */
public class DiscountFactory {

	private static DiscountFactory instance;
	private static final int maximumRate = 100;

	private DiscountFactory() {
	}

	public static DiscountFactory getInstance() {
		if (instance == null)
			instance = new DiscountFactory();
		return instance;
	}

	/**
	 * @return {@link Campaign}
	 * @param type                   specifies discount type {@link DiscountType}
	 * @param category               category of the Campaign, since a campaign can
	 *                               be applied to a category
	 * @param minPurchasedItemNumber minimum purchase amount
	 * @param discountAmount         discount amount Creates a Campaign object
	 * 
	 * @throws IllegalArgumentException when discount type is rate and discount
	 *                                  amount is bigger than 100 and less than 0
	 */
	public Campaign getDiscount(DiscountType type, Category category, int minPurchasedItemNumber, Double discountAmount)
			throws IllegalArgumentException {
		switch (type) {
		case RATE:
			checkRateTest(discountAmount);
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
	 * @throws IllegalArgumentException when discount type is rate and discount
	 *                                  amount is bigger than 100 and less than 0
	 */
	public Coupon getDiscount(DiscountType type, Double minPurchaseAmount, Double discountAmount)
			throws IllegalArgumentException {
		switch (type) {
		case AMOUNT:
			return new AmountCoupon(minPurchaseAmount, discountAmount);
		case RATE:
			checkRateTest(discountAmount);
			return new RateCoupon(minPurchaseAmount, discountAmount);
		default:
			return null;
		}
	}

	private void checkRateTest(Double discountAmount) throws IllegalArgumentException {
		if (discountAmount > maximumRate || discountAmount < 0)
			throw new IllegalArgumentException("Discount amount cannot be bigger than maximum rate: " + maximumRate);
	}
}

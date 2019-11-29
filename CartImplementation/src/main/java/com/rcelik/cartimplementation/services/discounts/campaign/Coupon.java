package com.rcelik.cartimplementation.services.discounts.campaign;

/**
 * This is subclass of {@link Discount}
 */
public class Coupon extends Discount {
	/**
	 * Creates the Coupon Object
	 * 
	 * @param type               specifies discount type {@link DiscountType}
	 * @param minPurchuaseAmount minimum purchase amount
	 * @param discountAmount     discount amount
	 */
	public Coupon(DiscountType type, Double amount, Double discountAmount) {
		super(type, amount, discountAmount);
	}

}

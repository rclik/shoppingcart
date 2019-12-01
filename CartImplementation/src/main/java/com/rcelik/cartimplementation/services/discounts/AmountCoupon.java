package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

public class AmountCoupon extends Coupon {

	/**
	 * Third priority discount
	 */
	public AmountCoupon(Double amount, Double discountAmount) {
		super(DiscountType.AMOUNT, amount, discountAmount, DiscountPriority.THIRD);
	}

	/**
	 * Increments coupon discount of given chart
	 */
	@Override
	protected void applyDiscount(ShoppingCart cart) {
		cart.addCouponDiscount(this.discountAmount);
	}

}

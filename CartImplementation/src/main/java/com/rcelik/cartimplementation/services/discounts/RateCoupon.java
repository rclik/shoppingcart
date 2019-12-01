package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

class RateCoupon extends Coupon {

	/**
	 * Fourth Priority Discount
	 */
	RateCoupon(Double amount, Double discountAmount) {
		super(DiscountType.RATE, amount, discountAmount, DiscountPriority.FOURTH);
	}

	/**
	 * calculates the discount with total price with discount
	 */
	@Override
	protected void applyDiscount(ShoppingCart cart) {
		double totalAmountAfterApplyingDiscounts = cart.getTotalCartPriceWithDiscounts();
		cart.addCouponDiscount(totalAmountAfterApplyingDiscounts * getDiscountAmount() / 100);
	}
}

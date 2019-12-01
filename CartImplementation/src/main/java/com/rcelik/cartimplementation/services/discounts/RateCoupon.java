package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

class RateCoupon extends Coupon {

	RateCoupon(Double amount, Double discountAmount) {
		super(DiscountType.RATE, amount, discountAmount);
	}

	/**
	 * calculates the discount with total price without any discount
	 */
	@Override
	protected void applyDiscount(ShoppingCart cart) {
		double categoryTotalPrice = cart.getTotalCartPriceWithoutDiscounts();
		cart.addCouponDiscount(categoryTotalPrice * this.getDiscountAmount() / 100);
	}
}

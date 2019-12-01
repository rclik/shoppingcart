package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

class RateCoupon extends Coupon {

	RateCoupon(Double amount, Double discountAmount) {
		super(DiscountType.RATE, amount, discountAmount);
	}

	@Override
	protected void applyDiscount(ShoppingCart cart) {
		double categoryTotalPrice = cart.getTotalCartPriceWithoutDiscounts();
		cart.addCouponDiscount(categoryTotalPrice * this.getDiscountAmount() / 100);
//		System.out.println("[RateCoupon] " + cart.getCouponDiscount());
	}
}

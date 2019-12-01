package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

public class AmountCoupon extends Coupon {

	public AmountCoupon(Double amount, Double discountAmount) {
		super(DiscountType.AMOUNT, amount, discountAmount);
	}

	@Override
	protected void applyDiscount(ShoppingCart cart) {
		cart.addCouponDiscount(this.discountAmount);
//		System.out.println("[AmountCoupon] " + cart.getCouponDiscount());
	}

}

package com.rcelik.cartimplementation.services.discounts.campaign;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

/**
 * This is subclass of {@link Discount}
 */
public abstract class Coupon extends Discount {
	/**
	 * Creates the Coupon Object
	 * 
	 * @param type               specifies discount type {@link DiscountType}
	 * @param minPurchuaseAmount minimum purchase amount
	 * @param discountAmount     discount amount
	 */
	public Coupon(DiscountType type, Double minimumCartAmount, Double discountAmount) {
		super(type, minimumCartAmount, discountAmount, DiscountPriority.SECOND);
	}

	public Double getMinPurchasedAmount() {
		return this.minAmount;
	}

	/**
	 * Checks minimum purchased amount and cart`s total purchased price before
	 * discount is applied. cart`s total purchased price before discount should be
	 * bigger than discount`s minimum purchased amount.
	 */
	@Override
	protected boolean isApplicable(ShoppingCart cart) {
		return this.getMinPurchasedAmount() < cart.getTotalCartPriceWithoutDiscounts();
	}

}

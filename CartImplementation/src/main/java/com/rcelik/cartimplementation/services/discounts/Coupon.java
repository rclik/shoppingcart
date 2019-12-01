package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

/**
 * This is subclass of {@link Discount}
 */
public abstract class Coupon extends Discount {
	/**
	 * Creates the Coupon Object
	 * 
	 * @param type               specifies discount type {@link DiscountType}
	 * @param minPurchuaseAmount minimum purchase amount for the cart
	 * @param discountAmount     discount amount
	 */
	public Coupon(DiscountType type, Double minimumCartAmount, Double discountAmount) {
		super(type, minimumCartAmount, discountAmount, DiscountPriority.SECOND);
	}

	public Double getMinPurchasedAmount() {
		return this.minAmount;
	}

	/**
	 * @throws IllegalArgumentException when cart total price is less than minimum
	 *                                  total cart price.
	 */
	@Override
	protected void isApplicable(ShoppingCart cart) throws IllegalArgumentException {
		if (getMinPurchasedAmount() > cart.getTotalCartPriceWithoutDiscounts())
			throw new IllegalArgumentException(
					"Coupon is not suitable for given cart, Cart total price should have more than  + "
							+ getMinPurchasedAmount());
	}

}

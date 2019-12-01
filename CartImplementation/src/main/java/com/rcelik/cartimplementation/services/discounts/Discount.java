package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

/**
 * This is abstract class for discounts. It holds:
 * <ul>
 * <li>{@link Discount#minAmount} minimum purchase amount in order to be this
 * discount can be applied</li>
 * <li>{@link Discount#discountAmount} discount amount for the purchase</li>
 * <li>{@link Discount#type} discount type, there are two type of discount
 * please refer {@link DiscountType}</li>
 * </ul>
 */
public abstract class Discount {

	protected Double discountAmount;
	protected DiscountType type;
	protected Double minAmount;
	private DiscountPriority discountPriority;

	/**
	 * Abstract Discount constructor
	 * 
	 * @param type           specifies discount type
	 * @param minAmount      minimum purchase amount, it can be minimum category
	 *                       item number or minimum total cart price
	 * @param discountAmount discount amount, it can be an amount or percentage
	 * @param priority       priority of the discount, discount is applied according
	 *                       to this value
	 */
	protected Discount(DiscountType type, Double minAmount, Double discountAmount, DiscountPriority priority) {
		this.type = type;
		this.minAmount = minAmount;
		this.discountAmount = discountAmount;
		this.discountPriority = priority;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public DiscountType getType() {
		return type;
	}

	public DiscountPriority getDiscountPriority() {
		return this.discountPriority;
	}

	/**
	 * Applies the discount for given cart.
	 * 
	 * @see {@link Discount#isApplicable(ShoppingCart)}
	 * @see {@link Discount#apply(ShoppingCart)}
	 */
	public final void apply(ShoppingCart cart) throws IllegalArgumentException {
		isApplicable(cart);
		applyDiscount(cart);
	}

	/**
	 * Applies the discount to the cart
	 * 
	 * @see {@link AmountCampaign#apply(ShoppingCart)}}
	 * @see {@link RateCampaign#apply(ShoppingCart)}}
	 * @see {@link AmountCoupon#apply(ShoppingCart)}}
	 * @see {@link RateCoupon#apply(ShoppingCart)}}
	 */
	protected abstract void applyDiscount(ShoppingCart cart);

	/**
	 * Check if discount is applicable to cart
	 * 
	 * @see {@link Campaign#isApplicable(ShoppingCart)}}
	 * @see {@link Coupon#isApplicable(ShoppingCart)}}
	 * 
	 * @throws IllegalArgumentException when discount is not applicable for given
	 *                                  cart
	 */
	protected abstract void isApplicable(ShoppingCart cart) throws IllegalArgumentException;
}

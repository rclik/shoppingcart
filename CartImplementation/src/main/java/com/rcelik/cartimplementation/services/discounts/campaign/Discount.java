package com.rcelik.cartimplementation.services.discounts.campaign;

/**
 * This is abstract class for discounts. It holds:
 * <ul>
 * <li>{@link Discount#minPurchaseAmount} minimum purchase amount in order to be
 * this discount can be applied</li>
 * <li>{@link Discount#discountAmount} discount amount for the purchase</li>
 * <li>{@link Discount#type} discount type, there are two type of discount
 * please refer {@link DiscountType}</li>
 * </ul>
 */
public abstract class Discount {

	protected Double minPurchaseAmount;
	protected Double discountAmount;
	protected DiscountType type;

	/**
	 * Abstract Discount constructor
	 * 
	 * @param type              specifies discount type
	 * @param minPurchaseAmount minimum purchase amount
	 * @param discountAmount    discount amount
	 */
	protected Discount(DiscountType type, Double minPurchaseAmount, Double discountAmount) {
		this.type = type;
		this.minPurchaseAmount = minPurchaseAmount;
		this.discountAmount = discountAmount;
	}

	public Double getMinPurchaseAmount() {
		return minPurchaseAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public DiscountType getType() {
		return type;
	}
}

package com.rcelik.cartimplementation.services.discounts.campaign;

import com.rcelik.cartimplementation.services.category.Category;

/**
 * This is subclass of {@link Discount}. It has additional class variable
 * category since campaign can only be applied to a category.
 * <ul>
 * <li>{@link Campaign#category}  stands for campaign category</li>
 * </ul>
 */
public class Campaign extends Discount {

	private Category category;

	/**
	 * Creates the Campaign Object
	 * 
	 * @param type               specifies discount type {@link DiscountType}
	 * @param category           category of the Campaign, since a campaign can be
	 *                           applied to a category
	 * @param minPurchuaseAmount minimum purchase amount
	 * @param discountAmount     discount amount
	 */
	public Campaign(DiscountType type, Category category, Double amount, Double discountAmount) {
		super(type, amount, discountAmount);
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}
}

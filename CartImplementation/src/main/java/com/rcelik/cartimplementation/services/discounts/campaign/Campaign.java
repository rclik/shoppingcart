package com.rcelik.cartimplementation.services.discounts.campaign;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.category.Category;

/**
 * This is subclass of {@link Discount}. It has additional class variable
 * category since campaign can only be applied to a category.
 * <ul>
 * <li>{@link Campaign#category} stands for campaign category</li>
 * </ul>
 */
public abstract class Campaign extends Discount {

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
	public Campaign(DiscountType type, Category category, Integer minPurchasedItemNumber, Double discountAmount) {
		super(type, Double.valueOf(minPurchasedItemNumber), discountAmount, DiscountPriority.FIRST);
		this.category = category;
	}

	public Integer getMinPurchasedItemNumber() {
		return this.minAmount.intValue();
	}

	public Category getCategory() {
		return category;
	}

	/**
	 * Checks if category`s minimum item number and number of category item in the
	 * cart. If number if category item in the cart is bigger than minimum category
	 * number then it is applicable.
	 * 
	 * @return boolean
	 */
	@Override
	protected boolean isApplicable(ShoppingCart cart) {
		return this.getMinPurchasedItemNumber() < cart.getNumberOfCategoryItems(this.getCategory());
	}
}

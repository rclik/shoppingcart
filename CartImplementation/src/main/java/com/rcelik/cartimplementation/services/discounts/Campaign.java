package com.rcelik.cartimplementation.services.discounts;

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
	public Campaign(DiscountType type, Category category, Integer minPurchasedItemNumber, Double discountAmount,
			DiscountPriority discountPriority) {
		super(type, Double.valueOf(minPurchasedItemNumber), discountAmount, discountPriority);
		this.category = category;
	}

	public Integer getMinPurchasedItemNumber() {
		return this.minAmount.intValue();
	}

	public Category getCategory() {
		return category;
	}

	/**
	 * @throws IllegalArgumentException when number of category items in the cart is
	 *                                  less than minimum purchase item number.
	 */
	@Override
	protected void isApplicable(ShoppingCart cart) throws IllegalArgumentException {
		if (this.getMinPurchasedItemNumber() > cart.getNumberOfCategoryItems(this.getCategory()))
			throw new IllegalArgumentException("Campaign is not suitable for given cart, Cart should have more than "
					+ getMinPurchasedItemNumber() + " items for " + this.category.getTitle() + " category");
	}
}

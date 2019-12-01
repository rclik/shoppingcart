package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.category.Category;

class RateCampaign extends Campaign {

	/**
	 * Second Priority Discount
	 */
	RateCampaign(Category category, Integer purchasedItemNumber, Double discountAmount) {
		super(DiscountType.RATE, category, purchasedItemNumber, discountAmount, DiscountPriority.SECOND);
	}

	/**
	 * Calculates the discount with total price for given category in the cart
	 * without applying any discounts
	 */
	@Override
	protected void applyDiscount(ShoppingCart cart) {
		double categoryTotalPrice = cart.getCategoryTotalDiscountedPrice(getCategory());
		Double discount = categoryTotalPrice * this.getDiscountAmount() / 100;
		cart.setCategoryTotalDiscountedPrice(getCategory(), discount);
		cart.addCampaignDiscount(discount);
	}

}

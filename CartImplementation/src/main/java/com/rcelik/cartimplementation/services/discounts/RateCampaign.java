package com.rcelik.cartimplementation.services.discounts;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.category.Category;

class RateCampaign extends Campaign {

	RateCampaign(Category category, Integer purchasedItemNumber, Double discountAmount) {
		super(DiscountType.RATE, category, purchasedItemNumber, discountAmount);
	}

	@Override
	protected void applyDiscount(ShoppingCart cart) {
		double categoryTotalPrice = cart.getCategoryItemTotalPrice(this.getCategory());
		cart.addCampaignDiscount(categoryTotalPrice * this.getDiscountAmount()/100);
//		System.out.println("[RateCampaign] " + cart.getCampaignDiscount());
	}

}

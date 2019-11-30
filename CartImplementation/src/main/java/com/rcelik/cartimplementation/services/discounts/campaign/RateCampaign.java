package com.rcelik.cartimplementation.services.discounts.campaign;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.category.Category;

public class RateCampaign extends Campaign {

	public RateCampaign(Category category, Integer purchasedItemNumber, Double discountAmount) {
		super(DiscountType.RATE, category, purchasedItemNumber, discountAmount);
	}

	@Override
	protected void applyDiscount(ShoppingCart cart) {
		double categoryTotalPrice = cart.getCategoryItemTotalPrice(this.getCategory());
		cart.addCampaignDiscount(categoryTotalPrice * this.getDiscountAmount()/100);
		System.out.println("[RateCampaign] " + cart.getCampaignDiscount());
	}

}

package com.rcelik.cartimplementation.services.discounts.campaign;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.category.Category;

public class AmountCampaign extends Campaign {

	public AmountCampaign(Category category, Integer purchasedItemNumber, Double discountAmount) {
		super(DiscountType.AMOUNT, category, purchasedItemNumber, discountAmount);
	}

	@Override
	protected void applyDiscount(ShoppingCart cart) {
		cart.addCampaignDiscount(this.discountAmount);
		System.out.println("[AmountCampaign] " + cart.getCampaignDiscount());
	}
}

package com.rcelik.cartimplementation.services.cart;

import java.util.List;

import com.rcelik.cartimplementation.services.discounts.Discount;

/**
 * Helper class that calculates the discounts
 */
class CartDiscountListCalculator {
	List<Discount> discountList;
	ShoppingCart cart;

	CartDiscountListCalculator(List<Discount> discountList, ShoppingCart cart) {
		this.discountList = discountList;
		this.cart = cart;
	}

	/**
	 * Calculates the total discounts for coupon and campaign first orders the
	 * discounts then applies discount operation for corresponding discount
	 * 
	 * @throws IllegalArgumentException if given discount is not suitable for the
	 *                                  given cart. After getting this exception, if
	 *                                  cart object is used then already applied
	 *                                  discounts like appliedDiscount,
	 *                                  campaignDiscount, couponDiscount and
	 *                                  totalDiscount class instances should be
	 *                                  updated
	 */
	void calculate() throws IllegalArgumentException {
		// order the discounts
		orderDiscounts();
		cart.calculateTotalCartPrice(); // need to calculate total cart price
		for (Discount discount : discountList) {
			discount.apply(cart);
		}
	}

	/*
	 * This function orders the list so that FIRST priority discounts resides at the
	 * beginning of the list
	 */
	private void orderDiscounts() {
		this.discountList.sort((discount1, discount2) -> {
			return discount1.getDiscountPriority().getPriority() - discount2.getDiscountPriority().getPriority();
		});
	}

}

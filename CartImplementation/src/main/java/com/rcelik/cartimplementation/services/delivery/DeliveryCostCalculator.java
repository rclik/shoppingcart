package com.rcelik.cartimplementation.services.delivery;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;

public class DeliveryCostCalculator {

	private Double costPerDelivery;
	private Double costPerProduct;
	private Double fixedCost;

	public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost) {
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = fixedCost;
	}

	public Double getCostPerDelivery() {
		return costPerDelivery;
	}

	public Double getCostPerProduct() {
		return costPerProduct;
	}

	public Double getFixedCost() {
		return fixedCost;
	}

	/**
	 * Calculates the delivery cost for given cart by this formula:
	 * <p>
	 * distinctCategoryNumber * costPerDelivery + distinctProductNumber *
	 * costPerProduct + fixedCost
	 * </p>
	 * Then it sets the delivery cost for the cart.
	 * 
	 */
	public Double calculateFor(ShoppingCart cart) {
		int distinctCategoryNum = cart.getDistinctCategoryNumber();
		int distinctProductNum = cart.getDistinctProductNumber();
		Double result = (distinctCategoryNum * this.costPerDelivery) + (distinctProductNum * this.costPerProduct)
				+ this.fixedCost;
		cart.setDeliveryCost(result);
		return result;
	}

}

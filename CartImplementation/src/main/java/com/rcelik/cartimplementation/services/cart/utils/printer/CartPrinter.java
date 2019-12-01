package com.rcelik.cartimplementation.services.cart.utils.printer;

import java.util.List;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.cart.utils.CartUtil;
import com.rcelik.cartimplementation.services.cart.utils.orderer.CartOrderer;
import com.rcelik.cartimplementation.services.product.Product;

public abstract class CartPrinter implements CartUtil {

	public void printOrderly(ShoppingCart cart, CartOrderer orderer) {
		List<Product> list = orderer.order(cart);
		print(cart, list);
	}

	private void print(ShoppingCart cart, List<Product> list) {
		StringBuilder sb = new StringBuilder("Shopping Cart \n");
		list.stream().forEach(product -> {
			sb.append(product).append(", quantity: " + cart.getProductQuantity(product));
			sb.append("\n");
		});
		sb.append("Total Price Without Discount: ").append(cart.getTotalCartPriceWithoutDiscounts()).append("\n");
		sb.append("Total Discount: ").append(cart.getTotalDiscounts()).append("\n");
		sb.append("Delivery Cost: ").append(cart.getDeliveryCost()).append("\n");
		sb.append("Total Amount: ").append(cart.getTotalCost()).append("\n");
		print(sb.toString());
	}

	protected abstract void print(String text);
}

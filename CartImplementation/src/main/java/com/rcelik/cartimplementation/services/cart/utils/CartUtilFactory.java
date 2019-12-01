package com.rcelik.cartimplementation.services.cart.utils;

import java.util.HashMap;
import java.util.Map;

import com.rcelik.cartimplementation.services.cart.utils.orderer.CartOrderByProductCategoryTitle;
import com.rcelik.cartimplementation.services.cart.utils.orderer.CartOrderer;
import com.rcelik.cartimplementation.services.cart.utils.printer.CartPrinter;
import com.rcelik.cartimplementation.services.cart.utils.printer.SystemIOPrinter;

public class CartUtilFactory {

	private static final Map<CartUtilType, CartUtil> map = new HashMap<CartUtilType, CartUtil>();
	private static final CartUtilFactory instance;

	private CartUtilFactory() {
	}

	static {
		map.put(CartUtilType.Orderer, new CartOrderByProductCategoryTitle());
		map.put(CartUtilType.Printer, new SystemIOPrinter());
		instance = new CartUtilFactory();
	}

	public static final CartUtilFactory getInstance() {
		return instance;
	}

	public CartPrinter getPrinter() {
		return (CartPrinter) map.get(CartUtilType.Printer);
	}

	public CartOrderer getOrderer() {
		return (CartOrderer) map.get(CartUtilType.Orderer);
	}

}

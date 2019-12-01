package com.rcelik.cartimplementation.services.cart.utils.orderer;

import java.util.List;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.cart.utils.CartUtil;
import com.rcelik.cartimplementation.services.product.Product;

public abstract class CartOrderer implements CartUtil {
	public abstract List<Product> order(ShoppingCart cart);
}

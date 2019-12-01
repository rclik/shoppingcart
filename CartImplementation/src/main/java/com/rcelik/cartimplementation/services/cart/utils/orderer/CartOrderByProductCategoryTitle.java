package com.rcelik.cartimplementation.services.cart.utils.orderer;

import java.util.Collections;
import java.util.List;

import com.rcelik.cartimplementation.services.cart.ShoppingCart;
import com.rcelik.cartimplementation.services.product.Product;

public class CartOrderByProductCategoryTitle extends CartOrderer {

	@Override
	public List<Product> order(ShoppingCart cart) {
		List<Product> list = cart.getProducts();
		Collections.sort(list);
		return list;
	}

}

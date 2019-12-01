package com.rcelik.cartimplementation.services.cart.utils.printer;

public class SystemIOPrinter extends CartPrinter {

	@Override
	protected final void print(String text) {
		System.out.println(text);
	}

}

package com.rcelik.cartimplementation.services.discounts.campaign;

public enum DiscountPriority {
	FIRST(1), SECOND(2);

	private final int priority;

	private DiscountPriority(int levelCode) {
		this.priority = levelCode;
	}

	public int getPriority() {
		return this.priority;
	}
}

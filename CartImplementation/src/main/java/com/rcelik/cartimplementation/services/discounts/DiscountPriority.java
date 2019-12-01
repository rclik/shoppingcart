package com.rcelik.cartimplementation.services.discounts;

/**
 * Enum is used for prioritizing the discount
 */
public enum DiscountPriority {
	FIRST(1), SECOND(2), THIRD(3), FOURTH(4);

	private final int priority;

	private DiscountPriority(int levelCode) {
		this.priority = levelCode;
	}

	public int getPriority() {
		return this.priority;
	}
}

package com.rcelik.cartimplementation.services.discounts;

/**
 * There are two types of discounts
 * <ul>
 * <li>{@link DiscountType#AMOUNT} for amount type for example 50 TL
 * discount</li>
 * <li>{@link DiscountType#RATE} for rate type for example 20% discount</li>
 * </ul>
 */
public enum DiscountType {
	AMOUNT, RATE, DEFAULT
}

package com.rcelik.cartimplementation.services.cart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.rcelik.cartimplementation.services.cart.utils.CartUtilFactory;
import com.rcelik.cartimplementation.services.cart.utils.orderer.CartOrderer;
import com.rcelik.cartimplementation.services.cart.utils.printer.CartPrinter;
import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.discounts.Coupon;
import com.rcelik.cartimplementation.services.discounts.Discount;
import com.rcelik.cartimplementation.services.product.Product;

public class ShoppingCart {

	private Map<Product, Integer> cartItems;
	private int maxDiscountNumber;
	private int appliedDiscountNumber;
	private Double totalCartPriceWithoutDiscounts;
	private Double campaignDiscountAmount;
	private Double couponDiscountAmount;
	private Double deliveryCost;

	private ShoppingCart() {
		this.cartItems = new HashMap<Product, Integer>();
		this.maxDiscountNumber = 0;
		this.totalCartPriceWithoutDiscounts = 0.0;
		this.campaignDiscountAmount = 0.0;
		this.couponDiscountAmount = 0.0;
		this.deliveryCost = 0.0;
		this.appliedDiscountNumber = 0;
	}

	/**
	 * Constructor that creates ShoppingChart
	 * 
	 * @param product,          the cart is initialized with given product
	 * @param quantity          with given quantity
	 * @param maxDiscountNumber cart has maximum number of discounts to be added
	 */
	public ShoppingCart(Product product, Integer quantity, int maxDiscountNumber) {
		this();
		this.addProduct(product, quantity);
		this.maxDiscountNumber = maxDiscountNumber;
	}

	public int getMaxDiscountNumber() {
		return maxDiscountNumber;
	}

	/**
	 * Returns all product inside the cart
	 */
	public List<Product> getProducts() {
		List<Product> list = new ArrayList<Product>();
		this.cartItems.forEach((product, quantity) -> {
			list.add(product);
		});
		return list;
	}

	public Integer getProductQuantity(Product comingProduct) {
		return this.cartItems.containsKey(comingProduct) ? this.cartItems.get(comingProduct) : 0;
	}

	/**
	 * Adds a product to the cart.
	 * 
	 * @see ShoppingCart#addProduct(Product, int)
	 */
	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	/**
	 * Adds a product @param product with given quantity @param quantity If the
	 * product is already on the cart then increases its quantity with given
	 * quantity value.
	 */
	public void addProduct(Product product, int quantity) {
		Integer currentQuantity = getProductQuantity(product);
		this.cartItems.put(product, currentQuantity + quantity);
	}

	/**
	 * Returns distinct product number in the cart
	 */
	public Integer getDistinctProductNumber() {
		return this.cartItems.size();
	}

	/**
	 * Returns distinct category numbers in the cart
	 */
	public Integer getDistinctCategoryNumber() {
		Set<Category> categorySet = new HashSet<Category>();
		this.cartItems.forEach((product, quantity) -> {
			categorySet.add(product.getCategory());
		});
		return categorySet.size();
	}

	/**
	 * Applies given discounts to the to the cart. First checks if given number of
	 * discounts is more than allowable discount for the cart, then throws
	 * IllegalArgumentException else applies calculator logic of
	 * {@link CartDiscountListCalculator#calculate()}
	 */
	public void applyDiscounts(Discount... discounts) throws IllegalArgumentException {
		if ((discounts.length + appliedDiscountNumber) > this.maxDiscountNumber) {
			throw new IllegalArgumentException(
					"Discounts cannot be applied to cart since number of applied discounts is bigger than maximum number of allowed discount: "
							+ this.maxDiscountNumber);
		} else {
			CartDiscountListCalculator cal = new CartDiscountListCalculator(Arrays.asList(discounts), this);
			cal.calculate(); // calculate all discounts
			appliedDiscountNumber += discounts.length; // increment the applied
		}
	}

	public Double getTotalCartPriceWithoutDiscounts() {
		return this.totalCartPriceWithoutDiscounts;
	}

	/**
	 * Calculates the total price of the cart
	 */
	void calculateTotalCartPrice() {
		this.totalCartPriceWithoutDiscounts = 0.0; // maybe the function is called before.
		this.cartItems.forEach((product, quantity) -> {
			this.totalCartPriceWithoutDiscounts += product.getPrice() * quantity;
		});
	}

	/**
	 * Return number of products in the cart, whose category is same as given. if
	 * given category is not fount then returns 0
	 */
	public int getNumberOfCategoryItems(Category category) {
		int quantity = 0;
		for (Entry<Product, Integer> entry : this.cartItems.entrySet()) {
			if (category.equals(entry.getKey().getCategory())) {
				quantity += entry.getValue();
			}
		}
		return quantity;
	}

	/**
	 * Adds given discount amount to campaign discount amount.
	 */
	public void addCampaignDiscount(Double discountAmount) {
		this.campaignDiscountAmount += discountAmount;
	}

	/**
	 * Adds given discount amount to coupon discount amount.
	 */
	public void addCouponDiscount(Double discountAmount) {
		this.couponDiscountAmount += discountAmount;
	}

	/**
	 * Returns the total campaign discount mounts. Firstly it was 0, and whenever an
	 * campaign is applied then it is increased with campaign discount
	 * 
	 */
	public Double getCampaignDiscount() {
		return this.campaignDiscountAmount;
	}

	/**
	 * Returns the total coupon discount mounts. Firstly it was 0, and whenever an
	 * coupon is applied then it is increased with campaign discount
	 * 
	 */
	public Double getCouponDiscount() {
		return this.couponDiscountAmount;
	}

	/**
	 * Calculates the total price in the cart for given category.
	 */
	public Double getCategoryItemTotalPrice(Category category) {
		Double totalCost = 0.0;
		for (Entry<Product, Integer> entry : this.cartItems.entrySet()) {
			if (category.equals(entry.getKey().getCategory())) {
				totalCost += entry.getKey().getPrice() * entry.getValue();
			}
		}

		return totalCost;
	}

	/**
	 * Applies coupon to the cart
	 * 
	 * @see {@link ShoppingCart#applyDiscounts(Discount...)}
	 */
	public void applyCoupon(Coupon coupon) throws IllegalArgumentException {
		this.applyDiscounts(coupon);
	}

	public void setDeliveryCost(Double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public Double getDeliveryCost() {
		return this.deliveryCost;
	}

	/**
	 * This calculates the total discounts applied to the cart.
	 */
	public Double getTotalDiscounts() {
		return this.getCouponDiscount() + this.getCampaignDiscount();
	}

	/**
	 * This calculates the total cost. It is calculated by summing delivery cost and
	 * total cart cost and subtracting total discount mount form that.
	 */
	public Double getTotalCost() {
		return this.getTotalCartPriceWithoutDiscounts() + this.getDeliveryCost() - this.getTotalDiscounts();
	}

	/**
	 * Calculates the cart total price after subtracting total discounts
	 */
	public Double getTotalCartPriceWithDiscounts() {
		return totalCartPriceWithoutDiscounts - getTotalDiscounts();
	}

	public void print() {
		// orders products with respect to category title
		CartPrinter printer = CartUtilFactory.getInstance().getPrinter();
		CartOrderer orderer = CartUtilFactory.getInstance().getOrderer();
		printer.printOrderly(this, orderer);
	}

}

package com.rcelik.cartimplementation.services.cart;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rcelik.cartimplementation.services.category.Category;
import com.rcelik.cartimplementation.services.delivery.DeliveryCostCalculator;
import com.rcelik.cartimplementation.services.discounts.AmountCampaign;
import com.rcelik.cartimplementation.services.discounts.AmountCoupon;
import com.rcelik.cartimplementation.services.discounts.Campaign;
import com.rcelik.cartimplementation.services.discounts.Coupon;
import com.rcelik.cartimplementation.services.discounts.DiscountFactory;
import com.rcelik.cartimplementation.services.discounts.DiscountType;
import com.rcelik.cartimplementation.services.product.Product;

public class ShoppingCartTest {

	private int maxDiscountNumber;

	private Category fruitCategory;
	private Double appleProductPrice;
	private Product strawberyProduct;
	private Double strawberyProductPrice;
	private Product appleProduct;

	private Category electronicCategory;
	private Double laptopProductPrice;
	private Product laptopProduct;

	private Campaign rateFruitCampaign;
	private Campaign amountFruitCampaign;
	private Coupon rateCoupon;
	private Coupon amountCoupon;

	@Before
	public final void initialize() {
		maxDiscountNumber = 3;

		fruitCategory = new Category.Builder("Fruit").build();
		appleProductPrice = Double.valueOf(5);
		appleProduct = new Product.Builder(fruitCategory, "Apple").withPrice(appleProductPrice).build();
		strawberyProductPrice = Double.valueOf(10);
		strawberyProduct = new Product.Builder(this.fruitCategory, "Strawberry").withPrice(strawberyProductPrice)
				.build();

		electronicCategory = new Category.Builder("Electronic").build();
		laptopProductPrice = Double.valueOf(2500);
		laptopProduct = new Product.Builder(electronicCategory, "Laptop").withPrice(laptopProductPrice).build();

		rateFruitCampaign = DiscountFactory.getInstance().getDiscount(DiscountType.AMOUNT, fruitCategory, 1,
				Double.valueOf(2));
		amountFruitCampaign = DiscountFactory.getInstance().getDiscount(DiscountType.RATE, fruitCategory, 1,
				Double.valueOf(2));
		rateCoupon = DiscountFactory.getInstance().getDiscount(DiscountType.RATE, Double.valueOf(2), Double.valueOf(2));
		amountCoupon = DiscountFactory.getInstance().getDiscount(DiscountType.AMOUNT, Double.valueOf(2),
				Double.valueOf(2));
	}

	@Test
	public final void createChartTest() {
		Integer appleProductQuantity = 2;
		ShoppingCart cart = createShoppingCart(appleProduct, appleProductQuantity, maxDiscountNumber);

		List<Product> result = cart.getProducts();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(maxDiscountNumber, cart.getMaxDiscountNumber());

		Product comingProduct = result.get(0);
		Assert.assertEquals(appleProduct, comingProduct);
		Assert.assertEquals(appleProductQuantity, cart.getProductQuantity(comingProduct));

		// means we should have 3 strawberry products
		cart.addProduct(strawberyProduct);
		cart.addProduct(strawberyProduct, 2);

		cart.addProduct(laptopProduct);

		Integer distProductNumber = 3; // we should have 3
		Integer distCategoryNumber = 2; // we should have 2
		Assert.assertEquals(distProductNumber, cart.getDistinctProductNumber());
		Assert.assertEquals(distCategoryNumber, cart.getDistinctCategoryNumber());
	}

	private final ShoppingCart createShoppingCart(Product product, Integer quantity, int maxDiscountNumber) {
		return new ShoppingCart(product, quantity, maxDiscountNumber);
	}

	@Test
	public final void applyDiscountsTest() {
		ShoppingCart cart = createShoppingCart(appleProduct, 5, 4);
		cart.addProduct(strawberyProduct, 2);
		cart.applyDiscounts(rateFruitCampaign, amountFruitCampaign, rateCoupon, amountCoupon);

		// calculate total cart price without discounts
		Double totalCartPrice = Double.valueOf(45);
		Assert.assertEquals(totalCartPrice, cart.getTotalCartPriceWithoutDiscounts());

		// calculate total campaign discount
		Double totalCampaignDiscount = Double.valueOf(2.9); // 2 + 0.9
		Assert.assertEquals(totalCampaignDiscount, cart.getCampaignDiscount());

		// calculate total coupon discount
		Double totalCouponDiscount = Double.valueOf(2.9); // 2 + 0.9
		Assert.assertEquals(totalCouponDiscount, cart.getCouponDiscount());

		// calculate total coupon discount
		Double totalDiscount = Double.valueOf(5.8); // 2.9 + 2.9
		Assert.assertEquals(totalDiscount, cart.getTotalDiscounts());

		// calculate total coupon discount
		Double totalCartPriceAfterDiscount = Double.valueOf(39.2); // 45 - 5.8
		Assert.assertEquals(totalCartPriceAfterDiscount, cart.getTotalCartPriceWithDiscounts());
	}

	@Test
	public final void wholeCartOperationsTest() {
		ShoppingCart cart = createShoppingCart(appleProduct, 5, 5);
		cart.addProduct(strawberyProduct, 2);
		cart.addProduct(laptopProduct, 1);
		Campaign laptopCampaign = new AmountCampaign(electronicCategory, 1, Double.valueOf(100));
		cart.applyDiscounts(rateFruitCampaign, amountFruitCampaign, rateCoupon, laptopCampaign);

		cart.applyCoupon(amountCoupon);

		Double costPerDelivery = Double.valueOf(2);
		Double costPerProduct = Double.valueOf(3);
		Double fixedCost = Double.valueOf(2.99);

		DeliveryCostCalculator calculator = new DeliveryCostCalculator(costPerDelivery, costPerProduct, fixedCost);
		calculator.calculateFor(cart);

		// total amount before discounts
		Assert.assertEquals(Double.valueOf(2545), cart.getTotalCartPriceWithoutDiscounts());
		// calculate total coupon discounts
		Assert.assertEquals(Double.valueOf(52.9), cart.getCouponDiscount());
		// calculate total campaign discounts
		Assert.assertEquals(Double.valueOf(102.9), cart.getCampaignDiscount());
		// calculate total discounts
		Assert.assertEquals(Double.valueOf(155.8), cart.getTotalDiscounts());
		// calculate total amount after discounts
		Assert.assertEquals(Double.valueOf(2389.2), cart.getTotalCartPriceWithDiscounts());

		// calculate delivery cost
		Assert.assertEquals(Double.valueOf(15.99), cart.getDeliveryCost());

		// calculate total cost 2405.19
		Assert.assertEquals(Double.valueOf(2405.19), cart.getTotalCost(), 0.00001);

		cart.print();
	}

	@Test(expected = IllegalArgumentException.class)
	public final void applyDiscountToWrongCategoryTest() {
		ShoppingCart cart = createShoppingCart(laptopProduct, 5, 4);
		cart.applyDiscounts(rateFruitCampaign);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void applyCouponToCartWithLessTotalPriceTest() {
		ShoppingCart cart = createShoppingCart(appleProduct, 5, 4);
		Coupon tenPercentCouponForOverThousandCart = new AmountCoupon(Double.valueOf(1000), Double.valueOf(10));
		cart.applyDiscounts(tenPercentCouponForOverThousandCart);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void applyDiscountsFailTest() {
		ShoppingCart cart = createShoppingCart(appleProduct, 5, 3);
		cart.addProduct(strawberyProduct, 2);
		// this should throw exception
		cart.applyDiscounts(rateFruitCampaign, amountFruitCampaign, rateCoupon, amountCoupon);
	}

	@Test
	public final void applyCouponTest() {
		ShoppingCart cart = createShoppingCart(appleProduct, 5, 3);
		cart.applyCoupon(amountCoupon);
		Double totalCouponDiscount = Double.valueOf(2);
		Assert.assertEquals(totalCouponDiscount, cart.getCouponDiscount());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void applyMoreDiscountForCartTest() {
		ShoppingCart cart = createShoppingCart(appleProduct, 5, 0); // cart can take 0 discount
		cart.applyCoupon(amountCoupon); // should throw and exception
	}

}

package com.rcelik.cartimplementation.services.category;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rcelik.cartimplementation.services.category.Category;

/*
 * Category has title and may have parent category
 * */
public class CategoryTest {

	private String foodCategoryName;
	private String fruitCategoryName;

	private Category foodCategory;
	private Category fruitCategory;

	/*
	 * Suppose we have food and fruit categories. Food is parent category of fruit.
	 */
	@Before
	public final void initializeObjects() {
		this.foodCategoryName = "food";
		this.foodCategory = new Category.Builder(this.foodCategoryName).build();

		this.fruitCategoryName = "fruit";
		this.fruitCategory = new Category.Builder(fruitCategoryName).withParentCategory(this.foodCategory).build();
	}

	/*
	 * Check that footCategory name is equal to footCategoryName Check that
	 * footCategor`s parent category is null
	 * 
	 */
	@Test
	public final void parentCategoryCreationTest() {
		// check categoryName is correct
		Assert.assertEquals(foodCategoryName, foodCategory.getTitle());
		// check parent category is null
		Assert.assertNull(foodCategory.getParentCategory());

	}

	/*
	 * Check that fruitCategory name is equal to fruitCategoryName Check that
	 * fruitCategor`s parent category is foodCategory
	 * 
	 */
	@Test
	public final void childCategoryCreationTest() {
		// check categoryName is correct
		Assert.assertEquals(this.fruitCategoryName, this.fruitCategory.getTitle());
		// check parent category is null
		Assert.assertEquals(this.foodCategory, this.fruitCategory.getParentCategory());
	}

}

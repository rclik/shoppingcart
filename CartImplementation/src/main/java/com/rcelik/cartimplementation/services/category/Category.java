package com.rcelik.cartimplementation.services.category;

public class Category {

	private String title;
	// We can create a ParentCategory class too
	// ParentCategory does not have parent category object while child category has.
	// But it restricts that parent category if a parent category class is created
	// then it cannot have parent category
	// But with this manner, it can be applied more flexibly.
	private Category parentCategory;

	private Category(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	/**
	 * Builder class is a helper class to generate the category object
	 * <ul>
	 * <li>Uses {@link Builder#withParentCategory(Category)} method to add parent
	 * category</li>
	 * <li>Uses {@link Builder#build()}} method to create new instance of
	 * {@link Category} class</li>
	 * </ul>
	 */
	public static class Builder {
		private String title;
		private Category parentCategory;

		public Builder(String title) {
			this.title = title;
		}

		/**
		 * Adds parent category for the current builder to be used in creating category
		 */
		public Builder withParentCategory(Category parentCategory) {
			this.parentCategory = parentCategory;
			return this;
		}

		/**
		 * Creates new Category object
		 */
		public Category build() {
			Category category = new Category(this.title);
			category.parentCategory = this.parentCategory;
			return category;
		}
	}

	@Override
	public String toString() {
		return "Category: [ title: " + title + "]";
	}

}

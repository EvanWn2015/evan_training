package test.design_pattem.builders;

import org.junit.Test;

import idv.evan.design_pattern.builders.Meal;
import idv.evan.design_pattern.builders.MealBuilder;

public class BuilderPattern {

	@Test
	public void testMealBuilder() {
		MealBuilder mealBuilder = MealBuilder.newInstance();

		Meal vegMeal = mealBuilder.prepareVegMeal();
		System.out.println("Veg Meal");
		vegMeal.showItems();
		System.out.println("Total Cost: " + vegMeal.getCost());

		Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
		System.out.println("\n\nNon-Veg Meal");
		nonVegMeal.showItems();
		System.out.println("Total Cost: " + nonVegMeal.getCost());
	}
}

package idv.evan.design_pattern.builders;

public class MealBuilder {

	private static MealBuilder MEAL_BUILDER;

	private MealBuilder() {

	}

	public static MealBuilder getInstance() {
		if (MEAL_BUILDER == null) {
			MEAL_BUILDER = new MealBuilder();
		}
		return MEAL_BUILDER;
	}

	public static MealBuilder newInstance() {
		return new MealBuilder();
	}

	public Meal prepareVegMeal() {
		Meal meal = Meal.newInstance();
		meal.addItem(VegBurger.newInstance());
		meal.addItem(Coke.newInstance());
		return meal;
	}

	public Meal prepareNonVegMeal() {
		Meal meal = Meal.newInstance();
		meal.addItem(ChickenBurger.newInstance());
		meal.addItem(Pepsi.newInstance());
		return meal;
	}
}

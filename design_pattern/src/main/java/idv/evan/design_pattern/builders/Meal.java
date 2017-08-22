package idv.evan.design_pattern.builders;

import java.util.ArrayList;
import java.util.List;

import idv.evan.design_pattern.builders.intf.IItem;

public class Meal {

	private static Meal MEAL;
	private static List<IItem> items = new ArrayList<IItem>();

	private Meal() {

	}

	public static Meal getInstance() {
		if (MEAL == null) {
			MEAL = new Meal();
		}
		return MEAL;
	}

	public static Meal newInstance() {
		return new Meal();
	}

	public void addItem(IItem item) {
		items.add(item);
	}

	public float getCost() {
		float cost = 0.0f;

		for (IItem item : items) {
			cost += item.price();
		}
		return cost;
	}

	public void showItems() {

		for (IItem item : items) {
			System.out.print("Item : " + item.name());
			System.out.print(", Packing : " + item.packing().pack());
			System.out.println(", Price : " + item.price());
		}
	}
}

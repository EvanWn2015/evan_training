package idv.evan.design_pattern.builders;

import idv.evan.design_pattern.builders.abs.ABurger;

public class ChickenBurger extends ABurger {

	private ChickenBurger() {

	}

	public String name() {
		return "Chicken Burger";
	}

	@Override
	public float price() {
		return 50.5f;
	}

	public static ABurger newInstance() {
		return new ChickenBurger();
	}

}

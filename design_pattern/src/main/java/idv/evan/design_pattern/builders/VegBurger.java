package idv.evan.design_pattern.builders;

import idv.evan.design_pattern.builders.abs.ABurger;

public class VegBurger extends ABurger {

	private VegBurger() {

	}

	public String name() {
		return "Veg Burger";
	}

	@Override
	public float price() {
		return 25.0f;
	}

	public static ABurger newInstance() {
		return new VegBurger();
	}

}

package idv.evan.design_pattern.builders;

import idv.evan.design_pattern.builders.abs.AColdDrink;

public class Coke extends AColdDrink {

	private Coke() {

	}

	public String name() {
		return "Coke";
	}

	@Override
	public float price() {
		return 30.0f;
	}

	public static AColdDrink newInstance() {
		return new Coke();
	}
}

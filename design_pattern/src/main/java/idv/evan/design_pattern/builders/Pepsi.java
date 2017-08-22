package idv.evan.design_pattern.builders;

import idv.evan.design_pattern.builders.abs.AColdDrink;

public class Pepsi extends AColdDrink {

	private Pepsi() {

	}

	public String name() {
		return "Pepsi";
	}

	@Override
	public float price() {
		return 35.0f;
	}

	public static AColdDrink newInstance() {
		return new Pepsi();
	}

}

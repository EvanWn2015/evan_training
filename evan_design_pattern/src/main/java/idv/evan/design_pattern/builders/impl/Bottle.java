package idv.evan.design_pattern.builders.impl;

import idv.evan.design_pattern.builders.intf.IPacking;

public class Bottle implements IPacking {

	private Bottle() {

	}

	public String pack() {
		return "Bottle";
	}

	public static Bottle newInstance() {
		return new Bottle();
	}

}

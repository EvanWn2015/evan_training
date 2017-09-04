package idv.evan.design_pattern.factory.color.impl;

import idv.evan.design_pattern.factory.color.IColor;

public class Blue implements IColor {

	public void fill() {
		System.out.println("Inside Blue::fill() method.");
	}

	public static Blue newInstance() {
		return new Blue();
	}

}

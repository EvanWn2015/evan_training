package idv.evan.design_pattern.factory.color.impl;

import idv.evan.design_pattern.factory.color.IColor;

public class Green implements IColor {

	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}

	public static Green newInstance() {
		return new Green();
	}

}

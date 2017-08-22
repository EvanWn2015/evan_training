package idv.evan.design_pattern.factory.color.impl;

import idv.evan.design_pattern.factory.color.IColor;

public class Red implements IColor {

	public void fill() {
		System.out.println("Inside Red::fill() method.");
	}

	public static Red newInstance() {
		return new Red();
	}
}

package idv.evan.design_pattern.factory.shape.impl;

import idv.evan.design_pattern.factory.shape.IShape;

public class Triangle implements IShape {

	public void draw() {
		System.out.println("Inside Triangle::draw() method.");
	}

	public static Triangle newInstance() {
		return new Triangle();
	}
}

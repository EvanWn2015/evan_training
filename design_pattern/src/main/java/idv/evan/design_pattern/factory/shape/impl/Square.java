package idv.evan.design_pattern.factory.shape.impl;

import idv.evan.design_pattern.factory.shape.IShape;

public class Square implements IShape {

	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}

	public static Square newInstance() {
		return new Square();
	}

}

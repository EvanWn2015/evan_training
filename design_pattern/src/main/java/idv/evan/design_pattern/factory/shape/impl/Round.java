package idv.evan.design_pattern.factory.shape.impl;

import idv.evan.design_pattern.factory.shape.IShape;

public class Round implements IShape {

	public void draw() {
		System.out.println("Inside Round::draw() method.");
	}

	public static Round newInstance() {
		return new Round();
	}

}

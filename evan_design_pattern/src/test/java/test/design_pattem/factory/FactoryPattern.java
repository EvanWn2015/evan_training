package test.design_pattem.factory;

import org.junit.Test;

import idv.evan.design_pattern.factory.ColorFactory;
import idv.evan.design_pattern.factory.ShapeFactory;
import idv.evan.design_pattern.factory.SuperSketchpadFactory;
import idv.evan.design_pattern.factory.type.ChoiceType;
import idv.evan.design_pattern.factory.type.ColorType;
import idv.evan.design_pattern.factory.type.ShapeType;

public class FactoryPattern {

	@Test
	public void testShapeFactory() {

		ShapeFactory.newInstance().getShape(ShapeType.TRIANGLE).draw();
		ShapeFactory.newInstance().getShape(ShapeType.SQUARE).draw();
		ShapeFactory.newInstance().getShape(ShapeType.ROUND).draw();
	}

	@Test
	public void testColorFactroy() {

		ColorFactory.newInstance().getColor(ColorType.RED).fill();
		ColorFactory.newInstance().getColor(ColorType.BLUE).fill();
		ColorFactory.newInstance().getColor(ColorType.GREEN).fill();
	}

	@Test
	public void testSuperFactory() {

		SuperSketchpadFactory.getFactory(ChoiceType.SHAPE).getShape(ShapeType.TRIANGLE).draw();
		SuperSketchpadFactory.getFactory(ChoiceType.SHAPE).getShape(ShapeType.SQUARE).draw();
		SuperSketchpadFactory.getFactory(ChoiceType.SHAPE).getShape(ShapeType.ROUND).draw();

		System.out.println("=================================");

		SuperSketchpadFactory.getFactory(ChoiceType.COLOR).getColor(ColorType.RED).fill();
		SuperSketchpadFactory.getFactory(ChoiceType.COLOR).getColor(ColorType.BLUE).fill();
		SuperSketchpadFactory.getFactory(ChoiceType.COLOR).getColor(ColorType.GREEN).fill();

	}

	@Test
	public void testSuperFactoryByName() {

		SuperSketchpadFactory.getFactory(ChoiceType.SHAPE).getShapeByName(ShapeType.TRIANGLE.toString()).draw();
		SuperSketchpadFactory.getFactory(ChoiceType.SHAPE).getShapeByName(ShapeType.SQUARE.toString()).draw();
		SuperSketchpadFactory.getFactory(ChoiceType.SHAPE).getShapeByName(ShapeType.ROUND.toString()).draw();

		System.out.println("=================================");

		SuperSketchpadFactory.getFactory(ChoiceType.COLOR).getColorByName(ColorType.RED.toString()).fill();
		SuperSketchpadFactory.getFactory(ChoiceType.COLOR).getColorByName(ColorType.BLUE.toString()).fill();
		SuperSketchpadFactory.getFactory(ChoiceType.COLOR).getColorByName(ColorType.GREEN.toString()).fill();

	}
}

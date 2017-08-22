package idv.evan.design_pattern.factory;

import idv.evan.design_pattern.factory.color.IColor;
import idv.evan.design_pattern.factory.shape.IShape;
import idv.evan.design_pattern.factory.shape.impl.Round;
import idv.evan.design_pattern.factory.shape.impl.Square;
import idv.evan.design_pattern.factory.shape.impl.Triangle;
import idv.evan.design_pattern.factory.type.ColorType;
import idv.evan.design_pattern.factory.type.ShapeType;

public class ShapeFactory extends ASketchpadFactory {

	@Override
	public IShape getShape(ShapeType shapeType) {
		if (shapeType != null) {
			if (shapeType.equals(ShapeType.TRIANGLE)) {
				return Triangle.newInstance();
			} else if (shapeType.equals(ShapeType.SQUARE)) {
				return Square.newInstance();
			} else if (shapeType.equals(ShapeType.ROUND)) {
				return Round.newInstance();
			}
		}
		return null;
	}

	@Override
	public IShape getShapeByName(String shapeName) {
		if (shapeName != null) {
			if (shapeName.equals(ShapeType.TRIANGLE.toString())) {
				return Triangle.newInstance();
			} else if (shapeName.equals(ShapeType.SQUARE.toString())) {
				return Square.newInstance();
			} else if (shapeName.equals(ShapeType.ROUND.toString())) {
				return Round.newInstance();
			}
		}
		return null;
	}

	@Override
	public IColor getColor(ColorType colorType) {
		return null;
	}

	@Override
	public IColor getColorByName(String colorName) {
		return null;
	}

	public static ASketchpadFactory newInstance() {
		return new ShapeFactory();
	}

}

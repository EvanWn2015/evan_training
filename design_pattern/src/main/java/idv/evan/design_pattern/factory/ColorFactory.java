package idv.evan.design_pattern.factory;

import idv.evan.design_pattern.factory.color.IColor;
import idv.evan.design_pattern.factory.color.impl.Blue;
import idv.evan.design_pattern.factory.color.impl.Green;
import idv.evan.design_pattern.factory.color.impl.Red;
import idv.evan.design_pattern.factory.shape.IShape;
import idv.evan.design_pattern.factory.type.ColorType;
import idv.evan.design_pattern.factory.type.ShapeType;

public class ColorFactory extends ASketchpadFactory {

	@Override
	public IShape getShape(ShapeType shapeType) {
		return null;
	}

	@Override
	public IColor getColor(ColorType colorType) {
		if (colorType != null) {
			if (colorType.equals(ColorType.RED)) {
				return Red.newInstance();
			} else if (colorType.equals(ColorType.BLUE)) {
				return Blue.newInstance();
			} else if (colorType.equals(ColorType.GREEN)) {
				return Green.newInstance();
			}
		}

		return null;
	}

	@Override
	public IColor getColorByName(String colorName) {
		if (colorName != null) {
			if (colorName.equals(ColorType.RED.toString())) {
				return Red.newInstance();
			} else if (colorName.equals(ColorType.BLUE.toString())) {
				return Blue.newInstance();
			} else if (colorName.equals(ColorType.GREEN.toString())) {
				return Green.newInstance();
			}
		}

		return null;
	}

	@Override
	public IShape getShapeByName(String shapeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ASketchpadFactory newInstance() {
		return new ColorFactory();
	}

}

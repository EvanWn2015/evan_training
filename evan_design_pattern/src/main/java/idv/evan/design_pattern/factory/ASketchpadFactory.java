package idv.evan.design_pattern.factory;

import idv.evan.design_pattern.factory.color.IColor;
import idv.evan.design_pattern.factory.shape.IShape;
import idv.evan.design_pattern.factory.type.ColorType;
import idv.evan.design_pattern.factory.type.ShapeType;

public abstract class ASketchpadFactory {
	
	public abstract IColor getColor(ColorType colorType);

	public abstract IShape getShape(ShapeType shapeType);

	public abstract IColor getColorByName(String colorName);

	public abstract IShape getShapeByName(String shapeName);
}

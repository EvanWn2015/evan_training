package idv.evan.design_pattern.factory;

import idv.evan.design_pattern.factory.type.ChoiceType;

public class SuperSketchpadFactory {

	public static ASketchpadFactory getFactory(ChoiceType choice) {
		if (choice != null) {
			if (choice.equals(ChoiceType.SHAPE)) {
				return ShapeFactory.newInstance();
			} else if (choice.equals(ChoiceType.COLOR)) {
				return ColorFactory.newInstance();
			}
		}
		return null;
	}

	public static SuperSketchpadFactory newInstance() {
		return new SuperSketchpadFactory();
	}
}

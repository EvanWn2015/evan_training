package idv.evan.design_pattern.factory.type;

public enum ShapeType {

	TRIANGLE("Triangle"), SQUARE("Square"), ROUND("Round");

	private final String shape;

	private ShapeType(String s) {
		shape = s;
	}

	public boolean equalsShape(String otherName) {
		return shape.equals(otherName);
	}

	public String toString() {
		return this.shape;
	}
}

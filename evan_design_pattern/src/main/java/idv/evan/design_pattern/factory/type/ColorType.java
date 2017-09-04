package idv.evan.design_pattern.factory.type;

public enum ColorType {

	RED("Red"), BLUE("Blue"), GREEN("Green");

	private final String color;

	private ColorType(String s) {
		color = s;
	}

	public boolean equalsColor(String otherName) {
		return color.equals(otherName);
	}

	public String toString() {
		return this.color;
	}
}

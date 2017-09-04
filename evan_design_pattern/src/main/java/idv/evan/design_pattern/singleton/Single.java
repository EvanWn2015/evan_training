package idv.evan.design_pattern.singleton;

public class Single {

	private static Single SINGLE;

	private Single() {
	}

	public static Single getInstance() {
		if (SINGLE == null) {
			SINGLE = new Single();
		}
		return SINGLE;
	}

	public void showMessage() {
		System.out.println("Hello Single!");
	}
}

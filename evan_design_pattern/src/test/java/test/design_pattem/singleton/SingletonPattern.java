package test.design_pattem.singleton;

import org.junit.Test;

import idv.evan.design_pattern.singleton.Single;

public class SingletonPattern {

	@Test
	public void testSingle() {
		Single.getInstance().showMessage();
	}
}

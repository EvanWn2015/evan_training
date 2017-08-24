package test.tools.zip;

import org.junit.Test;

import idv.evan.tools.cn.ChineseHelper;
import idv.evan.tools.cn.ChineseHelper.ChineseType;

public class EVanTest {

	@Test
	public void rte() {
		System.out.println(ChineseHelper.convert("國",ChineseType.T));
		System.out.println(ChineseHelper.convert("国",ChineseType.T));
		System.out.println(ChineseHelper.convert("sadsa",ChineseType.T));
		System.out.println(ChineseHelper.convert("国sDFSDFSF",ChineseType.S));
	}
}

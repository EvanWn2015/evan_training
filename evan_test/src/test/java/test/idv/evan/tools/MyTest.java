package test.idv.evan.tools;

import org.junit.Test;

import com.google.gson.Gson;

import idv.evan.design_pattern.enumFactory.ColorFactory;
import idv.evan.design_pattern.enumFactory.Red;

public class MyTest {

	
	@Test
	public void myTest () throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Red red = ColorFactory.RED.newInstance();
		System.out.println(red);
		
		new Gson().fromJson("", Red.class);
	
	}
}

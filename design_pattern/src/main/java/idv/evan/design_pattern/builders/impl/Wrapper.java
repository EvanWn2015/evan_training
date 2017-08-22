package idv.evan.design_pattern.builders.impl;

import idv.evan.design_pattern.builders.intf.IPacking;

public class Wrapper implements IPacking {

	
	private Wrapper (){
		
	}
	
	public String pack() {
		return "Wrapper";
	}

	public static Wrapper newInstance() {
		return new Wrapper();
	}
}

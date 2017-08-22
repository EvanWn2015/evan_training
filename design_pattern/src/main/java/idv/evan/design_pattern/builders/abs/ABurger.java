package idv.evan.design_pattern.builders.abs;

import idv.evan.design_pattern.builders.impl.Wrapper;
import idv.evan.design_pattern.builders.intf.IItem;
import idv.evan.design_pattern.builders.intf.IPacking;

public abstract class ABurger implements IItem {

	public IPacking packing() {
		return Wrapper.newInstance();
	}

	public abstract float price();

}

package idv.evan.design_pattern.builders.abs;

import idv.evan.design_pattern.builders.impl.Bottle;
import idv.evan.design_pattern.builders.intf.IItem;
import idv.evan.design_pattern.builders.intf.IPacking;

public abstract class AColdDrink implements IItem {

	public IPacking packing() {
		return Bottle.newInstance();
	}

	public abstract float price();

}

package idv.evan.design_pattern.enumFactory;


import java.lang.reflect.Type;
import java.util.List;

import com.beust.jcommander.internal.Lists;

public enum ColorFactory {

	RED(0, Red.class), 
	GREEN(1, Green.class);

	private static List<?> INSTANCES = Lists.newArrayList();

	private final int index;
	private final Type type;

	private ColorFactory(int i,Type t) {
		index = i;
		type = t;
	}

	public boolean equals(Type t) {
		return this.type.equals(t);
	}

	public String toString(Type t) {
		System.err.println(t.getTypeName());
		return t.getTypeName();
	}

	
	@SuppressWarnings("unchecked")
	public <T> T newInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (T) Class.forName(this.type.getTypeName()).newInstance();
	}

	@SuppressWarnings("unchecked")
	public <T> T getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (INSTANCES.isEmpty() || INSTANCES.get(this.index) == null) {
			INSTANCES.add(this.index, newInstance());
		}
		return (T) INSTANCES.get(this.index);
	}
}

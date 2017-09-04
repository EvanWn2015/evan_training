package idv.evan.data_structure.tree;

public abstract class Tree<T> {
	protected T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public abstract Tree<T> parent();

	public abstract TreeList<T> children();

	public abstract int size();

	public abstract int depth();

	public abstract int degree();

	public abstract int level();

	public Tree(T value) {
		this.value = value;
	}

	public abstract void add(T value);

	public abstract void add(Tree<T> tree);

	public abstract void remove();

	public abstract Tree<T> copy();
}

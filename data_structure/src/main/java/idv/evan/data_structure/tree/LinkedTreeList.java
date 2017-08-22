package idv.evan.data_structure.tree;


import java.util.Iterator;
import java.util.List;

public class LinkedTreeList<T> extends TreeList<T> {
	protected List<LinkedTree<T>> list;

	public LinkedTreeList(List<LinkedTree<T>> list) {
		this.list = list;
	}

	@Override
	public int size() {
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public Iterator<Tree<T>> iterator() {
		return (Iterator<Tree<T>>) ((List<Tree<T>>) (List<?>) list).iterator();
	}
}

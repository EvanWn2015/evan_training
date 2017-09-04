package idv.evan.data_structure.tree;

import java.util.LinkedList;
import java.util.List;

public class LinkedTree<T> extends Tree<T> {
	protected List<LinkedTree<T>> childrenList;

	protected LinkedTree<T> parent;

	@Override
	public Tree<T> parent() {
		return parent;
	}

	protected TreeList<T> children;

	@Override
	public TreeList<T> children() {
		return children;
	}

	protected int size;

	@Override
	public int size() {
		return size;
	}

	protected int depth;

	@Override
	public int depth() {
		return depth;
	}

	@Override
	public int degree() {
		return childrenList.size();
	}

	protected int level;

	@Override
	public int level() {
		return level;
	}

	public LinkedTree(T value) {
		super(value);
		childrenList = new LinkedList<LinkedTree<T>>();
		children = new LinkedTreeList<T>(childrenList);
		depth = 1;
		level = 1;
		size = 1;
	}

	@Override
	public void add(T value) {
		add(new LinkedTree<T>(value));
	}

	@Override
	public void add(Tree<T> tree) {
		LinkedTree<T> gtree = (LinkedTree<T>) tree;
		if (gtree.parent != null)
			gtree.remove();
		gtree.parent = this;
		if (gtree.depth + 1 > depth) {
			depth = gtree.depth + 1;
			bubbleDepth();
		}
		gtree.level = level + 1;
		gtree.updateLevel();
		childrenList.add(gtree);
		size += gtree.size;
		bubbleCount(gtree.size);
	}

	@Override
	public void remove() {
		if (parent == null)
			return;
		parent.childrenList.remove(this);
		if (depth + 1 == parent.depth)
			parent.updateDepth();
		parent.size -= size;
		parent.bubbleCount(-size);
		parent = null;
	}

	@Override
	public Tree<T> copy() {
		return copy(1);
	}

	protected LinkedTree<T> copy(int level) {
		LinkedTree<T> cloneTree = new LinkedTree<T>(value);
		cloneTree.depth = depth;
		cloneTree.level = level;
		cloneTree.size = size;
		for (LinkedTree<T> child : childrenList) {
			LinkedTree<T> cloneChild = child.copy(level + 1);
			cloneChild.parent = cloneTree;
			cloneTree.childrenList.add(cloneChild);
		}
		return cloneTree;
	}

	protected void bubbleDepth() {
		if (parent == null)
			return;

		if (depth + 1 > parent.depth) {
			parent.depth = depth + 1;
			parent.bubbleDepth();
		}
	}

	protected void updateDepth() {
		int tmpDepth = depth;
		depth = 1;
		for (LinkedTree<T> child : childrenList)
			if (child.depth + 1 > depth)
				depth = child.depth + 1;
		if (tmpDepth == depth || parent == null)
			return;
		if (tmpDepth + 1 == parent.depth)
			parent.updateDepth();
	}

	protected void bubbleCount(int diff) {
		if (parent == null)
			return;

		parent.size += diff;
		parent.bubbleCount(diff);
	}

	protected void updateLevel() {
		int childLevel = level + 1;
		for (LinkedTree<T> child : childrenList) {
			child.level = childLevel;
			child.updateLevel();
		}
	}
}

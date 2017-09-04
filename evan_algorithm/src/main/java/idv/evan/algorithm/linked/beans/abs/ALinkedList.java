package idv.evan.algorithm.linked.beans.abs;

import idv.evan.algorithm.linked.beans.Node;
import idv.evan.algorithm.linked.beans.intf.ILinkedList;

public abstract class ALinkedList<T> implements ILinkedList<T> {
	protected int count;
	protected Node<T> first;
	protected Node<T> last;

	@Override
	public Node<T> getFirst() {
		return first;
	}
	@Override
	public Node<T> getLast() {
		return last;
	}
	@Override
	public int size() {
		return count;
	}
	@Override
	public abstract void addFirst(T value);
	@Override
	public abstract void addLast(T value);
	@Override
	public abstract void addBefore(Node<T> node, T value);
	@Override
	public abstract void addAfter(Node<T> node, T value);
	@Override
	public abstract void removeFirst();
	@Override
	public abstract void removeLast();
	@Override
	public abstract void remove(Node<T> node);
}

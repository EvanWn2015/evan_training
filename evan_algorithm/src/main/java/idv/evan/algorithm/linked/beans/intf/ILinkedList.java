package idv.evan.algorithm.linked.beans.intf;

import idv.evan.algorithm.linked.beans.Node;

public interface ILinkedList<T> {

	public Node<T> getFirst();
	public Node<T> getLast();
	public int size();
	public void addFirst(T value);
	public void addLast(T value);
	public void addBefore(Node<T> node, T value);
	public void addAfter(Node<T> node, T value);
	public void removeFirst();
	public void removeLast();
	public void remove(Node<T> node);

}
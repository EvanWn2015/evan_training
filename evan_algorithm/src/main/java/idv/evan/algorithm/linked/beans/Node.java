package idv.evan.algorithm.linked.beans;

import idv.evan.algorithm.linked.beans.abs.ANode;

public class Node<T> extends ANode<T>{

	private Node<T> previous;
	private Node<T> next;
	private T value;
	
	public Node(T value) {
		this.value = value;
	}
	
	@Override
	public Node<T> getPrevious() {
		return previous;
	}
	
	@Override
	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}

	@Override
	public Node<T> getNext() {
		return next;
	}

	@Override
	public void setNext(Node<T> next) {
		this.next = next;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;
	}

}

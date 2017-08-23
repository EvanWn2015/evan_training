package idv.evan.algorithm.linked;

import idv.evan.algorithm.linked.beans.Node;
import idv.evan.algorithm.linked.beans.abs.ALinkedList;

public class DoublyLinkedList<T> extends ALinkedList<T> {

	@Override
	public void addFirst(T value) {
		Node<T> node = new Node<T>(value);
		if (count == 0)
			last = node;
		else {
			node.setNext(first);
			first.setPrevious(node);
		}
		first = node;
		++count;
	}

	@Override
	public void addLast(T value) {
		Node<T> node = new Node<T>(value);
		if (count == 0)
			first = node;
		else {
			last.setNext(node);
			node.setPrevious(last);
		}
		last = node;
		++count;
	}

	@Override
	public void addBefore(Node<T> node, T value) {
		Node<T> newNode = new Node<T>(value);
		newNode.setPrevious(node.getPrevious());
		node.getPrevious().setNext(newNode);
		node.setPrevious(newNode);
		newNode.setNext(node);
		if (node == first) {
			first = newNode;
		}
		++count;
	}

	@Override
	public void addAfter(Node<T> node, T value) {
		Node<T> newNode = new Node<T>(value);
		newNode.setNext(node.getNext());
		node.getNext().setPrevious(newNode);
		node.setNext(newNode);
		newNode.setPrevious(node);
		if (node == last) {
			last = newNode;
		}
		++count;
	}

	@Override
	public void removeFirst() {
		if (count == 0)
			throw new ArrayIndexOutOfBoundsException();
		else if (count == 1) {
			first = null;
			last = null;
		} else {
			Node<T> node = first.getNext();
			first.setNext(null);
			node.setPrevious(null);
			first = node;
		}
		--count;
	}

	@Override
	public void removeLast() {
		if (count == 0)
			throw new ArrayIndexOutOfBoundsException();
		else if (count == 1) {
			first = null;
			last = null;
		} else {
			Node<T> node = last.getPrevious();
			last.setPrevious(null);
			node.setNext(null);
			last = node;
		}
		--count;
	}

	@Override
	public void remove(Node<T> node) {
		if (node == first)
			removeFirst();
		else if (node == last)
			removeLast();
		else {
			node.getPrevious().setNext(node.getNext());
			node.getNext().setPrevious(node.getPrevious());
			node.setNext(null);
			node.setPrevious(null);
			--count;
		}
	}

}
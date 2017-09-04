package idv.evan.algorithm.linked.beans.intf;

import idv.evan.algorithm.linked.beans.Node;

public interface INode<T> {

	public Node<T> getPrevious();
	public void setPrevious(Node<T> previous);
	public Node<T> getNext();
	public void setNext(Node<T> next);
	public T getValue();
	public void setValue(T value);

}
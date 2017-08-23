package idv.evan.algorithm.linked.beans.abs;

import idv.evan.algorithm.linked.beans.Node;
import idv.evan.algorithm.linked.beans.intf.INode;

public abstract class ANode<T> implements INode<T> {

	@Override
	public abstract Node<T> getPrevious();
	@Override
	public abstract void setPrevious(Node<T> previous) ;
	@Override
	public abstract Node<T> getNext() ;
	@Override
	public abstract void setNext(Node<T> next) ;
	@Override
	public abstract T getValue() ;
	@Override
	public abstract void setValue(T value);

}

package ci284.lab2.lists;

public class ListItemC<T extends Comparable<T>> extends LinkedListC<T> {

	T head;
	LinkedListC<T> tail;
	
	public ListItemC(T head, LinkedListC<T> tail) {
		this.head = head;
		this.tail = tail;
	}
	
	@Override
	public T head() {
		return head;
	}

	@Override
	public LinkedListC<T> tail() {
		return tail;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ListItemC<?>)) return false;
		ListItemC<T> that = (ListItemC<T>) o;
		return head.equals(that.head) && tail.equals(that.tail);
	}
	
	@Override
	public String toString() {
		return head.toString() + " :: " + tail.toString();
	}
	/*
	 * The methods you need to implement start here.
	 */
	@Override
	public ListItemC<T> insert(T e) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	@Override
	public int length() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	@Override
	public boolean member(T e) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	@Override
	public int indexOf(T e) {
		return indexOfInner(e, 0);
	}
	@Override
	protected int indexOfInner(T e, int count) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	@Override
	public LinkedListC<T> delete(T e) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}

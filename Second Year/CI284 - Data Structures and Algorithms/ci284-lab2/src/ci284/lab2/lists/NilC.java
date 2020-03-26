package ci284.lab2.lists;

public class NilC<T extends Comparable<T>> extends LinkedListC<T> {

	@Override
	public T head() {
		throw new RuntimeException("Called head on an empty list.");
	}

	@Override
	public LinkedListC<T> tail() {
		throw new RuntimeException("Called tail on an empty list.");
	}
	@Override
	public boolean equals(Object o) {
		return (o instanceof NilC<?>);
	}
	@Override
	public String toString() {
		return "Nil";
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
		throw new UnsupportedOperationException("Not implemented yet");
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

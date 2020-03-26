package ci284.lab2.lists;

public class ListItem<T> extends LinkedList<T> {
	T head;
	LinkedList<T> tail;
	
	public ListItem(T head, LinkedList<T> tail) {
		this.head = head;
		this.tail = tail;
	}
	
	@Override
	public T head() {
		return head;
	}

	@Override
	public LinkedList<T> tail() {
		return tail;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ListItem<?>)) return false;
		ListItem<T> that = (ListItem<T>) o;
		return head.equals(that.head) && tail.equals(that.tail);
	}
	
	@Override
	public String toString() {
		return head.toString() + " :: " + tail.toString();
	}
	/*
	 * The methods you need to implement start here
	 */
	@Override
	public boolean isEmpty() {
		return false;
		
	}
	@Override
	public int length() {
		return tail().length() + 1;
		
	}
	@Override
	public boolean member(T e) {
		if (head().equals(e))
		{
			return true;
		}
		else
		{
			return tail.member(e);
		}
	}
	
	@Override
	public int indexOf(T e) {
		int index = indexOfInner(e, 0);
		return index;
		
	}
	@Override
	protected int indexOfInner(T e, int count) {
		if (head().equals(e))
		{
			return count;
		}
		count++;
		return tail.indexOfInner(e, count);
	}
	
	@Override
	public LinkedList<T> delete(T e) {
		if (head.equals(e))
			return tail;
		else
			return new ListItem(head(), tail.delete(e));
	}
}
package ci284.ass1.pqueue;

public class PQueue<T> {

	private PQueueItem<T> head;

	public static enum ORDER {
		ASC, DESC;
	}

	public static ORDER DEFAULT_ORDER = ORDER.DESC;
	private ORDER order;

	/**
	 * The default constructor for a PQueue, with the default order for priorities
	 */
	public PQueue() {
		order = DEFAULT_ORDER;
	}

	/**
	 * The constructor to make a new PQueue with a given order
	 * 
	 * @param order
	 */
	public PQueue(ORDER userOrder) {
		order = userOrder;
	}

	/**
	 * Remove and return data from the item at the front of the queue
	 * 
	 * @return
	 */
	public T pop() {
		if (head != null) {
			T temp = head.getData();
			head = head.getNext();
			return temp;
		}
		else
			return null;
	}

	/**
	 * Return the data from the item at the front of the queue, without removing the
	 * item itself
	 * 
	 * @return
	 */
	public T peek() {
		return (head == null) ? null : head.getData();
	}

	/**
	 * Remove and return the item at the front of the queue
	 * 
	 * @return
	 */
	public PQueueItem<T> popItem() {
		if (head != null) {
			PQueueItem<T> temp = head;
			head = head.getNext();
			return temp;
		}
		else
			return null;
	}

	/**
	 * Return the item at the front of the queue without removing it
	 * 
	 * @return
	 */
	public PQueueItem<T> peekItem() {
		return (head == null) ? null : head;
	}

	/**
	 * Insert a new item into the queue, which should be put in the right place
	 * according to its priority. That is, is order == ASC, then the new item should
	 * appear in the queue before all items with a HIGHER priority. If order ==
	 * DESC, then the new item should appear in the queue before all items with a
	 * LOWER priority.
	 * 
	 * @param data
	 * @param priority
	 */
	public void insert(T data, int priority) {
		if (order == ORDER.ASC) {
			priority = -priority;
		}
		PQueueItem<T> newItem = new PQueueItem<T>(data, priority);
		PQueueItem<T> temp;
		if (head != null) {
			if (head.getPriority() < priority) {
				temp = head;
				head = newItem;
				head.setNext(temp);
			}
			else {
				temp = head;
				while (temp.getNext() != null) {
					if (temp.getNext().getPriority() < priority) {
						newItem.setNext(temp.getNext());
						temp.setNext(newItem);
						return;
					}
					else {
						temp = temp.getNext();
					}
				}
				temp.setNext(newItem);
			}
		}
		else {
			head = newItem;
			return;
		}
	}

	/**
	 * Return the length of the queue
	 * 
	 * @return
	 */
	public int length() {
		int length = 0;
		if (head != null) {
			length++;
			PQueueItem<T> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
				length++;
			}
		}
		return length;
	}

	public String toString() {
		int i = length();
		PQueueItem<T> current = head;
		StringBuffer sb = new StringBuffer();
		while (i > 0) {
			sb.append(current.toString());
			if (i > 1)
				sb.append(": ");
			current = current.getNext();
			i--;
		}
		return sb.toString();
	}

}

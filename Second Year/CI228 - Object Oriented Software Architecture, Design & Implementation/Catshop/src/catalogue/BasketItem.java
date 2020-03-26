package catalogue;

public class BasketItem {
	
	private int quantity;		// the three values held
	private Product data;
	private BasketItem next;
	
	/**
	 * creates a new BasketItem
	 * @param data
	 * @param quantity
	 */
	public BasketItem(Product data, int quantity) {
		this.data = data;
		this.quantity = quantity;
	}
	
	// VV these allow for accessing the item data VV
	public int getquantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product getData() {
		return data;
	}
	
	public void setData(Product data) {
		this.data = data;
	}
	
	public BasketItem getNext() {
		return next;
	}
	
	public void setNext(BasketItem next) {
		this.next = next;
	}
	
	/**
	 * sets the current item's value to a new item's
	 * @param item
	 */
	public void set(BasketItem item) {
		this.quantity = item.quantity;
		this.data = item.data;
		this.next = item.next;
	}
	
	public String toString() {
		return String.format("[%s,%d]", data.toString(), quantity);
	}

}


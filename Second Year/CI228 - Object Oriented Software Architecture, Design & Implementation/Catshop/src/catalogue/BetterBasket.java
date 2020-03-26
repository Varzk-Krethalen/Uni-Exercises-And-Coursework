package catalogue;

import java.io.Serializable;
import java.util.Currency;
import java.util.Formatter;
import java.util.Locale;


/**
 * A collection of products from the CatShop used to record the products that
 * are to be purchased, in order of product number, with multiple items possible
 * per entry.
 * 
 * @author Alex Shafee 16849558
 * @version 0.1
 *
 */
public class BetterBasket extends Basket implements Serializable {
	private static final long serialVersionUID = 1L;
	private BasketItem head;

	/**
	 * Constructor for a basket which is used to represent a customer order/ wish
	 * list
	 */
	public BetterBasket() {
		super();
	}
	
	
	/**
	 * adds a given product to the basket if not already in there, or increases
	 * quantity of existing product if it does
	 */
	public boolean add(Product pr) {
		if (head != null) {											//if basket/queue exists,
			BasketItem temp = head;
			boolean exists = true;
			while (temp != null && exists) {						//iterate through, check if pr in basket
				if (temp.getData().getProductNum().equals(pr.getProductNum())) {
					temp.setQuantity(temp.getquantity() + pr.getQuantity());
					return true;									//if so, increase its quantity
				}
				else {
					if (temp.getNext() != null) {
						temp = temp.getNext();
					}
					else {
						exists = false;
					}
				}
			}
		}
		insert(pr, Integer.parseInt(pr.getProductNum()));			//if not in basket, insert as new item
		return true;
	}
	
	
	/**
	 * Takes a product and its product number, which is used as priority. If queue !exist, product
	 * is new head of queue. Otherwise, adds product between the two items with closest product
	 * numbers, keeping them in order.
	 * @param data
	 * @param priority
	 */
	private void insert(Product data, int priority) {
		BasketItem newItem = new BasketItem(data, data.getQuantity());		//item to be added
		BasketItem temp;													//item for iteration
		if (head != null) {													//if queue exists,
			if (Integer.parseInt(head.getData().getProductNum()) > priority) {
				temp = head;												//if higher priority than head,
				head = newItem;												//item becomes new head, head
				head.setNext(temp);											//becomes tail
			} else {
				temp = head;
				while (temp.getNext() != null) {							//iterate through list
					if (Integer.parseInt(temp.getNext().getData().getProductNum()) > priority) {
						newItem.setNext(temp.getNext());					//when next item has lower priority,
						temp.setNext(newItem);								//insert new item before it
						return;
					} else {
						temp = temp.getNext();
					}
				}
				temp.setNext(newItem);
			}
		} else {
			head = newItem;													//if no queue, item is new head
			return;
		}
	}
	
	
	/**
	 * returns number of items in the queue
	 */
	public int size() {
		int size = 0;
		if (head != null) {											//if queue exists,
			size++;													//iterate through, count
			BasketItem temp = head;									//all items
			while (temp.getNext() != null) {
				temp = temp.getNext();
				size++;
			}
		}
		return size;												//return final count
	}
	
	
	/**
	 * checks quantity of a Product in the basket
	 */
	public int checkAmount(String pn) {
		if (!head.getData().getProductNum().equals(pn)) {			//checks if head target
			BasketItem temp = head;
			while (temp.getNext() != null) {						//if not, checks through
				Product nextData = temp.getNext().getData();		//queue and returns target's
				if (nextData.getProductNum().equals(pn)) {			//quantity when found
					return nextData.getQuantity();
				}
				temp = temp.getNext();
			}
			return 0;
		}
		return head.getData().getQuantity();
	}
	
	
	/**
	 * Takes a product number and amount, iterates through to find
	 * that product in the queue, and either lowers its quantity or
	 * removes it.
	 */
	public void removeItem(String pn, int amount) {
		if (head != null) {											//checks if queue exists
			BasketItem temp = head;
			if (temp.getData().getProductNum().equals(pn)) {		//checks if head is to
				if(temp.getquantity() > amount) {					//be removed. if so, sets 
					temp.setQuantity(temp.getquantity() - amount);	//new head to the next 
				} else {											//item or decrements value
					head = temp.getNext();
				}
				return;
			}
			boolean next = true;
			BasketItem previous = null;
			while (next) {											//iterates through queue items
				if (temp.getData().getProductNum().equals(pn)) {	//checks if is the target product
					if(temp.getquantity() > amount) {
						temp.setQuantity(temp.getquantity() - amount);	//decrements quantity value
						
					} else if (temp.getNext() != null) {
						temp.set(temp.getNext());					//or removes item
					} else {
						previous.setNext(null);
					}
					return;
				}
				else {
					if (temp.getNext() != null) {
						previous = temp;
						temp = temp.getNext();
					}
					else {
						next = false;
					}
				}
			}
			
		}
		return;
	}
	
	
	/**
	 * Returns a description of the products in the basket suitable for printing.
	 * @return a string description of the basket products
	 */
	public String getDetails() {
		Locale uk = Locale.UK;
		StringBuilder sb = new StringBuilder(256);
		Formatter fr = new Formatter(sb, uk);
		String csign = (Currency.getInstance(uk)).getSymbol();
		double total = 0.00;
		if (this.getOrderNum() != 0)
			fr.format("Order number: %03d\n", this.getOrderNum());
		if (head != null) {
			BasketItem temp = head;
			boolean exists = true;
			while (temp != null && exists) {
				int number = temp.getquantity();
				fr.format("%-7s", temp.getData().getProductNum());
				fr.format("%-14.14s ", temp.getData().getDescription());
				fr.format("(%3d) ", number);
				fr.format("%s%7.2f", csign, temp.getData().getPrice() * number);
				fr.format("\n");
				total += temp.getData().getPrice() * number;
				if (temp.getNext() != null) {
					temp = temp.getNext();
				}
				else {
					exists = false;
				}
			}
			fr.format("----------------------------\n");
			fr.format("Total                       ");
			fr.format("%s%7.2f\n", csign, total);
			fr.close();
		}
		return sb.toString();
	}

}

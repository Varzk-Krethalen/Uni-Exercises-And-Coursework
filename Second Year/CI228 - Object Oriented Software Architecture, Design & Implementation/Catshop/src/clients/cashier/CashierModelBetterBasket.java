package clients.cashier;

import catalogue.BetterBasket;
import debug.DEBUG;
import middle.MiddleFactory;
import middle.StockException;


public class CashierModelBetterBasket extends CashierModel{
	
  /**
   * Construct the model of the Cashier
   * @param mf The factory to create the connection objects
   */
	public CashierModelBetterBasket(MiddleFactory mf) {
		super(mf);
	}

	/**
	 * Return an instance of a new Basket 
	 * @return an instance of a new Basket
	 */
	protected BetterBasket makeBasket() {
		return new BetterBasket();
	}
	
	/**
	 * Item is removed from basket if valid
	 */
	public void doRemove() {
		String theAction = "";
		if (theState != State.checked) { 								// See if ok
			theAction = "Check if OK with customer first";
		} else if (pn != null && theBasket != null) { 					// if basket and input exist
			removeTry : try {
				int amount 		 = theProduct.getQuantity();
				int basketAmount = theBasket.checkAmount(pn);			// check 
				amount = (amount > basketAmount) ? basketAmount: amount;//makes sure amount isn't >
				if (basketAmount == 0) {								//the quantity in the basket
					theAction = "Invalid Removal";
					break removeTry;
				}
				theBasket.removeItem(pn, amount); 						// remove corresponding product
				theStock.addStock(pn, amount);							// reset stock
				theAction = "Item Removed";
			} catch (StockException e) {
				DEBUG.error( "%s\n%s", 
	            "CashierModel.doBuy", e.getMessage() );
				theAction = e.getMessage();
			}
			
		} else {
			theAction = "Invalid Removal";
		}
		theState = State.process; 										// All Done
		setChanged();
		notifyObservers(theAction);
	}
	
	
}

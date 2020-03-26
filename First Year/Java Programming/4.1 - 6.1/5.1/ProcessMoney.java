/**
 * Deal with processing money entered by a customer
 * to purchase tickets.
 */
public class ProcessMoney
{
  // Instance variables to hold the state of
  //   the ticket machine
  private int theTicketPrice = 0;         // Ticket price
  private int theCredit = 0;              // Money from customer
  private int theMoneyInTheMachine = 0;   // All the money
  
  /**
   * Set the price for the current ticket
   * @param amount Cost of ticket in pence 
   */
  public void setTicketPrice( int amount )
  {
    theTicketPrice = amount;
  }

  /**
   * Return the price in pence of the current ticket
   * @return  Cost of ticket in pence
   */
  public int getTicketPrice()
  {
    int price = theTicketPrice;
    return price;
  }

  /**
   * Add a coin with value 'coin' in pence 
   *  to the ticket machine to 'partial' pay for the ticket.
   * @param coin The coin to add to the ticket machine
   */
  public void add( int coin )
  {
    theCredit += coin;
    theMoneyInTheMachine += coin;
  } 

  /**
   * Return true if sufficent money has been entered to buy
   * the ticket else return false
   * @return true if sufficient money else false
   */
  public boolean enough()
  {
    if (theCredit >= theTicketPrice)
    {
        return true;
    }
    else
    {
        return false;
    }
  }

  /**
   * Return the value in pence paid so far into the machine
   *  for a ticket
   * @return  In pence the amount paid so far
   */
  public int getPaidSoFar()
  {
    int paid = theCredit;
    return paid;
  }

  /**
   * Cancel the purchase of the ticket.
   * 'Coins' are returned to the customer.
   * The amount credited so far is set to zero.
   */
  public void cancel()
  {
    theMoneyInTheMachine -= theCredit;
    theCredit = 0;
  }

  /**
   * The ticket has been bought, and money
   * entered by the user is added to the total
   * money in the machine
   */
  public void bought()
  {
    theCredit = 0;
  }

  /**
   * Deliver in pence the total amount of money in the machine
   *  that has been used to purchase tickets.
   * @return The total money in the machine in pence.
   */
  public int moneyInMachine()
  {
    int money = theMoneyInTheMachine;
    return money;
  }

  // This method is not required for 5.1
  // However for exercise 5.2 it is required
  /**
   * Deliver how many 'coins' are in the machine.
   * Thus if there were 63 10p coins
   *  getNumCoins( 10 ) would return 63.
   * @param  coin The coin selected
   * @return Total number of 'coin'p coins in the machine.
   */
  public int getNumCoins( int coin )
  {
    return 0;
  }
}

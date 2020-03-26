import java.util.*;

/**
 * A model of the ATM
 */
public class Model extends Observable
{
  private enum  State { ACCOUNT_NO, PASSWORD, PROCESSING }
  
  private State state = State.ACCOUNT_NO;
  private long  number = 0;
  private Bank  bank = new Bank();
  
  private String cAction = "";    // For checking what stage the atm is in
  private String display1 = null; // Message 1
  private String display2 = null; // Message 2

  /**
   * Construct the model of the ATM
   */
  public Model()
  {
    Debug.trace("Constructor Model");
    display1 =  "Welcome to the ATM\n";
    display2 =  "Welcome: Enter your 6 digit account number\n" +
                "Followed by \"Ent\"";
  }
  
  /**
   *  process ATM transaction
   *  @param button name of pressed button
   */
  public void process( String button )
  {
    Debug.trace("VewATM.process State [%-10s] Button = <%s>",
                 state.toString(), button );

    switch ( button )
    {
      case "1" : case "2" : case "3" : case "4" : case "5" :
      case "6" : case "7" : case "8" : case "9" : case "0" :
        char c = button.charAt(0);
        number = number * 10 + c-'0';           // Build number 
        display1 = "" + number;
        display();                              // Update display
        return;
      case "CLR" :
        number = 0;
        display1 = String.format("%d", number);
        display();                              // Update display
        return;
      case "Ent" :
        if (cAction.equals(""))  // checks to see if the atm is in the initial state,
        {                        // waiting for a login
            switch ( state )
            {
              case ACCOUNT_NO:
                bank.setAcNumber( number );
                number = 0;
                state = State.PASSWORD;
                display1 = "";
                display2 = "Now enter your 5 digit password\n" +
                           "Followed by \"Ent\"";
                break;
              case PASSWORD:
                bank.setAcPasswd( number );
                number = 0;
                display1 = "";
                if ( bank.checkValid() )
                {
                  state = State.PROCESSING;
                  display2 = "Accepted\n" +
                             "Now enter the transaction you require";
                  cAction = "WaitTrans";
                } else {
                  state = State.ACCOUNT_NO;
                  bank.loggedOut();
                  display2 =  "Error: Please enter your a/c number";
                }
                break;
              default :
            }
        }
        else if (cAction.equals("WithWait"))    // if the atm is waiting to withdraw, and ent is
        {                                       // pressed, withdraws the current number
            display1 = "";
            if ( bank.withdraw( number ) )
            {
              display2 =   "Withdrawn: " + number + " : Balance = " + bank.getBalance();
              bank.logToFile("Withdrawal", number, "Success");
            } else {
              display2 =   "You do not have sufficient funds";
              bank.logToFile("Withdrawal", number, "Failure");
            }
            cAction = "WaitTrans";
        }
        else if (cAction.equals("DepoWait"))    // if the atm is waiting to deposit, and ent is
        {                                       // pressed, deposits the current number
            bank.deposit( number );
            display1 = "";
            display2 = "Deposited: " + number + " : Balance = " + bank.getBalance();
            bank.logToFile("Deposit", number, "Success");
            number = 0;
            cAction = "WaitTrans";
        }
        display();                             // Update display
        return;
  
    }

    if ( state != State.PROCESSING ) 
    {
      state = State.ACCOUNT_NO;
      display1 =  "But you are not logged in\n";
      display2 =  "Welcome: Enter your account number\n" +
                  "Followed by \"Ent\"";
      display();                              // Update display
      return;
    }
    
    if (cAction.equals("WaitTrans")) // only runs if the atm is waiting for a transaction command
    {
        switch ( button )
        {
          case "W/D" :               // Withdraw action - sets the cAction variable, so that
            cAction = "WithWait";    // the atm will wait for input and ENT before withdrawing
            number = 0;
            display2 = "Enter amount to withdraw, then press enter";
            break;
     
          case  "Bal" :               // Balance action
            number = 0;
            display2 = "Your balance is: " + bank.getBalance();
            bank.logToFile("Balance", number, "Success");
            break;
        
          case "Dep" :               // Deposit action - sets the cAction variable, so that
            cAction = "DepoWait";    // the atm will wait for input and ENT before depositing
            number = 0;
            display2 = "Enter amount to deposit, then press enter";
            break;
        
          case "Fin" :               // Finish transactions
            state = State.ACCOUNT_NO;
            number = 0;
            display2 = "Welcome: Enter your 6 digit account number";
            bank.loggedOut();
            cAction = "";            // resets cAction to initial state, to allow logging in
            break;
        }
    }
    display();                              // Update display
  }

  /** Return the number input 
   *  @return The result of the transaction
   */
  public long getResult()
  {
    return number;
  }
 
  
  /** Return message 1 
   *  @return message #1 from model, usually current state
   */
  public String getMessage1()
  {
    return display1;
  }
  
  /** Return message 2
   *  @return message #2 from model, information about what has happened
   */
  public String getMessage2()
  {
    return display2;
  }
  
  /** Ask for a redisplay of the ATM screen*/
  public void display()
  {
    setChanged(); notifyObservers();
  }
}

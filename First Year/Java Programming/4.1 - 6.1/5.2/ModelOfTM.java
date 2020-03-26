import java.util.*;

/**
 *   Model of a ticket machine
 */
public class ModelOfTM extends Observable
{
  private enum  State { DESTINATION, PAY };
  private State state = State.DESTINATION;
  private String display1 = "";
  private String display2  = "";
  private String destination = "";
 
  private ProcessMoney pm = new ProcessMoney();

  public ModelOfTM()
  {
    display1 = "Welcome: Please select your destination";
    display2 = "";
  }
 
  /**
   * Return the message for area 1 in the display
   * @return The message for area 1
   */
  public String getDisplay1() { return display1; }

  /**
   * Return the message for area 2 in the display
   * @return The message for area 2
   */
  public String getDisplay2() { return display2; }
  
  private void setDestination( String too, int price )
  {
    destination = too;
    display1 = "Ticket Machine";
    display2 = "Cost of a ticket to " + too +
               " is " + price + " pence\n" +
               "Please enter money for the ticket\n\n" +
               "To select another destination " +
               " press Cancel first\n";
               
    state = State.PAY;
    pm.setTicketPrice( price );
  }

  /**
   * Process a transaction on the model of the ticket machine
   * @param button The action requested
   */
  public void process( String button )
  {
    switch  ( state )
    {
      case DESTINATION :
        switch ( button )
        {
          case "Brighton" :
            setDestination( button, 260 ); break;
          case "London Road" :
            setDestination( button, 260 ); break;
          case "Falmer" : 
            setDestination( button, 260 ); break;
          case "Lewes" :
            setDestination( button, 430 ); break;
          case "Start" :
              display1 = "Ticket Machine";
              display2 = " 1) Select your destination\n" + 
                         " 2) Enter Money\n" +
                         " 3) Select Buy to buy, Cancel to cancel transaction";
              break;

         default :
              display1 = "Ticket Machine";
              display2 = "Select a destination first";
        }
        break;

      case PAY :
        switch ( button )
        {
          case "Help" :
              display1 = "Ticket Machine";
              display2 = " 1) Select your destination\n" + 
                         " 2) Enter Money\n" +
                         " 3) Select Buy to buy, Cancel to cancel transaction";
          case "Buy" :
            if ( pm.enough() )
            {
              pm.bought();
              display1 = "Ticket Machine";
              display2 = "You have now paid for your ticket to " + 
                            destination + "\n\n" +
                            "Single ticket for " + destination + " delivered";
              state = State.DESTINATION;
            } else {
              display1 = String.format("Enter more money: " +
                           "Ticket price %dp - still to pay %dp",
                           pm.getTicketPrice(),  
                           pm.getTicketPrice()-pm.getPaidSoFar()  );
            }
            break;
          
          case "Cancel" :
          case "Start"  :
            display1 = "Ticket Machine";
            if ( pm.getPaidSoFar() > 0 )
            {
              display2 = "Your money of " + 
                          pm.getPaidSoFar() + "p is returned\n";
            } else {
              display2 = "";
            }
            pm.cancel();
            display2 += "Welcome: Please select your destination";
            state = State.DESTINATION;
            break;
          default:
            // Only valid entry is enter amount a number
            try
            {
              int entered = 0;
              entered = Integer.parseInt( button.trim() );
              pm.add( entered );
              int stillToPay = pm.getTicketPrice()-pm.getPaidSoFar();
              String about = "";
              if ( stillToPay > 0 )
              {
                 about = "still to pay " + stillToPay + "p";
              }
              else if ( stillToPay == 0 )
              {
                 about = "correct amount entered";
              }
              else
              {
                 about = "OVERPAID by " + -stillToPay + "p";
              }

              
              display1 = String.format("Ticket price %dp - %s",
                           pm.getTicketPrice(),  about );

            } catch ( Exception err )
            {
              // Should not occure
              display1 = String.format("Invalid Entry: " +
                           "Ticket price %dp - still require %dp",
                           pm.getTicketPrice(),  
                           pm.getTicketPrice()-pm.getPaidSoFar()  );
            }
            break;
        }
    }
    setChanged(); notifyObservers();
  }
}

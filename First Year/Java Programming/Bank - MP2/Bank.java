import java.sql.*;
import java.io.FileWriter;

/**
 * The Bank
 * The details of bank account(s) must be held
 *  in a relational database (typically Derby).
 */
public class Bank
{
  /**
   * Create the bank 
   *  uses a database to hold all data
   */
  private Connection con = null;    // Connection to database
  private Statement  stm = null;    // Used to talk to database
                                    // More database connection info below
  private static final String urlDB =
                 "jdbc:derby:Accounts.db";
  private static final String DRIVER =
                 "org.apache.derby.jdbc.EmbeddedDriver";

  private long accNumber = 0;        // The current account number
  private long accPasWrd = 0;        // The current account password
  private boolean accessOK = false;  // Can access account info.

  /**
   * Constructor sets up link to database,
   * making sure it's connected for use
   */
  public Bank()
  {
    try
    {
      Class.forName(DRIVER).newInstance(); //  Driver to access database
      con  = DriverManager.getConnection( urlDB, "", "" );
    }
    catch ( SQLException e )
    {
      System.err.println( "Problem with connection to " + urlDB );
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLState:     " + e.getSQLState());
      System.out.println("VendorError:  " + e.getErrorCode());
      System.exit( -1 );
    }
    catch ( Exception e )
    {
        // Most likely library's of Derby not made visible 
        System.err.println("Can not load JDBC driver.");
        System.exit( -1 );
    }
    try {
        //stm = con.createStatement();
        stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
		                          ResultSet.CONCUR_UPDATABLE);
    } catch (Exception e) {
        System.err.println("problems creating statement object" );
    }
  }

  /**
   * Set the number of the selected account
   * @param accNumber The account number
   */
  public void setAcNumber( long Number ) 
  { 
      accNumber = Number;
  }

  /**
   * Set the password for the selected account
   * @param accPasWrd The account password
   */
  public void setAcPasswd( long PasWrd ) 
  { 
      accPasWrd = PasWrd;
  }

  /**
   * Set the internal state to be no access 
   *  for account transactions
   */
  public void loggedOut() 
  {
      accNumber = 0;                 // Destroy so can not be used
      accessOK = false;              // Revoke/Stop access to Account
  }

  /**
   * Check account number and password is valid.
   * if the account number and password are correct then
   * set the internal state to be valid access 
   * @return success/ failure
   */
  public boolean checkValid() 
  {    
      long passHash = hashPass();   // hashes the password, removing the password, leaving only the hash
      long result = 0;              // A temporary variable for checking SQL results
      ResultSet res = null;         // Used to hold the SQL query results
      try
      {
          res = stm.executeQuery("select PassHash from AccountInfo" + " where AccountNo ='" + accNumber + "'" );   // selects the password hash for a given account
          if (res.next())
          {
            result = res.getLong("PassHash");       // sets result to equal that hash
          }
      }
      catch ( Exception e )
      {
          System.err.println("problems with SQL statement:\n" + e.getMessage() );
      }
      if (passHash == result)   // compares account's hash with the one processed from the input password
      {                         // If correct, allows access to main ATM functions for that account
          accessOK = true;
          return true; 
      }
      else
      {
          Debug.trace( "Bank: checkValid" ); 
          accNumber = passHash = -1;     // Destroy so can not be used
          accessOK = false;              // Revoke/Stop access to Account
          return false;
          
      }
  }

  /**
   * Withdraws money from account,
   * updating the amount in the database
   * @param amount of money to W/D
   * @return success/ failure
   */
  public boolean withdraw( long amount ) 
  { 
      long tempBal = -1;        // Temp variable for the balance
      ResultSet res = null;     // Used to hold the SQL query results
      try
      {
          res = stm.executeQuery("select Balance from AccountInfo where AccountNo ='" + accNumber + "'" );  // selects the balance for a given account
          if (res.next())
          {
            tempBal = res.getLong("Balance");   // sets temp variable to balance
          }
      }
      catch ( Exception e )
      {
          System.err.println("problems with SQL statement:\n" + e.getMessage() );
      }
      if (tempBal >= amount)    // if the balance is greater than withdraw amount, sets the database balance to equal balance - withdrawal
      {
          try
          {
              tempBal -= amount;
              stm.executeUpdate("update AccountInfo AccountInfo set Balance = " + tempBal + " where AccountNo ='" + accNumber + "'" );
          }
          catch ( Exception e )
          {
              System.err.println("problems with SQL statement:\n" + e.getMessage() );
          }
          return true;
      }
      else
      {
          Debug.trace( "Bank: withdraw %d", amount ); 
          assert ( accessOK );  // Major security issue
          return false; 
      }
  }
  
  /**
   * Deposits money into account,
   * updating the amount in the database
   * @param amount of money to deposit
   * @return success/ failure
   */
  public boolean deposit( long amount )
  {
      long tempBal = -1;        // Temp variable for the balance
      ResultSet res = null;     // Used to hold the SQL query results
      try
      {
          res = stm.executeQuery("select Balance from AccountInfo where AccountNo ='" + accNumber + "'" );  // selects the balance for a given account
          if (res.next())
          {
            tempBal = res.getLong("Balance");   // sets temp variable to balance
          }
      }
      catch ( Exception e )
      {
          System.err.println("problems with SQL statement:\n" + e.getMessage() );
      }
      tempBal += amount;    // adds deposited amount to temp variable
      try
      {
          stm.executeUpdate("update AccountInfo AccountInfo set Balance = " + tempBal + " where AccountNo ='" + accNumber + "'" );  // sets the database balance to equal the temporary
          return true;
      }
      catch ( Exception e )
      {
          System.err.println("problems with SQL statement:\n" + e.getMessage() );
          Debug.trace( "Bank: Deposit " + amount ); 
          assert ( accessOK );
          return false;
      }
  }
  
  /**
   * Get balance of account
   * @return balance of account
   */
  public long getBalance() 
  { 
      long tempBal = -1;        // Temp variable for the balance
      ResultSet res = null;     // Used to hold the SQL query results
      try
      {
          res = stm.executeQuery("select Balance from AccountInfo where AccountNo ='" + accNumber + "'" );  // selects the balance for a given account
          if (res.next())
          {
            tempBal = res.getLong("Balance");   // sets temp variable to balance
          }
      }
      catch ( Exception e )
      {
          System.err.println("problems with SQL statement:\n" + e.getMessage() );
      }
      if (tempBal >= 0)
      {
          System.out.print(tempBal);    // if the stored balance is valid, returns it to be printed
          return tempBal; 
      }
      else
      {
          Debug.trace( "Bank: get balance" ); 
          assert ( accessOK );
          return 0;
      }
  }
  
  /**
   * A very very basic hash function, based
   * on multiplying the number by primes.
   * Produces an 8 digit number, based on
   * an allowed 5 digit password.
   */
  public long hashPass()
  {
      long passHash;
      accPasWrd -= 1;
      accPasWrd = accPasWrd * 31;
      accPasWrd += 1;
      accPasWrd = accPasWrd * 31;
      passHash = accPasWrd;
      accPasWrd = -1;                // Destroy so can not be used
      return passHash;
  }
  
  /**
   * Creates a csv log file of transactions (deposit/withdraw)
   * in the format: 
   * AccountNumber,Action,Amount(deposited/withdrawn),SuccessState,NewBalance
   */
  public void logToFile(String action, long amount, String state)
  {
      String csvString = "" + accNumber + "," + action + "," + amount + "," + state + "," + getBalance();   // creates a string of the needed information
      try
      {
          FileWriter writer = new FileWriter("atmLog.csv", true); // creates a new file object - opens the existing log file if it exists
          writer.append(csvString); // writes the main info string to the file
          writer.append('\n');      // adds a newline
          writer.flush();           // makes sure data is written
          writer.close();           // closes the file object
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }
 

}

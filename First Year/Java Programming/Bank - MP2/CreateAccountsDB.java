import java.sql.*;


/**
 * Create database containing Accounts for use in the ATM
 * @version 1.0
 */
class CreateAccountsDB
{
    public static void main( String args[] )
  {
    CreateAccountsDB db = new CreateAccountsDB();
    db.createDB();       // Create & setup connection
    db.createTable();    // Create table in database
    db.populate();       // Populate table in database
    db.close();          // Otherwise you need to reset virtual machine
  }

  private Connection con = null;   // Connection to database
  private Statement  stm = null;   // Used to talk to database

  private static final String urlDB =
                 "jdbc:derby:Accounts.db;create=true";
  private static final String DRIVER =
                 "org.apache.derby.jdbc.EmbeddedDriver";

  /**
   * Create database for the first time and establish a
   *  connection to the database.
   */
  public void createDB()
  {
    try
    {
      Class.forName(DRIVER).newInstance();    //  Driver to access database
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
        System.err.println("Can not load JDBC driver.");
        System.exit( -1 );
    }

    try {
        // Create a statement object
        //  used to send SQL to the database
        stm = con.createStatement();
    } catch (Exception e) {
        System.err.println("problems creating statement object" );
    }
  }

  /**
   * Create the table AccountInfo
   *  composed of the AccountNo, PassHash, Balance
   * 
   */
  public void createTable()
  {
    try
    {
      // Remove (drop) table as create in next section
      stm.execute( "drop table AccountInfo" );     // May fail
    } catch (Exception e)
    {
      // Table did not exist - just ignore
      //   Usual case, when database initially created
    }


     String sql = "create table AccountInfo (     " +
                  "  AccountNo      Char(6) ,   " +
                  "  PassHash       Char(8),    " +
                  "  Balance        Float)";
    try
    {
       stm.execute( sql );

    } catch (Exception e)
    {
      System.out.printf("problems with SQL statement:\n %s\n %s\n",
                         sql, e.getMessage() );
      System.exit(-1);  // Give up
    }
  }

  /**
   * Populate the table AccountInfo with examples of Accounts<PRE>
   */
  public void populate()
  {
    try
    {
      // Populate with some values
      stm.execute( "insert into AccountInfo values " +
                  "('100000', '10676741', 176)" );
      stm.execute( "insert into AccountInfo values " +
                  "('200000', '21354412', 21)" );
      stm.execute( "insert into AccountInfo values " +
                  "('300000', '32032083', 7)" );
      stm.execute( "insert into AccountInfo values " +
                  "('400000', '42709754', 109)" );
      stm.execute( "insert into AccountInfo values " +
                  "('500000', '53387425', 15)" );


    } catch (Exception e)
    {
      System.err.println("problems with SQL statement:\n" +
                         e.getMessage() );
    }
  }

  /**
   * Close the Database
   */
  public void close()
  {
    try
    {
      stm.close();          // Close the statement Object
      con.close();           // Close the connection to the Database
    } catch (SQLException e )
    {
      System.err.println( e.getMessage() );
    }
  }
}

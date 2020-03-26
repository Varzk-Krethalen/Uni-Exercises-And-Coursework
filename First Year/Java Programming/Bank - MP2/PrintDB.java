import java.sql.*;
public class PrintDB
{
    public static void main()
    {
        Connection con = null;    // Connection to database
        Statement  stm = null;    // Used to talk to database
        final String urlDB =
                 "jdbc:derby:Accounts.db";
        final String DRIVER =
                 "org.apache.derby.jdbc.EmbeddedDriver";
        ResultSet rs = null;
        try //connect to database
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
        try //select and print all contents of database - For testing purposes
          {
              rs = stm.executeQuery("select * from AccountInfo");
              ResultSetMetaData rsmd = rs.getMetaData();
              int columnsNumber = rsmd.getColumnCount();
              while (rs.next()) {
                  for (int i = 1; i <= columnsNumber; i++) {
                      if (i > 1) System.out.print(",  ");
                      String columnValue = rs.getString(i);
                      System.out.print(columnValue + " " + rsmd.getColumnName(i));
                  }
                  System.out.println("");
              }    
          }
          catch ( Exception e )
          {
              System.err.println("problems with SQL statement:\n" + e.getMessage() );
          }
    }
}

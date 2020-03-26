import java.util.*;

public class Test
{
  public static void main( String args[] )
  {
    ModelOfDiary m = new ModelOfDiary();

    for ( int year=2070; year<=2080; year++ )
    {
      for ( int month=1; month<=12; month++ )
      {
        for ( int day=1; day<=28; day++ )
        {
          String key  =  makeKey(year,month,day);
          String message = m.getMessageForToday();
          if ( message == null )
          {
            System.out.printf("FAIL date <%s>\n" +
                   "     retrieved message for date was null\n" +
                   "     Should have been \"\" as entry for date not in diary\n" +
                   "     Did you check for key present in the map using\n" +
                   "     the method containsKey(). ",
                   key );
            System.exit(0);
          }
          if ( ! message.equals( "" ) )
          {
            System.out.printf("FAIL date <%s>\n" +
                   "     retrieved message for date was \"%s\"\n" +
                   "     Should have been \"\" as entry for date not in diary\n",
                   key, message );
            System.exit(0);
          }
        }
      }
    }

    int check = 0;
    for ( int year=2000; year<=2020; year++ )
    {
      for ( int month =1; month<=12; month++ )
      {
        for ( int day = 1; day<=28; day++ )
        {
          check++;
          m.setDate( year, month, day );
          String key  =  makeKey(year,month,day);
          String message = String.format( "6.1  Seq check: %06d %s",
                               check, makeKey(year, month, day) );
          m.saveMessageForToday( message );
        }
      }
    }

    check = 0;
    for ( int year=2000; year<=2020; year++ )
    {
      for ( int month = 1; month<=12; month++ )
      {
        for ( int day = 1; day<=28; day++ )
        {
          check++;
          m.setDate( year, month, day );
          String key     = makeKey(year,month,day);
          String today   = m.getMessageForToday();
          String message = String.format( "6.1  Seq check: %06d %s",
                               check, makeKey(year, month, day) );
          if ( ! today.equals( message ) )
          {
            System.out.printf("FAIL for date <%s>\n" +
                              "     the stored  message was   <%s>\n" +
                              "     the retrieved message was <%s>\n",
                             key, message, today );
            System.exit(0);
          }
        }
      }
    }

    int dateCheck[][] =  { { 2016,  1, 31 },
                           { 2016,  2, 29 },
                           { 2016,  3, 31 },
                           { 2016,  4, 30 },
                           { 2016,  5, 31 },
                           { 2016,  6, 30 },
                           { 2016,  7, 31 },
                           { 2016,  8, 31 },
                           { 2016,  9, 30 },
                           { 2016, 10, 31 },
                           { 2016, 11, 30 },
                           { 2016, 12, 31 },
                           { 2017,  1, 31 },
                           { 2017,  2, 28 },
                           { 2017,  3, 31 },
                           { 2017,  4, 30 },
                           { 2017,  5, 31 },
                           { 2017,  6, 30 },
                           { 2017,  7, 31 },
                           { 2017,  8, 31 },
                           { 2017,  9, 30 },
                           { 2017, 10, 31 },
                           { 2017, 11, 30 },
                           { 2017, 12, 31 },
                           { 2012,  2, 29 },
                           { 2000,  2, 29 },
                           { 2001,  2, 28 },
                           { 2002,  2, 28 },
                           { 2003,  2, 28 },
                           { 2004,  2, 29 },
                           { 1900,  2, 28 },
                           { 1800,  2, 28 },
                           { 1700,  2, 28 },
                           { 1600,  2, 29 },
                           { 2100,  2, 28 }
                        };


    for ( int[] date : dateCheck )
    {
      int year  = date[0];
      int month = date[1];
      int days  = date[2];

      int calcDays = m.maxDaysInMonth(year, month);
      if (calcDays != days)
      {
        System.out.printf("Year %4d month %2d" +
                          " has %2d days and not %2d days\n",
                          year, month, days, calcDays);
        System.exit(0);
      }
    }
    System.out.println("Success");
  }

  private static String makeKey(int year, int month, int day)
  {
    String date =  String.format( "%04d/%02d/%02d", year, month, day );
    return date;
  }
}


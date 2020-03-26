import java.util.*;

/**
 *   Model of a diary
 */
public class ModelOfDiary extends Observable
{
  private DiaryIO dio = new DiaryIO();
  // The diary is represented by a map
  // The key is the diary entry date
  // and the data value is a string representing the diary entry
  private Map< String, String> diary;  // date, message

  private int year = 2017, month = 4, day = 23;

  /**
   * Construct the diary
   * By reloading if possible a saved version
   */
  public ModelOfDiary()
  {
    diary = dio.read();     // Read previous saved diary
  }

  /**
   * Retrieve the message for the current date, from the diary
   * If no event for the date then the 
   *  empty "" string is returned
   * @return The diary entry for the current date.
   */
   // The date is held in the instance variables 
   //   year, month, day
   // Use the date as the key (A String) to retrieve the message
   // from the map used to represent the diary entries
  public String getMessageForToday()
  {
    // *********************************************************
    // ***  You need to add code here                        ***
    // *********************************************************
    String dateString = Integer.toString(getDay()) + "/" + Integer.toString(getMonth()) + "/" + Integer.toString(getYear());
    if( diary.containsKey(dateString))
    {
        return diary.get(dateString);
    }
    else
    {
        return "";   
    }
  }

  /**
   * Set in the diary the message for the current day 
   * @param message The message to be set in the diary
   */
   // The date is held in the instance variables 
   //   year, month, day
   // Use the date as the key (A String) to store the message
   // to the map used to represent the diary entries
   // Note: Map is an interface
  public void saveMessageForToday( String message )
  {
    // *********************************************************
    // ***  You need to add code here                        ***
    // *********************************************************
    String dateString = Integer.toString(getDay()) + "/" + Integer.toString(getMonth()) + "/" + Integer.toString(getYear());
    diary.put(dateString, message);
  }

  /**
   * Return the number of days in the month for the year
   * month = 1 - 12, Jan = 1, Feb = 2, etc.
   * years in the range 1600 - 2400
   * @param year The year
   * @param month The month
   * @return The days in the month specified
   */
  public int maxDaysInMonth( int year, int month )
  {
    // *********************************************************
    // ***  You need to add code here                        ***
    // *********************************************************
    int numDays = 0;
    switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numDays = 30;
                break;
            case 2:
                if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
                {
                    numDays = 29;
                }
                else
                {
                    numDays = 28;
                }
                break;
    }
    return numDays;
  }

  /**
   * Return From the current date return the year 
   * @return The current year in the date 
   */
  public int getYear()  { return year; }

  /**
   * Return From the current date return the month 
   * @return The current month in the date 
   */
  public int getMonth() { return month; }

  /**
   * Return From the current date return the day 
   * @return The current day in the date 
   */
  public int getDay()   { return day; }

  /**
   * To the current data add +1 or -1 to the year
   * @param add Amount to add [+-]1
   */
  public void addToYear( int add )
  {
    year += add;
    if ( year < 2000 ) year = 2000;
    if ( year > 2100 ) year = 2100;
    int mDays = maxDaysInMonth( year, month );
    if ( day > mDays ) day = mDays;
    setChanged(); notifyObservers();
  }

  /**
   * To the current data add +1 or -1 to the month
   * @param add Amount to add [+-]1
   */
  public void addToMonth( int add )
  {
    month += add;
    if ( month < 1  ){ addToYear(-1); month = 12; }
    if ( month > 12 ){ addToYear(+1); month = 1;  }
    int mDays = maxDaysInMonth( year, month );
    if ( day > mDays ) day = mDays;
    setChanged(); notifyObservers();
  }

  /**
   * To the current data add +1 or -1 to the day
   * @param add Amount to add [+-]1
   */
  public void addToDay( int add )
  {
    day += add;
    if ( day < 1 )
    {
      // Move to last day of previous month
      day = 1;       // avoid endless recursion
      addToMonth(-1);
      day = maxDaysInMonth( year, month );
    } else {
      // Move to next day
      int maxday = maxDaysInMonth( year, month );
      if ( day > maxday )
      {
        day = 1;
        addToMonth(+1);
      }
    }
    setChanged(); notifyObservers();
  }

  /**
   * Set the current date
   * @param  aYear The year
   * @param  aMonth The Month
   * @param  aDay   The day
   */
  public void setDate( int aYear, int aMonth, int aDay )
  {
    day = aDay; month = aMonth; year = aYear;
    setChanged(); notifyObservers();
  }

  /**
   * Close the diary
   */
  public void close()
  {
    dio.write( diary );     // Save the diary to disk
  }

  /**
   * Change the date
   * For example +Y Add 1 year, -M subtract 1 month 
   * @param which "[+-][YMD]" 
   */
  public void changeDate( String which )
  {
    switch ( which )
    {
       case "+Y" : addToYear(+1);  break;
       case "-Y" : addToYear(-1);  break;
       case "+M" : addToMonth(+1); break;
       case "-M" : addToMonth(-1); break;
       case "+D" : addToDay(+1);   break;
       case "-D" : addToDay(-1);   break;
    }
  }
}

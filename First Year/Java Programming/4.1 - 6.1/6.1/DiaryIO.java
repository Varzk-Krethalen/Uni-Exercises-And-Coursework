import java.util.*;
import java.io.*;

public class DiaryIO
{
  /**
   * Write the map to a disk file called diary.dat
   * @param diary The map representing the diary
   */
  public void write( Map<String,String> diary )
  {
    try
    {
      FileOutputStream ostream = new FileOutputStream("diary.dat");
      ObjectOutputStream oos   = new ObjectOutputStream(ostream);

      oos.writeObject( diary );

      oos.flush(); 
      oos.close(); 
      ostream.close();
    }
    catch ( IOException e )
    {
      System.out.println("IOException : " + e.getMessage() );
    }
    catch ( Throwable e )
    {
      System.out.println("FAIL : " + e.getMessage() );
    }
  }

  @SuppressWarnings("unchecked")
  private static
    Map<String,String> readObject( ObjectInputStream in)
      throws java.io.IOException, java.lang.ClassNotFoundException
  {
    return (Map<String,String>) in.readObject();
  }

  /**
   * Read the map representing the diary from the file diary.dat
   * If fails to read the file diary.dat create an empty map.
   * @return A map representing the diary
   */
  public Map<String,String> read()
  {
    try
    {
      FileInputStream istream = new FileInputStream("diary.dat");
      ObjectInputStream   ois = new ObjectInputStream(istream);

      Map <String,String> diary = readObject(ois);

      ois.close(); 
      istream.close(); 
      return diary;
    }
    catch ( Exception e )
    {
      // Failed to read diary, so create an instance of an object 
      //  that implements the map interface
      return new HashMap< String, String >( 1000 );
    }
  }
}


import java.util.*;

class Module
{
  private ArrayList<Student> people = new ArrayList<>();
  /**
   * Add a student
   * @param student A students details
   */
  public void add( Student student )
  {
    people.add( student );
  }

  /**
   * Return the number of students who pass this module
   * @return Number of students who have passed
   */
  public int pass()
  {
    int pass = 0;
    for (int i = 0; i < people.size(); i++)
    {
        if (people.get(i).getMark() >= 40) {
            pass++;
        }    
    }
    return pass;
    }

  /**
   * Return the number of students who fail this module
   * @return Number of students who have failed
   */
  public int fail()
  {
    int fail = 0;
    for (int i = 0; i < people.size(); i++)
    {
        if (people.get(i).getMark() < 40) {
            fail++;
        }    
    }
    return fail;
  }

  /**
   * Return the name of the student with the highest mark
   *  There will only be 1 students who has the highest mark
   * @return Name of the student with the highest mark.
   */
  public String top()
  {
    String top = "";
    int topMark = 0;
    for (int i = 0; i < people.size(); i++)
    {
        if (people.get(i).getMark() > topMark) {
            top = people.get(i).getName();
            topMark = people.get(i).getMark();
        }
    }
    return top;
  }

  /**
   * Return the average mark of students on the module
   * @return The average mark
   */
  public double average()
  {
    int total = 0;
    for (int i = 0; i < people.size(); i++)
    {
        total += people.get(i).getMark();
    }
    double average = (double)total / people.size();
    return average;
  }
}

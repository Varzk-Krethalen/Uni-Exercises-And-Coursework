public class Calculator
{
  // Declaration of a long variable to hold the stored result
  private long theResult = 0;

  // Evaluate an arithmetic operation on the stored result
  //  E.g evaluate( '+', 9) would add 9 to the stored result
  //      evaluate( '/', 3) would divide the stored result by 3
  //      actions are '+'. '-', '*', '/'
  // Note: if the operation is
  //      evaluate( '/', 0 ) the theResult returned must be 0
  //      (Not mathematically correct)
  //      You will need to do a special check to ensure this
  /**
   * perform the operation 
   *  theResult = theResult 'action' number
   * @param action An arithmetic operation + - * /
   * @param number A whole number
   */
  public void evaluate( char action, long number )
  {
    if (action == '+')
        theResult += number;
    else if (action == '-')
        theResult -= number;
    else if (action == '/' && number != 0)
        theResult = theResult / number;
    else
        theResult = theResult * number;
  }

  /**
   * Return the long calculated value
   * @return The calculated value
   */
  public long getValue()
  {
    return theResult;
  }

  /**
   * Set the stored result to be number
   * @param number to set result to.
   */
  public void setValue( long number )
  {
    theResult = number;
  }

  /**
   * Set the stored result to be 0
   */
  public void reset()
  {
    theResult = 0;
  }

}


public class Counter
{
  private long value = 0;

  /**
   * Return the current value of the counter
   * @return The value of the counter
   */
  public long getValue()
  {
    return value;
  }

  /**
   * Reset the counter to zero
   */
  public void reset()
  {
      value = 0;
  }

  /**
   * Add 1 to the counter
   */
  public void inc()
  {
    value++;
  }

  /**
   * Subtract 1 from the counter
   */
  public void dec()
  {
    value--;
  }

  /**
   * Add 5 to the counter
   */
  public void add5()
  {
    value += 5;
  }

  /**
   * Add 10 to the counter
   */
  public void add10()
  {
    value += 10;
  }

}


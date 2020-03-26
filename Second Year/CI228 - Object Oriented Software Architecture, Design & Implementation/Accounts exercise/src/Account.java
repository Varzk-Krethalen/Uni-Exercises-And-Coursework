class Account
{
  private double the_balance  = 0.0d;  // Balance of account
  private double theOverdraft = 0.0d;  // Overdraft allowed

  public Account() {
    the_balance = theOverdraft = 0.00;
  }

  public double getBalance()
  {
    return the_balance;
  }

  public double withdraw( final double money )
  {
    assert money >= 0.00;             // Cause error if money -ve
    if ( the_balance - money >= theOverdraft )
    {
      the_balance = the_balance - money;
      return money;
    } else {
      return 0.00;
    }
  }

  public void deposit( final double money )
  {
    assert money >= 0.00;             // Cause error if money -ve
    the_balance = the_balance + money;
  }


  public void setOverdraftLimit( final double amount )
  {
    theOverdraft = amount;
  }

  public double getOverdraftLimit()
  {
    return theOverdraft;
  }
}


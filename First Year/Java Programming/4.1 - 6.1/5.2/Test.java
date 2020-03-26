public class Test
{
  private static ProcessMoneyX pm = new ProcessMoneyX();

  public static void main( String args[] )
  {
    int res = 0, expected = 100;

    // ---------------------------------------------------------------

    test( "setTicketPrice() & getTicketPrice() ");

    pm.setTicketPrice( expected );
    res = pm.getTicketPrice();
    check( res == expected,
          "Ticket price is %d should be %d", res, expected );

    anotherTest();
    expected = 200;
    pm.setTicketPrice( expected );
    res = pm.getTicketPrice();
    check( res == expected,
          "Ticket price is %d should be %d", res, expected );

    // ---------------------------------------------------------------

    test( "add() & getPaidSoFar()");

    pm.add( 10 ); pm.add( 20 ); pm.add( 50 );
    expected = 80;
    res = pm.getPaidSoFar();
    check( res == expected,
          "Money entered into machine is %d should be %d", res, expected );


    anotherTest();
    pm.add( 20 ); pm.add( 10 ); pm.add( 100 );
    expected = 80 + 130;
    res = pm.getPaidSoFar();
    check( res == expected,
          "Money entered into machine is %d should be %d", res, expected );

    // ---------------------------------------------------------------

    test( "add() & cancel()");

    pm.add( 10 ); pm.add( 20 ); pm.add( 50 );
    expected = 0;
    pm.cancel();
    res = pm.getPaidSoFar();
    check( res == expected,
          "money entered into machine is now %d should be 0", res );

    anotherTest();
    pm.add( 100 ); pm.add( 200 ); pm.add( 50 );
    expected = 0;
    pm.cancel();
    res = pm.getPaidSoFar();
    check( res == expected,
          "money entered into machine is now %d should be 0", res );

    // ---------------------------------------------------------------

    test( "enough()");

    pm.setTicketPrice( 200 );
    pm.add(10); pm.add(20); pm.add( 50 ); pm.add( 100 ); pm.add( 200 );
    expected = 380;
    check( pm.enough(),
          "Enough money entered into machine 380 for 200 ticket" );
    pm.cancel();

    anotherTest();
    pm.setTicketPrice( 210 );
    pm.add( 100 ); pm.add( 100 ); pm.add( 20 );
    expected = 210;
    check( pm.enough(),
          "Enough money entered into machine 220 for 210 ticket" );
    pm.cancel();

    anotherTest();
    pm.setTicketPrice( 210 );
    pm.add( 100 ); pm.add( 20 ); pm.add( 20 );
    expected = 140;
    check( ! pm.enough(),
          "Not enough money entered into machine 140 for 210 ticket" );
    pm.cancel();

    anotherTest();
    pm.setTicketPrice( 210 );
    pm.add( 100 ); pm.add( 50 ); pm.add( 50 );
    expected = 200;
    check( ! pm.enough(),
          "Not enough money entered into machine 200 for 210 ticket" );
    pm.cancel();

    // ---------------------------------------------------------------

    test( "bought() & moneyInMachine()");

    pm.setTicketPrice( 200 );
    pm.add( 100 ); pm.add( 100 ); pm.add( 0 );
    if ( pm.enough() )
    {
      pm.bought();
    }

    expected = 200;
    res = pm.moneyInMachine();
    check( expected == res,
          "Total money in machine %d should be %d", res, expected );


    anotherTest();
    res = pm.getPaidSoFar();
    check( res == 0,
          "Money for ticket in machine is %d should be 0", res );
    pm.cancel();

    pm.setTicketPrice( 200 );
    anotherTest();
    pm.add( 100 ); pm.add( 100 ); pm.add( 10 );
    if ( pm.enough() )
    {
      pm.bought();
    }

    expected = 410;
    res = pm.moneyInMachine();
    check( expected == res,
          "Total money in machine %d should be %d", res, expected );


    anotherTest();
    res = pm.getPaidSoFar();
    check( res == 0,
          "Money for ticket in machine is %d should be 0", res );

    // ---------------------------------------------------------------

    test("Count coins");
    pm = new ProcessMoneyX();
    anotherTest();  checkRecord(  10, 1,  20, 1 );
    anotherTest();  checkRecord(  10, 2,  20, 3 );
    anotherTest();  checkRecord(  20, 4, 100, 2 );
    anotherTest();  checkRecord(  50, 3,  20, 2 );
    anotherTest();  checkRecord( 100, 3, 200, 1 );
    anotherTest();  checkRecord( 200, 2,  10, 2 );
    anotherTest();  checkRecord( 100, 1, 200, 3 );
    anotherTest();  checkRecord( 100, 1,  50, 4 );

    System.out.println( "Success" );
  }

  private static void checkRecord( int coin1, int howMany1, int coin2, int howMany2 )
  {
     pm = new ProcessMoneyX();
     pm.setTicketPrice( howMany1 * coin1 + howMany1 * coin2 );

     for ( int coin: new int[] { 10, 20, 50, 100, 200 } )
     {
       pm.add( coin ); pm.add( coin );
     }
     for ( int i=1; i<=howMany1*2; i++ ) { pm.add( coin1 ); }
     for ( int i=1; i<=howMany2*2; i++ ) { pm.add( coin2 ); }
     pm.cancel();

     for ( int i=1; i<=howMany1; i++ ) { pm.add( coin1 ); }
     for ( int i=1; i<=howMany2; i++ ) { pm.add( coin2 ); }
     pm.bought();

     int actual1 = pm.getNumCoins( coin1 );
     check( howMany1 == actual1,
            "Expected %d - %dp coins found %d - %dp coins",
             howMany1, coin1, actual1, coin1  );

     int actual2 = pm.getNumCoins( coin2 );
     check( howMany2 == actual2,
            "Expected %d - %dp coins found %d - %dp coins",
             howMany2, coin2, actual2, coin2  );

     for ( int coin: new int[] { 10, 20, 50, 100, 200 } )
     {
       if ( ! ( coin == coin1 || coin == coin2 ) )
       {
         int howMany = 0;
         int actual  = pm.getNumCoins( coin );
         check( howMany == actual,
                "Expected %d - %dp coins found %d - %dp coins",
                howMany, coin, actual, coin  );
       }
    }

  }

  private static String what = "";
  private static String how  = "";

  private static void check( boolean ok, String fmt, Object... params )
  {
    if ( ! ok )
    {
      System.out.println( what + how );
      System.out.print("ERROR: " );
      System.out.printf( fmt, params );
      System.out.println();
      System.exit(-1);
    }
  }

  private static void test( String str )
  {
    how  = "";
    what = "Test: "  + str;
  }

  private static void anotherTest()
  {
    how = "";
  }

  public static void testAppend( String str )
  {
    how += "\n  " + str;
  }

}

class ProcessMoneyX extends ProcessMoney
{
  @Override
  public void setTicketPrice( int amount )
  {
    Test.testAppend( String.format("setTicketPrice(%d)", amount ) );
    super.setTicketPrice( amount );
  }

  @Override
  public int getTicketPrice()
  {
    int theTicketPrice = super.getTicketPrice();
    Test.testAppend( String.format("%d <- getTicketPrice()", theTicketPrice ) );
    return theTicketPrice;
  }

  @Override
  public void add( int coin )
  {
    Test.testAppend( String.format("add(%d)", coin ) );
    super.add( coin );
  }

  @Override
  public boolean enough()
  {
    boolean res = super.enough();
    Test.testAppend( String.format("%s <- enough()", (res?"True":"False") ) );
    return res;
  }

  @Override
  public int getPaidSoFar()
  {
    int res = super.getPaidSoFar();
    Test.testAppend( String.format("%d <- getPaidSoFar()", res ) );
    return res;
  }

  @Override
  public void cancel()
  {
    super.cancel();
    Test.testAppend( String.format( "cancel()" ) );
  }

  @Override
  public void bought()
  {
    super.bought();
    Test.testAppend( String.format( "bought()" ) );
  }

  @Override
  public int moneyInMachine()
  {
    int res = super.moneyInMachine();
    Test.testAppend( String.format("%d <- moneyInMachine()", res ) );
    return res;
  }

  @Override
  public int getNumCoins( int coin )
  {
    int res = super.getNumCoins( coin);
    Test.testAppend( String.format("%d <- getNumCoins(%d)", res, coin ) );
    return res;
  }
}

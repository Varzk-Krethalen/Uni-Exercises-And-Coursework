class main25
{
    public static void main(String args [])
    {
        int money = 1;
        String coinstring = "";
        int loop = 0;
        while (money != 0)
        {
            System.out.print("#Enter amount of change in pennies (1-500)");
            money = BIO.getInt();
            if (money != 0)
            {
                if (loop != 0)
                 {
                     coinstring = coinstring + "\n";
                 }
                coinstring = coinstring + maincount(money);
                loop++;
            }
        }
        System.out.println("Amount Coins");
        System.out.print(coinstring);
    }
    public static int coincount(int amount, int divisor)
    {
        int coins = 0;
        coins = amount / divisor;
        return coins;
    }
    public static String stringcalc(int number)
    {
        String coinstring = "";
        if (number > 1)
        {
            coinstring = String.valueOf(number) + "*";
        }
        return coinstring;
    }
    public static String maincount(int money)
    {
        String coinstring = "";
        mainloop:
        while (money != 0)
        {
            int amount = money;
            int twopound = 0;
            int onepound = 0;
            int fiftyp = 0;
            int twentyp = 0;
            int tenp = 0;
            int fivep = 0;
            int twop = 0;
            int onep = 0;
            String firstspace = "";
            String coincoins = "coins";
            if (money>0 && money<501)
            {
                twopound = coincount(money,200);
                money = (money - (200 * twopound));
                if (money!=0)
                {
                    onepound = coincount(money,100);
                    money = (money - (100 * onepound));
                    if (money!=0)
                    {
                        fiftyp = coincount(money,50);
                        money = (money - (50 * fiftyp));
                        if (money!=0)
                        {
                            twentyp = coincount(money,20);
                            money = (money - (20 * twentyp));
                            if (money!=0)
                            {
                                tenp = coincount(money,10);
                                money = (money - (10 * tenp));
                                if (money!=0)
                                {
                                    fivep = coincount(money,5);
                                    money = (money - (5 * fivep));
                                    if (money!=0)
                                    {
                                        twop = coincount(money,2);
                                        money = (money - (2 * twop));
                                        if (money!=0)
                                        {
                                            onep = coincount(money,1);
                                            money = (money - (1 * onep));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (money == 0)
            {
                break mainloop;
            }
            else
            {
                coinstring = "Invalid amount " + money + "p";
                break mainloop;
            }
            if (twopound!=0)
            {
                coinstring = coinstring + stringcalc(twopound) + "200p ";
            }
            if (onepound!=0)
            {
                coinstring = coinstring + stringcalc(onepound) + "100p ";
            }
            if (fiftyp!=0)
            {
                coinstring = coinstring + stringcalc(fiftyp) + "50p ";
            }
            if (twentyp!=0)
            {
                coinstring = coinstring + stringcalc(twentyp) + "20p ";
            }
            if (tenp!=0)
            {
                coinstring = coinstring + stringcalc(tenp) + "10p ";
            }
            if (fivep!=0)
            {
                coinstring = coinstring + stringcalc(fivep) + "5p ";
            }
            if (twop!=0)
            {
                coinstring = coinstring + stringcalc(twop) + "2p ";
            }
            if (onep!=0)
            {
                coinstring = coinstring + stringcalc(onep) + "1p ";
            }
            int coins = twopound + onepound + fiftyp + twentyp + tenp + fivep + twop + onep;
            if (amount < 100 && amount > 9)
            {
                firstspace = " ";
            }
            else if (amount < 10)
            {
                firstspace = "  ";
            }
            if (coins == 1)
            {
                coincoins = "coin";
            }
            coinstring = firstspace + amount + "p   " + coins + " " + coincoins + " " + coinstring;
        }
        return coinstring;
    }
}
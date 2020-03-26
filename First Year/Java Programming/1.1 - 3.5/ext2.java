class ext2
{
    public static void main(String args [])
    {
        System.out.print("#How long a side?");
        int side = BIO.getInt();
        int side2 = side;
        int length = side;
        int loop = 1;
        boolean nested = false;
        int checkOdd = check(side);
        if (checkOdd == 0 || checkOdd == 1)
        {
            System.out.print("Side invalid [" + side + "]");
            return;
        }
        while (side > 0)
        {
            if (side == length || side == 0)
            {
                firstlast(side);
            }
            else
            {
                for (int i = side-2; i > 0; i--)
                {
                    System.out.print(".");
                }
                System.out.print("*");
                if (side2 - 2 > 3 && check(loop) > 0)
                {
                    side2 -= 2;
                    for (int i = 2; i > 0; i--)
                    {
                        System.out.print(".");
                    }
                    for (int i = side; i > 0; i--)
                    {
                        System.out.print("*");
                    }
                    for (int i = 2; i > 0; i--)
                    {
                        System.out.print(".");
                    }
                    nested = true;
                }
                else if (nested = true)
                {
                    System.out.print("..*");
                    if (side == 0)
                    {
                        for (int i = side; i < length; i++)
                        {
                            System.out.print(".");
                        }
                    }
                    else
                    {
                        for (int i = (length-side-1); i < 0; i++)
                        {
                            System.out.print(".");
                        }
                    }
                }
                System.out.print("*");
                for (int i = side-2; i > 0; i--)
                {
                    System.out.print(".");
                }
            }
            System.out.println("");
            loop++;
            side--;
        }
        
    }
    public static void firstlast (int side)
    {
        for (int i = side-1; i > 0; i--)
        {
            System.out.print(".");
        }
        for (int i = side; i > 0; i--)
        {
            System.out.print("*");
        }
        for (int i = side-1; i > 0; i--)
        {
            System.out.print(".");
        }
    }
    public static int check(int side)
    {
        int isOdd = 0;
        double checkOdd = (double)side;
        if (checkOdd/2 != (int)checkOdd/2 && checkOdd > 1)
        {
            isOdd = 1;
            if (checkOdd >= 5 && checkOdd <= 41)
            {
                isOdd = 2;
            }
        }
        return isOdd;
    }
}
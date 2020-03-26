class main24
{
    public static void main(String args [])
    {
        System.out.print("#How long a side?");
        int side = BIO.getInt();
        int loop = 1;
        int checkOdd = check(side);
        if (checkOdd == 0)
        {
            System.out.print("Side of diamond (" + side + ") must be odd");
        }
        else if (checkOdd == 1)
        {
            System.out.print("Side of diamond (" + side + ") must be between 5 & 41");
        }
        else if (checkOdd == 2)
        {
            side = (side/2)+1;
            int gap = side;
            while (side > 0)
            {
                int k = 1;
                int l = 1;
                for (int i = 1; i < side;i++)
                {
                    System.out.print(".");
                    k++;
                }
                System.out.print("*");
                for (int j = 1; j < loop + 1;j++)
                {
                    l++;
                }
                for (int a = l;a > 2; a--)
                {
                    System.out.print(".");
                    if (a != l)
                    {
                        System.out.print(".");
                    }
                    if (a == 3)
                    {
                        System.out.print("*");
                    }
                }
                for (int b = k;b > 1; b--)
                {
                    System.out.print(".");
                }
                System.out.println("");
                side--;
                loop++;
            }
            while (side < gap-1)
            {
                int k = 1;
                int l = 1;
                for (int i = side; i > -1;i--)
                {
                    System.out.print(".");
                    k++;
                }
                System.out.print("*");
                for (int j = loop; j > 2;j--)
                {
                    l++;
                }
                for (int a = l;a > 1 ; a--)
                {
                    System.out.print(".");
                    if (a != l && a != 2)
                    {
                        System.out.print(".");
                    }
                    if (a == 3)
                    {
                        System.out.print("*");
                    }
                }
                for (int b = k;b > 2; b--)
                {
                    System.out.print(".");
                }
                System.out.println("");
                side++;
                loop--;
            }
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
 
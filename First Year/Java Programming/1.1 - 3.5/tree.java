class tree
{
    public static void main(String args[])
    {
        System.out.print("How many spaces?");
        int spaces = BIO.getInt() + 1;
        int gap = spaces;
        int loop = 1;
        while (spaces > 0)
        {
            for (int i=1; i < spaces; i++)
            {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int j=1; j < loop; j++)
            {
                System.out.print("**");
            }
        System.out.println("");
        spaces = spaces - 1;
        loop++;
        }
        for (int l=1; l<4; l++)
        {
            for ( int k=1; k < gap - 1; k++)
            {
                System.out.print(" ");
            }
            System.out.println("| |");
        }
    }
}
            
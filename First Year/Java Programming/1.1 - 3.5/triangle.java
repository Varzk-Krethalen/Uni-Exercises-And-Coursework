class triangle
{
    public static void main(String args[])
    {
        System.out.print("How tall?");
        int height = BIO.getInt() + 1;
        int gap = height;
        int loop = 1;
        while (height > 1)
        {
            for (int i=1; i < height; i++)
            {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int j=1; j < loop; j++)
            {
                System.out.print("*");
            }
        System.out.println("");
        height = height - 1;
        loop++;
        }
    }
}
            
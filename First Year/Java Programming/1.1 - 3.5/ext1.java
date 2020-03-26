class ext1
{
    public static void main(String args [])
    {
        System.out.print("#How long a side?");
        int stars = BIO.getInt();
        double check = (double)stars;
        if (check/2 != (int)check/2 && check > 1)
        {
            String star = "*";
            for(int j=1; j<stars; j++)
            {
                star = star + "*";
            }
            System.out.println(star);
            int sets = (stars - 2)/2;
            for(int i=1; i<stars-1; i++)
            {
                System.out.print("*");
                for(int k=1; k<sets+1; k++)
                {
                    System.out.print(".+");
                }
                System.out.print(".");
                System.out.println("*");
            }
            System.out.print(star);
        }
        else
        {
            System.out.printf("Size invalid [%d]", stars);
        }
    }
}

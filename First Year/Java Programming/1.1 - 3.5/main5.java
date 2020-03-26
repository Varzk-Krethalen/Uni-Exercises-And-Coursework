class main5
{
    public static void main(String args [])
    {
        System.out.print("#How long a side?");
        int stars = BIO.getInt();
        String star = "*";
        for(int j=1; j<stars; j++)
        {
            star = star + "*";
        }
        System.out.println(star);
        for(int i=1; i<stars-1; i++)
        {
            System.out.print("*");
            for(int k=1; k<stars-1; k++)
            {
                System.out.print(".");
            }
            System.out.println("*");
        }
        System.out.print(star);
    }
}

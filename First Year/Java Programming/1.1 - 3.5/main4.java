class main4
{
    public static void main(String args [])
    {
        System.out.print("#How many stars?");
        int stars = BIO.getInt();
        for(int i=1; i<stars+1; i++)
        {
            String star = "*";
            for(int j=1; j<stars; j++)
            {
                star = star + "*";
            }
            System.out.println(star);
        }
    }
}

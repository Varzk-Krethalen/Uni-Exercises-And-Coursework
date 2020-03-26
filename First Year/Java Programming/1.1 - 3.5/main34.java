class main34
{
    public static void main(String args[])
    {
        int x = 0;
        String input = "";
        boolean pal;
        while (!input.equals("END"))
        {
            System.out.print("#Input, please  ");
            input = BIO.getString();
            pal = true;
            if (!input.equals("END"))
            {
                for (int i = 0; i < (input.length()/2); i++)
                {
                    x = input.length() - i - 1;
                    if (Character.toLowerCase(input.charAt(i)) != Character.toLowerCase(input.charAt(x)))
                    {
                        pal = false;
                        break;
                    }
                }
                if (pal == true)
                {
                    System.out.println(input + "   is a palindrome");
                }
                else 
                {
                    System.out.println(input + "   is not a palindrome");
                }
            }
        }
    }
}

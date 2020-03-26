class main31
{
    public static void main(String args[])
    {
        String input = "";
        int spaces = 0;
        char letter;
        while (!input.equals("END"))
        {
            System.out.print("#Input, please");
            input = BIO.getString();
            spaces = 0;
            if (!input.equals("END"))
            {
                for (int i = 0; i < input.length(); i++)
                {
                    letter = input.charAt(i);
                    if (letter == ' ')
                    {
                        spaces++;
                    }
                }
                System.out.println("[ " + spaces + "] spaces in " + input);
            }
        }
    }
}
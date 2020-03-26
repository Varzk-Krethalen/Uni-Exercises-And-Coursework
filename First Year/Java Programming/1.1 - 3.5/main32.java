class main32
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
                    if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U')
                    {
                        spaces++;
                    }
                }
                System.out.println("[   " + spaces + "] vowels in " + "\"" +  input + "\"");
            }
        }
    }
}
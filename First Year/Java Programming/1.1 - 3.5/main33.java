class main33
{
    public static void main(String args[])
    {
        String input = "";
        boolean asc = true;
        char temp1;
        char temp2;
        while (!input.equals("END"))
        {
            System.out.print("#Input, please  ");
            input = BIO.getString();
            asc = true;
            if (!input.equals("END"))
            {
                for (int i = 0; i < (input.length()-1); i++)
                {
                    temp1 = input.charAt(i);
                    temp2 = input.charAt(i+1);
                    temp1 = Character.toLowerCase(temp1);
                    temp2 = Character.toLowerCase(temp2);
                    if (temp1 > temp2)
                    {
                        asc = false;
                        break;
                    }
                }
                if (asc == false)
                {
                    System.out.println(input + "   letters not in ascending order");
                }
                else
                {
                    System.out.println(input + "   letters in ascending order");
                }
            }
        }
    }
}
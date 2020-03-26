class main21
{
    public static void main(String args [])
    {
        String name = "Null";
        while (!name.equals("END"))
        {
            System.out.print("#Enter Student Name");
            name = BIO.getString();

            if (!name.equals("END"))
            {
                int mark = 0;
                String grade = "Fail";
                System.out.print("#Enter module mark");
                mark = BIO.getInt();
                if (mark >= 40 )
                {
                    grade = "Pass";
                    System.out.println(name + " " + grade);
                }
                else
                {
                    grade = "Fail";
                    System.out.println(name + " " + grade);
                }
            }
        }
    }
}
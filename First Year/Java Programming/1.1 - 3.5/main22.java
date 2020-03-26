class main22
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
                if (mark >= 70 )
                {
                    grade = "gains a 1st";
                    System.out.println(name + "["+ mark + "]" + grade);
                }
                else if (mark >= 60 )
                {
                    grade = "gains a 2.1";
                    System.out.println(name + "["+ mark + "]" + grade);
                }
                else if (mark >= 50 )
                {
                    grade = "gains a 2.2";
                    System.out.println(name + "["+ mark + "]" + grade);
                }
                else if (mark >= 40 )
                {
                    grade = "gains a 3rd";
                    System.out.println(name + "["+ mark + "]" + grade);
                }
                else
                {
                    grade = "fails";
                    System.out.println(name + "["+ mark + "]" + grade);
                }
            }
        }
    }
}
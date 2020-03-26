class main23
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
                int mark1 = 0;
                int mark2 = 0;
                String grade = "Fail";
                System.out.print("#Enter first mark");
                mark1 = BIO.getInt();
                System.out.print("#Enter first mark");
                mark2 = BIO.getInt();
                double avg = (mark1 + mark2)/2.0;
                if (mark1 >= 30 && mark2 >= 30 && avg >= 40 )
                {
                    grade = "Pass";
                    System.out.println(name + " ["+ mark1 + "," + mark2 + "]  " + avg + " " + grade);
                }
                    else if ( avg >= 40 )
                {
                    grade = "Fail [Threshold]";
                    System.out.println(name + " ["+ mark1 + "," + mark2 + "]  " + avg + " " + grade);
                }
                    else
                {
                    grade = "Fail";
                    System.out.println(name + " ["+ mark1 + "," + mark2 + "]  " + avg + " " + grade);
                }
            }
        }
    }
}
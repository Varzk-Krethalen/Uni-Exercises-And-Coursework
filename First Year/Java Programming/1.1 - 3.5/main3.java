class main3
{
    public static void main(String args [])
    {
        int ex = 0;
        int cw = 0;
        int sn = 1;
        while (sn != 0)
        {
            System.out.print("#Enter SN");
            sn = BIO.getInt();
            if (sn != 0)
            {
                System.out.print("#Enter Cw mark");
                cw = BIO.getInt();
                System.out.print("#Enter Ex mark");
                ex = BIO.getInt();
                double avg = (ex+ cw)/2.0;
                System.out.println("sn=" + sn + " ex=  " + ex + " cw=  " + cw + " mark =  " + avg);
            }
    }
    }
}
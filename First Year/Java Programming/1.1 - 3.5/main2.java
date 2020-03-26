class main2
{
    public static void main(String args [])
    {
        int ex = 0;
        int cw = 0;
        System.out.println("#Please enter coursework mark:");
        cw = BIO.getInt();
        System.out.println("#Please enter exam mark:");
        ex = BIO.getInt();
        double avg = (((double) ex)+ ((double) cw))/2;
        System.out.print("ex = ");
        System.out.print(ex);
        System.out.print(" cw = ");
        System.out.print(cw);
        System.out.print(" mark = ");
        System.out.print(avg);
    }
}
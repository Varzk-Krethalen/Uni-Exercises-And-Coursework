import java.util.ArrayList;
class main35
{
    public static void main(String args[])
    {
        System.out.print("#Length, please");
        int length = BIO.getInt();
        ArrayList<String> lineList = new ArrayList<String>();
        String input = "";
        while (!input.equals("END"))
        {
            System.out.print("#Lines, please");
            input = BIO.getString();
            if (!input.equals("END"))
            {
                lineList.add(input.replace(' ', '.'));
            }
        }
        for (int i = 0; i < lineList.size();i++)
        {
            lineList.set(i, lineCheck(lineList.get(i)));
            lineList.set(i, addFiller(lineList.get(i), length));
            System.out.println(lineList.get(i));
        }
    }
    public static String addFiller(String line, int length)
    {
        while (line.length() < length)
        {
            for (int i = 1; i < line.length(); i++)
            {
                char temp1 = line.charAt(i);
                char temp2 = line.charAt(i-1);
                if (temp1 == '.' && temp2 != '.' && line.length() < length )
                {
                    line = new StringBuilder(line).insert(i, ".").toString();
                }
            }
        }
        return line;
    }
    public static String lineCheck(String line)
    {
        int count = line.length() - line.replace(".", "").length();
        if (count == 0)
        {
            line = line + ".";
        }
        return line;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PhoneNumbers {
	
	// NB: backslashes are already "special" in java strings, so they need to be escaped twice in
	// regexes. Thus, the regex '\d' becomes '\\d' in Java. See 
        // http://docs.oracle.com/javase/tutorial/essential/regex/ for details.

	private static final String prompt = "Enter a string or press return to end: ";
	private static final String localNumberRegex = "[1-9][0-9]{5}";
	private static final String areaCodeRegex = "^((\\(01[0-9]{3}\\)|01[0-9]{3}) ?|\\+441[0-9]{3})?";
	private static final String phoneNumberRegex =  areaCodeRegex + localNumberRegex;
	private static final String regexOr =  "|";
	private static final String dayRegex =  "(0[1-9]|[1-2][0-9]|3[0-1])";
	private static final String monthRegex =  "(0[1-9]|1[0-2])";
	private static final String monthWordRegex =  "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
	private static final String yearRegex =  "[0-9]{4}";
	private static final String dash =  "-";
	private static final String slash =  "\\/";
	private static final String dashDateRegex =  dayRegex + dash + monthWordRegex + dash + yearRegex;
	private static final String dayMonthYearRegex = dayRegex + slash + monthRegex + slash + yearRegex;
	private static final String monthDayYearRegex = monthRegex + slash + dayRegex + slash + yearRegex;
	private static final String dateRegex =  dashDateRegex + regexOr + dayMonthYearRegex + regexOr + monthDayYearRegex;
	private static final String phoneNumberType = "UK landline number: ";
	private static final String dateType = "date: ";
	private static final int type = 1; // 0 for phonenumber, 1 for date

	public static void main(String[] args) {
	    System.out.print(prompt);
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	    String input;
	    String currentRegex = type == 0 ? phoneNumberRegex : dateRegex;
	    String currentType = type == 0 ? phoneNumberType : dateType;
		try {
			input = bufferedReader.readLine().trim();
			while(!input.equals("")) {
				if(input.matches(currentRegex)) {
					if(type == 1)
					{
						if (input.matches(dayMonthYearRegex))
						{
							System.out.printf("%s is a valid UK %s.\n", input, currentType);
						}
						else if (input.matches(monthDayYearRegex))
						{
							System.out.printf("%s is a valid US %s.\n", input, currentType);
						}
						else
						{
							System.out.printf("%s is a valid %s.\n", input, currentType);
						}
					}
					else
					{
						System.out.printf("%s is a valid %s.\n", input, currentType);
					}
				} else {
					System.out.printf("%s is not a valid %s.\n", input, currentType);
				}
				System.out.print(prompt);
				input = bufferedReader.readLine().trim();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

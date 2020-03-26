package src.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import src.backend.Risk.level;
import src.backend.Risk.type;

public class calc {
	/*
	 * Using https://www.gov.uk/government/uploads/system/uploads/attachment_data/file/685509/NS_Table_3_2_1516.xlsx
	 * as source for age range wages.
	 */
	
	/*
	 * This is the main function, but mostly serves to ask the questions to the user, taking input.
	 * It then uses a couple of conditionals to output the result of the system.
	 * I have used bigDecimal as it is generally one of the recommended types to use when dealing with
	 * money, rather than imprecise floats and ints.
	 */
	public static void main(String args[]) {
		
		Scanner scanner = new Scanner(System.in);		//used to read input from console.
		
		ArrayList<Risk> risks = new ArrayList<Risk>();	//holds the list of risks
		
		BigDecimal salary = BigDecimal.ZERO;
		BigDecimal secondSalary = BigDecimal.ZERO;
		BigDecimal spouseSalary = BigDecimal.ZERO;
		BigDecimal max = BigDecimal.ZERO;
		BigDecimal creditDebts = BigDecimal.ZERO;
		BigDecimal outgoings = BigDecimal.ZERO;
		String firstName = "";
		String lastName= "";
		String temp = "";
		int jobMonths = 0;
		int age = 0;

		boolean spouse = false;
		boolean firstTime = true;
		boolean secondJob = false;
		
		System.out.print("What is your first name?");
		firstName = scanner.nextLine();
		
		System.out.print("What is your last name?");
		lastName = scanner.nextLine();
		
		System.out.print("How old are you?");
		age = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("What is your yearly primary salary?");
		salary = scanner.nextBigDecimal();
		scanner.nextLine();
		
		System.out.print("Do you have a second job? (Y/N)");
		temp = scanner.nextLine();
		secondJob = (temp.equals("Y") || temp.equals("y"))  ? true : false;
		
		if (secondJob) {
			System.out.print("What is your yearly secondary salary?");
			secondSalary = scanner.nextBigDecimal();
			scanner.nextLine();
		}
				
		System.out.print("Are you a first time buyer? (Y/N)");
		temp = scanner.nextLine();
		firstTime = (temp.equals("Y") || temp.equals("y")) ? true : false;
		
		System.out.print("Do you have a spouse? (Y/N)");
		temp = scanner.nextLine();
		spouse = (temp.equals("Y") || temp.equals("y"))  ? true : false;
		
		if (spouse) {
			System.out.print("What is your spouse's yearly primary salary?");
			spouseSalary = scanner.nextBigDecimal();
			scanner.nextLine();
			
		}
		
		System.out.print("How many months have you been at your job?");
		jobMonths= scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("How much credit card debt do you have, if any?");
		creditDebts = scanner.nextBigDecimal();
		scanner.nextLine();
		
		System.out.print("What are your yearly outgoings?");
		outgoings = scanner.nextBigDecimal();
		scanner.nextLine();
		
		risks = checkRisks(firstTime, secondJob, secondSalary, creditDebts, age, outgoings, salary, jobMonths, risks);
		max = calculateLoan(salary, spouseSalary);		//these first check for any risks based on the given info, then calculates the max loan
		
		if(external.checkMidHigh(risks)) {				//if there are any mid or high risks, run a background check.
			risks = creditAndCounty(firstName, lastName, risks);
		}
		
		if (risks.isEmpty()) {							//if there are no risks, return maximum loan.
			System.out.print("Maximum loan amount: " + max);
		}
		else if (!external.checkLevels(risks)) {		//if there are not two+ medium or any high risks, return maximum loan.
			System.out.println("Maximum loan amount: " + max);
			System.out.print("No high level risks");
		}
		else {											//otherwise, refer the application externally.
			System.out.println("REFERRED:");
			external.refer(risks, max);
			
		}
		
		scanner.close();
	}
	
	
	public static ArrayList<Risk> checkRisks(boolean firstTime, boolean secondJob, BigDecimal secondSalary, BigDecimal creditDebts, int age, BigDecimal outgoings, BigDecimal salary, int jobMonths, ArrayList<Risk> risks) {	
		String temp;
		Risk newRisk;
		
		if (firstTime && (creditDebts.compareTo(new BigDecimal(1000))) == 1)  {				//check for new buyers in debt.
			newRisk = new Risk();
			temp = "First time buyer with debt of " + creditDebts;
			newRisk.set(level.MID, type.DEBT, temp);
			risks.add(newRisk);
		}
		
		if (age < 26 && jobMonths < 19 && salary.compareTo(new BigDecimal(28200)) == 1) {	//check for high fliers.
			newRisk = new Risk();
			temp = salary + " at " + age + " with " + jobMonths + " worked";
			newRisk.set(level.HIGH, type.HIGHFLYER, temp);
			risks.add(newRisk);
		}
		
		if (age > 18 && (outgoings.compareTo(new BigDecimal(2000)) == 0)) {					//check for overly low outgoings.
			newRisk = new Risk();
			temp = outgoings + " out at " + age + " with " + jobMonths + " worked";
			newRisk.set(level.MID, type.OUTGOINGS, temp);
			risks.add(newRisk);
		}
		
		if (secondJob) {																	//check for a second job
			newRisk = new Risk();
			temp = "Two jobs,primary salary " + salary + " and secondary salary " + secondSalary;
			newRisk.set(level.LOW, type.TWOJOB, temp);
			risks.add(newRisk);
		}
		
		return risks;
	}	
	
	
	/*
	 * Runs a quick calculation to find the maximum loan an applicant can have.
	 */
	public static BigDecimal calculateLoan(BigDecimal salary, BigDecimal spouseSalary) {
		BigDecimal max = BigDecimal.ZERO;
		BigDecimal three = new BigDecimal(3);
		max = salary.multiply(three);
		max = max.add(spouseSalary);
		return max;
	}
	
	
	/*
	 * Searches through a text file to find any existing records under their name, and if records exist, add
	 * them to the list of risks, then return that list.
	 */
	public static ArrayList<Risk> creditAndCounty(String firstName, String lastName, ArrayList<Risk> risks) {
		String nameSearch = lastName + ", " + firstName + " ";
		File file = new File("src\\backend\\creditCountyInfo.txt");
		Scanner scanner;
		ArrayList<String> lineList = new ArrayList<>();
		String[] parts;
		Risk newRisk;
		
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()){
			    lineList.add(scanner.nextLine()); 
			}
			
			for (String line : lineList ) {
				parts = line.split("-");

				if (parts[0].equals(nameSearch)) {
					newRisk = new Risk();
					switch(parts[1]) {
					case " CCJ " :
						newRisk.riskType = Risk.type.CCJ;
						newRisk.riskLevel = Risk.level.HIGH;
						newRisk.details = "Previous CCJ on " + parts[2];
						break;
					case " DEBT " :
						newRisk.riskType = Risk.type.UNDECLARED;
						if (parts[2].length() > 3) {
							newRisk.riskLevel = Risk.level.HIGH;					
						}
						else {
							newRisk.riskLevel = Risk.level.MID;
						}
						newRisk.details = "Undeclared debt of " + parts[2];
						break;
					case " DEFAULT " :
						newRisk.riskType = Risk.type.DEFAULT;
						newRisk.riskLevel = Risk.level.HIGH;
						newRisk.details = "Previous debt default on " + parts[2];
						break;
					case " BANKRUPT " :
						newRisk.riskType = Risk.type.BANKRUPT;
						newRisk.riskLevel = Risk.level.HIGH;
						newRisk.details = "Previous bankruptcy on " + parts[2];
						break;
					}
					risks.add(newRisk);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return risks;
	}
}

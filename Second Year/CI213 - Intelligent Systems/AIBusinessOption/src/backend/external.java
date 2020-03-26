package src.backend;

import java.math.BigDecimal;
import java.util.ArrayList;

public class external {
	

	/*
	 * Assume it would add to a referral list or somesuch in a full system.
	 * As it is, just creates a String of the risks for that person, 
	 * and the maximum loan they can be allowed if those risks are
	 * acceptable.
	 * Currently just prints it.
	 */
	public static void refer(ArrayList<Risk> risks, BigDecimal max) {
		String fullDetails = "";
		String line;
		for (Risk x : risks) {
			line = x.riskLevel.toString() + ", " + x.riskType.toString() +
					", " + x.details + '\n';
			fullDetails += line;
		}
		System.out.print(fullDetails + '\n' + "Maximum loan of: " + max);
		fullDetails = "";
	}
	
	/* 
	 * Checks through the list of risks, and if there are any high
	 * risks or more than 1 medium, returns true.
	 */
	public static boolean checkLevels(ArrayList<Risk> risks) {
		int midRisk = 0;
		int highRisk = 0;
		for (Risk x : risks) {
			if (x.riskLevel.equals(Risk.level.HIGH)) {highRisk++;}
			if (x.riskLevel.equals(Risk.level.MID)) {midRisk++;}
		}
		return (midRisk > 1 || highRisk > 0) ? true : false;
	}
	
	/*
	 * Returns true if there are any medium or high risks.
	 */
	public static boolean checkMidHigh(ArrayList<Risk> risks) {
		for (Risk x : risks) {
			if (x.riskLevel.equals(Risk.level.MID) || x.riskLevel.equals(Risk.level.HIGH)) {
				return true;
			}
		}
		return false;
	}
}
	

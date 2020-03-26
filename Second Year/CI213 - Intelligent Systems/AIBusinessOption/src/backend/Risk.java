package src.backend;

public class Risk {
	
	/*
	 * Creates a Risk object, with a type, a risk level, and a
	 * string of the details of that risk - e.g. applicant age.
	 */
	
	public enum level{LOW, MID, HIGH};
	public enum type{DEBT, HIGHFLYER, OUTGOINGS, UNDECLARED,
		DEFAULT, BANKRUPT, CCJ, TWOJOB};
		
	public level riskLevel;
	public type riskType;
	public String details;
	
	/*
	 * Sets the information for any created risk.
	 */
	public void set(level x, type y, String z) {
		riskLevel = x;
		riskType = y;
		details = z;
	}
	
}

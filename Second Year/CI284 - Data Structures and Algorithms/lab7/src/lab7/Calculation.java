package lab7;

public class Calculation {
	public int mult(int n, int times) {
		return mult(n, times, 0);
	}
	public int mult(int n, int times, int current) {
		return (times <= 0) ? current: mult(n, --times, current += n);
	}
	
	
	public int pow(int n, int power) {
		if (n == 0 && power != 0) {return 0;}
		return (power == 0) ? 1: pow(n, power, n);
	}
	public int pow(int n, int power, int current) {
		return (power <= 1) ? current: pow(n, --power, mult(current, n));
	}
	

	public int pow2(int n, int power) {
		if (n == 0 && power != 0) {return 0;}
		return (power == 0) ? 1: pow(n, power, n);
	}
	public int pow2(int n, int power, int current) {
		int odd = ((n % 2) == 0) ? 1 : n; 
		return (power <= 1) ? current: mult(odd, mult(pow2(n/2, --power, mult(current, n/2)), pow2(n/2, --power, mult(current, n/2))));
	}
}

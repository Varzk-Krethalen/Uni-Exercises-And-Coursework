package lab7;

import static org.junit.Assert.*;

import org.junit.*;

import lab7.Calculation;

import java.util.Random;

public class RecursiveTest {
	private Calculation myMath = new Calculation();
	private int a;
	private int b;

	@Before
	public void setUp() {
	}

	@Test
	public void testMult() {
		System.out.println("Mult");
		newValues();
		int expected = a * b;
		int real = myMath.mult(a, b);
		System.out.printf("%d x %d = %d%n", a, b, real);
		assertEquals(expected, real);
	}

	@Test
	public void testPowerOfStyle1() {
		System.out.println("Power of Style 1");
		newValues();
		int expected = (int) Math.pow(a, b);
		int real = myMath.pow(a, b);
		System.out.printf("%d ^ %d = %d%n", a, b, real);
		assertEquals(expected, real);
	}

	@Test
	public void testPowerOfStyle2() {
		System.out.println("Power of Style 2");
		newValues();
		int expected = (int) Math.pow(a, b);
		int real = myMath.pow2(a, b);
		System.out.printf("%d ^ %d = %d%n", a, b, real);
		assertEquals(expected, real);
	}

	public void newValues() {
		Random r = new Random();
		this.a = r.nextInt(10);
		this.b = r.nextInt(10);
	}

}
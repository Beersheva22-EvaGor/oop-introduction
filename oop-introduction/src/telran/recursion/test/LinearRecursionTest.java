package telran.recursion.test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.LinearRecursion.*;

import org.junit.jupiter.api.Test;

public class LinearRecursionTest {

	@Test
	void factorialTest() {
		assertEquals(24, factorial(4));
		assertEquals(24 * 5 * 6, factorial(6));
		assertEquals(24, factorial(-4));
	}

	@Test
	void powerTest() {
		assertEquals(1, power(1000, 0));
		assertThrowsExactly(IllegalArgumentException.class, () -> power(1000, -1));
		assertEquals(1000, power(10, 3));
		assertEquals(-1000, power(-10, 3));
	}

	@Test
	void sumTest() {
		int ar[] = { 1, 2, 3, 4, 5, 6 };
		assertEquals(21, sum(ar));
		assertEquals(0, sum(new int[0]));
	}

	@Test
	void reverseTest() {
		int ar[] = { 1, 2, 3, 4, 5, 6 };
		int expected[] = { 6, 5, 4, 3, 2, 1 };
		ar = reverse(ar);
		assertArrayEquals(expected, ar);

		int ar1[] = { 1, 2, 3, 4, 5, 6, 7};
		int expected1[] = {7, 6, 5, 4, 3, 2, 1 };
		ar1 = reverse(ar1);
		assertArrayEquals(expected1, ar1);
	}
	
	@Test
	void squareTest() {
		assertEquals(0, square(0));
		assertEquals(25, square(-5));
		assertEquals(25, square(5));
		assertEquals(81, square(9));
	}

	@Test
	void isSubstringTest() {
		String string = "hello world";
		String substr1 = "hello";
		String substr2 = "world";
		
		assertTrue(isSubstring(string, substr1));
		assertTrue(isSubstring(string, substr2));
		
		assertFalse(isSubstring(string, "no"));
	}
	
	@Test
	void compareTest() {
		char[] stringAr = {'h', 'e', 'l', 'l', 'o'};
		char[] substrAr = { 'e', 'l'};
		
		assertTrue(compare(stringAr, substrAr, 0));
		assertFalse(compare(stringAr, new char[]{'b'}, 0));
	}
}
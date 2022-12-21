package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.EvenOddComparator;
import telran.util.MyArrays;
import telran.util.StringsLengthComparator;

class MyArraysTest {

	@Test
	@Disabled
	void sortTest() {
		String[] strings = { "abcd", "lmn", "zz" };
		String[] expected = { "zz", "lmn", "abcd" };

		MyArrays.sort(strings, new StringsLengthComparator());
		assertArrayEquals(expected, strings);

	}
	
	@Test
	void evenOddTest() {
		Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7 };
		Integer expected[] = { -8, 2, 10, 100, 47, 13, 7 };
		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);

	}
	
	@Test
	void binarySearchTest() {
		Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7 };
		var compInt = new EvenOddComparator();
		MyArrays.sort(numbers, compInt);
		assertEquals(0, MyArrays.binarySearchObject(numbers, -8, compInt));
		assertEquals(2, MyArrays.binarySearchObject(numbers, 10, compInt));
		assertEquals(-8, MyArrays.binarySearchObject(numbers, 1, compInt));	// not in array
		assertEquals(-4, MyArrays.binarySearchObject(numbers, 12, compInt));	// not in array
		assertEquals(-6, MyArrays.binarySearchObject(numbers, 45, compInt));	// not in array
		
		String[] strings = { "abcd", "lmn", "zz" };
		MyArrays.sort(strings, new StringsLengthComparator());
		var compStr = new StringsLengthComparator();
		assertEquals(0, MyArrays.binarySearchObject(strings, "zz", compStr));
		assertEquals(2, MyArrays.binarySearchObject(strings, "abcd", compStr));
		assertEquals(-1, MyArrays.binarySearchObject(strings, "a", compStr));	// not in array
		assertEquals(-4, MyArrays.binarySearchObject(strings, "12345", compStr));	// not in array
	}
}

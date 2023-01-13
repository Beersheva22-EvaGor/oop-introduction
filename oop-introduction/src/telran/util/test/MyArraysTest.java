package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.EvenOddComparator;
import telran.util.MyArrays;

import static telran.util.MyArrays.*;
import telran.util.StringsLengthComparator;

class MyArraysTest {

	private static final int N_RUNS = 10000;
	private static final int N_NUMBERS = 10000;
	Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7 };
	String[] strings = { "abcd", "lmn", "zz", "fghwm" };

	Comparator<Integer> evenOddComparator = this :: evenOddCompare;

	@Test
	@Disabled
	void sortTest() {
		String[] strings = { "abcd", "lmn", "zz" };
		String[] expected = { "zz", "lmn", "abcd" };

		sort(strings, new StringsLengthComparator());
		assertArrayEquals(expected, strings);

	}

	@Test
	@Disabled
	void evenOddTest() {
		Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7 };
		Integer expected[] = { -8, 2, 10, 100, 47, 13, 7 };
		sort(numbers, evenOddComparator);
		assertArrayEquals(expected, numbers);

	}

	@Test
	@Disabled
	void binarySearchTest() {
		Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7 };
		var compInt = new EvenOddComparator();
		sort(numbers, compInt);
		assertEquals(0, binarySearchObject(numbers, -8, compInt));
		assertEquals(2, binarySearchObject(numbers, 10, compInt));
		assertEquals(-8, binarySearchObject(numbers, 1, compInt)); // not in array
		assertEquals(-4, binarySearchObject(numbers, 12, compInt)); // not in array
		assertEquals(-6, binarySearchObject(numbers, 45, compInt)); // not in array

		String[] strings = { "abcd", "lmn", "zz" };
		sort(strings, new StringsLengthComparator());
		var compStr = new StringsLengthComparator();
		assertEquals(0, binarySearchObject(strings, "zz", compStr));
		assertEquals(2, binarySearchObject(strings, "abcd", compStr));
		assertEquals(-1, binarySearchObject(strings, "a", compStr)); // not in array
		assertEquals(-4, binarySearchObject(strings, "12345", compStr)); // not in array
	}

	@Test
	@Disabled
	void filterTest() {
		int divider = 2;
		String subStr = "m";
		Predicate<Integer> predEven = t -> t % divider == 0;
		Predicate<String> predSubstr = s -> s.contains(subStr);
		String expectedStr[] = { "lmn", "fghwm" };
		Integer expectedNumbers[] = { 2, -8, 100, 10 };

		assertArrayEquals(expectedStr, filter(strings, predSubstr));
		assertArrayEquals(expectedNumbers, filter(numbers, predEven));
	}

	@Test
	void removeIfTest() {
		int divider = 2;
		String subStr = "m";
		Predicate<Integer> predEven = t -> t % divider == 0;
		Predicate<String> predSubstr = s -> s.contains(subStr);
		Integer expectedNumbers[] = { 13, 47, 7 };
		String expectedStr[] =  { "abcd", "zz" };
		
		assertArrayEquals(expectedStr, removeIf(strings, predSubstr));
		assertArrayEquals(expectedNumbers, removeIf(numbers, predEven));
	}
	
	@Test
	void removeRepeatedTest() {
		Integer[] numbersRepeatedValues = { 13, 13, 2, -8, -8, 47, 100, 100, 100, 10, 7, 7 , 13};
		String[] stringsRepeatedValues = { "abcd", "abcd", "lmn", "zz","zz", "fghwm", "abcd", "zz" };		
		
		assertArrayEquals(numbers, removeRepeated(numbersRepeatedValues));
		assertArrayEquals(strings, removeRepeated(stringsRepeatedValues));

	}
	
	@Test
	void containsTest() {
		Integer numbers[] = { null, 13, 2, -8, 47, 100, 10, 7 };
		String[] strings = { null, "abcd", "lmn", "zz", "fghwm" };
		
		assertTrue(contains(numbers, 13));
		assertTrue(contains(numbers, -8));
		assertTrue(contains(numbers, 7));
		assertTrue(contains(numbers, null));
		
		assertTrue(contains(strings, "abcd"));
		assertTrue(contains(strings, "zz"));
		assertTrue(contains(strings, "fghwm"));
		assertTrue(contains(strings, null));
		
		assertFalse(contains(numbers, 1000));
		assertFalse(contains(numbers, "-8"));
		
		assertFalse(contains(strings, "-8"));
		assertFalse(contains(strings, "abc"));
		assertFalse(contains(strings, "abcde"));		
	}
	
	@Test
	void joinFunctionalTest() {
		String expected = "13,2,-8,47,100,10,7";
		assertEquals(expected,MyArrays.join(numbers, ",") );
	}
	
	@Test
	@Disabled
	void joinPerformanceTest() {
		Integer largeArray[]= getLargeNumbersArray();
		for (int i = 0; i < N_RUNS; i++) {
			MyArrays.join(largeArray, ",");
		}
	}
	
	private Integer[] getLargeNumbersArray() {
		Integer[] res = new Integer[N_NUMBERS];
		Arrays.fill(res, 1000);
		return res;
	}

	int evenOddCompare(Integer o1, Integer o2){
		int res = Math.abs(o1) % 2 - Math.abs(o2) % 2;
	if (res == 0) {
		res = o1 % 2 != 0 ? Integer.compare(o2, o1) : Integer.compare(o1, o2);
	}
	return res;
	}
}

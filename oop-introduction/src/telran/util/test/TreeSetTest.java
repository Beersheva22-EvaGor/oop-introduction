package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.TreeSet;

class TreeSetTest extends SetTest {
	@Override
	@BeforeEach
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
	}

	@Override
	@Test
	void testIterator() {
		Iterator<Integer> it = collection.iterator();
		Integer[] clone = new Integer[numbers.length];
		Arrays.fill(clone, null);
		int i = 0;
		while (it.hasNext()) {
			clone[i++] = it.next();			
		}
		Arrays.sort(numbers);
		assertArrayEquals(numbers, clone);
	}

	@Test
	void testContains() {
		for (int i = 0; i<numbers.length; i++) {
			assertTrue(collection.contains(numbers[i]));
		}
	}
	
	@Override
	@Test
	void testAdd() {
		super.testAdd();
		assertFalse(collection.add(numbers[0]));
	}
	
	@Test
	void testAddition(){
		numbers = new Integer[] {10, -50, 100, -70, -30, -100, -60, 30, 150};
		collection = new TreeSet();
		for (var el: numbers)
			collection.add(el);
			
		Iterator<Integer> it = collection.iterator();
		Integer[] clone = new Integer[numbers.length];
		Arrays.fill(clone, null);
		int i = 0;
		while (it.hasNext()) {
			clone[i++] = it.next();			
		}
		for (var el: clone)
			System.out.print(el + " ");
	}
}

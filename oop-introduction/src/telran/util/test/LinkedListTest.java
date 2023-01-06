package telran.util.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import telran.util.*;

class LinkedListTest extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}
	
	@Test
	void testLoopWrongIndexes() {
		assertFalse(((LinkedList<Integer>)collection).hasLoop());
		assertThrows(IllegalArgumentException.class, ()->((LinkedList<Integer>)collection).setNext(1, 2));
	}

	@Test
	private void testLoopEmptyCollection() {
		collection.removeIf(x-> true);
		((LinkedList<Integer>)collection).setNext(collection.size(), 0);
		assertFalse(((LinkedList<Integer>)collection).hasLoop());
	}

	@Test
	private void testLoopBorders() {
		((LinkedList<Integer>)collection).setNext(collection.size(), 0);
		assertTrue(((LinkedList<Integer>)collection).hasLoop());
	}

	@Test
	private void testLoopSameIndex() {
		((LinkedList<Integer>)collection).setNext(2, 2);
		assertTrue(((LinkedList<Integer>)collection).hasLoop());
	}

	@Test
	private void testLoopDifferentIndexes() {
		((LinkedList<Integer>)collection).setNext(3, 1);
		assertTrue(((LinkedList<Integer>)collection).hasLoop());
	}
	

}
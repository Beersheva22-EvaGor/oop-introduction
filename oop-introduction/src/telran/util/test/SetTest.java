package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public abstract class SetTest extends CollectionTest {
	Set<Integer> set ;
	@BeforeEach
	void setUp() throws Exception {
		set = (Set<Integer>) collection;
		super.setUp();
	}

	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(Integer.MAX_VALUE));
		assertFalse(set.add(Integer.MAX_VALUE));

	}	
	
	
}

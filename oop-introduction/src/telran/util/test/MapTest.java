package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashMap;
import telran.util.Map;
import telran.util.Map.Entry;
import telran.util.Set;

abstract class MapTest {

	Map<Integer, String> map;

	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}

	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(1000));
	}

	@Test
	void getOrDefaultTest() {
		String s = "test";
		assertEquals("One", map.getOrDefault(1, s));
		assertEquals(s, map.getOrDefault(1000, s));
	}

	@Test
	void containsKeyTest() {
		assertTrue(map.containsKey(1));
		assertFalse(map.containsKey(1000));
	}
	@Test
	void containsValueTest() {
		assertTrue(map.containsValue("Three"));
		assertFalse(map.containsValue("NULL"));
	}
	
	
	@Test
	void putTest() {
		assertEquals("One", map.put(1, "אחד"));
		assertEquals("אחד", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));

	}

	@Test
	void putIfAbsentTest() {
		assertNull(map.putIfAbsent(5, "Five"));
		assertEquals("One", map.putIfAbsent(1, "אחד"));
		assertEquals("One", map.get(1));
	}
	
	@Test
	void valuesTest() {
		map.values().forEach(el ->assertTrue(map.containsValue(el)));
	}

	@Test
	void entrySetNContainsTest() {
		Set<Entry<Integer, String>> set = map.entrySet();
		set.forEach(el -> assertTrue(map.containsKey(el.getKey())));
	}

	@Test
	void keySetTest() {
		map.keySet().forEach(el ->assertTrue(map.containsKey(el)));
	}
	
	@Test
	void removeTest() throws Exception {
		assertEquals("One", map.remove(1));
		assertFalse(map.containsKey(1));
		assertEquals("Two", map.remove(2));
		assertEquals("Three", map.remove(3));
		assertEquals(0, map.entrySet().size());
	}

}

package telran.util.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class CollectionsTest {

	@Test
	void ArrayListTest() {		
		  ArrayList<String> list = new ArrayList<String>();
		  Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		  Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "1")); 
		  assertTrue(list.isEmpty()); assertEquals(0, list.size());
		  
		  String el0 = "Element 0"; 
		  list.add(el0); 
		  assertEquals(el0, list.get(0));
		  assertFalse(list.isEmpty()); 
		  assertEquals(1, list.size());
		  
		  el0 = "element 0"; list.set(0, el0); assertEquals(el0, list.get(0));
		  assertTrue(list.contains(el0)); assertFalse(list.contains("5321dfdlfjs"));
		  
		  String el1 = "element 1"; list.add(1, el1); assertEquals(el0, list.get(0));
		  assertEquals(el1, list.get(1));
		  
		  Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
		  list.remove(list.size())); 
		  list.remove(1); 
		  assertEquals(1, list.size());
		  
		  String el2 = "element 2"; list.add(el1); 
		  list.add(el2); 
		  assertEquals(0,
		  list.indexOf(el0)); 
		  assertEquals(2, list.indexOf(el2));
		  
		  list.add(el1); list.add(el2); assertEquals(0, list.lastIndexOf(el0));
		  assertEquals(3, list.lastIndexOf(el1)); assertEquals(4,
		  list.lastIndexOf(el2));
		  
		  list.remove(el1); assertEquals(el2, list.get(1));
		  
		  Predicate<String> predSubstr = s -> s.contains(el2);
		  list.removeIf(predSubstr); assertEquals(-1, list.indexOf(el2));
		  
		  // with nulls list.add(1, null); assertEquals(1, list.lastIndexOf(null));
		  list.removeIf(el -> el==null); assertEquals(-1, list.indexOf(null));
		  
		  // toArray: 
		  // less 
		  String someEl = "element 456"; list.add(someEl);
		  list.add(someEl); String[] ar = {"1", "2", "3"}; 
		  String[] arObj = list.toArray(ar); 
		  assertEquals(list.size(), arObj.length);
		  
		  // same length 
		  ar = new String[]{"1", "2", "r", "$"};	  
		  
		  
		  //greater 
		  ar = new String[]{"1", "2","hjk", "$", "ghj", "ghjsd"}; 
		  arObj = list.toArray(ar); 
		  
		  assertNull(arObj[arObj.length-1]);
		  assertNull(arObj[arObj.length-2]); 
		  assertEquals(someEl, arObj[arObj.length-3]);
		  
		  // Specified array is null
		  Assertions.assertThrows(NullPointerException.class, () -> list.toArray(null));
		  
		  //Specified array is a ChildClass whereas listArray is a superClass
		  ArrayList<Object> listObj = new ArrayList<>(); 
		  listObj.add(el0); 
		  arObj = list.toArray(ar);
		 
	}

}

package telran.recursion.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.recursion.MdArray;

class MdArrayTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void forEachTest() {
		MdArray<String> strs = new MdArray<>(new Integer[]{3, 15,7, 2, 10}, "hello"); 
		Integer[] length = {0};
		strs.forEach(str-> length[0] += str.length());
		assertEquals(3*15*7*2*10 * 5, length[0]);
		Integer[] strNum = {0};
		strs.forEach(str-> strNum[0]++);
		assertEquals( 3 * 15 * 7 * 2 * 10, strNum[0]);
		
		
		MdArray<Integer> array = new MdArray<>(new Integer[]{10,5,7}, 50); 
		Integer[] sum = {0}; 
		array.forEach(num -> sum[0]+=num);
		assertEquals(10 * 5 * 7 * 50, sum[0]);
	}
	
	@Test
	void toArrayTest() {
		MdArray<Integer> array = new MdArray<>(new Integer[]{10, 5,7}, 50); 
		Integer[] numbers = array.toArray(new Integer[0]);
		assertEquals(10*5*7, numbers.length);
		Integer[] expected = new Integer[10*5*7];
		Arrays.fill(expected, 50);
		assertArrayEquals(expected, numbers);
		
		MdArray<String> strings = new MdArray<>(new Integer[]{3, 15,7, 2, 10}, "hello"); 
		String[] stringsArray = strings.toArray(new String[0]);
		assertEquals(3*15*7*2*10, stringsArray.length);
	}
	@Test
	void getValueTest() {
		MdArray<Integer> array = new MdArray<>(new Integer[]{10,5,7}, 50);
		assertEquals(50, array.getValue(new Integer[]{3,4,6}));
		assertThrows(IllegalStateException.class, () -> array.getValue(new Integer[]{3,4}));
		assertThrows(NoSuchElementException.class, () -> array.getValue(new Integer[]{3,4,6,0}));
		assertThrows(NoSuchElementException.class, () -> array.getValue(new Integer[]{3,4,6,2,0}));
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.getValue(new Integer[]{3,4,71}) );
	}
	
	@Test
	void setValueTest() {
		MdArray<Integer> array = new MdArray<>(new Integer[]{10,5,7}, 50);
		Integer[] pos = new Integer[]{3,4,6};
		array.setValue(pos,100);
		assertEquals(100, array.getValue(pos));
		assertThrows(IllegalStateException.class, () -> array.setValue(new Integer[]{3,4},100));
		assertThrows(NoSuchElementException.class, () -> array.setValue(new Integer[]{3,4,6,0},100));
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.setValue(new Integer[]{3,4,71},100));
	}
}

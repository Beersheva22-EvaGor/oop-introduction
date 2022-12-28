package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class MyArrays{

	static public <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while (moveMaxAtEnd(objects, length, comparator));
	}

	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean res = false;
		for (int i = 0; i < length; i++) {
			if (comp.compare(objects[i], objects[i + 1]) > 0) {
				swap(objects, i, i + 1);
				res = true;
			}
		}
		return res;
	}

	private static <T> void swap(T[] objects, int i, int j) {
		T tmp = objects[i];
		objects[i] = objects[j];
		objects[j] = tmp;

	}

	public static <T> int binarySearchObject(T[] arraySorted, T searchedValue, Comparator<T> comp) {
		int left = 0;
		int right = arraySorted.length - 1;
		int middle = arraySorted.length / 2;
		while (left <= right && comp.compare(arraySorted[left], searchedValue) != 0) {
			if (comp.compare(searchedValue, arraySorted[middle]) <= 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}
		return left < arraySorted.length && comp.compare(arraySorted[left], searchedValue) == 0 ? left : -left - 1;
	}

	public static <T> T[] filter(T[] array, Predicate<T> predicate) {
		eraseParams(array);
		int counPredicate = getCountPredicate(array, predicate);
		T[] res = Arrays.copyOf(array, counPredicate);
		eraseParams(array);
		int index = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				res[index++] = element;
			}
		}
		return res;
	}

	private static <T> void eraseParams(T[] array) {
		helper = new Object[array.length];
		index[0] = 0;
	}

	private static <T> int getCountPredicate(T[] array, Predicate<T> predicate) {
		int res = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				res++;
			}
		}
		return res;
	}

	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {		
		return filter(array, predicate.negate());
	}

	static Object helper[];
	static int index[] = new int[1];
	
	public static <T> T[] removeRepeated(T[] array) {
		return removeIf(array, el -> {
			boolean res = true;
			if (!contains(helper, el)) {
				helper[index[0]++] = el;
				res = false;
			}
			return res;
		});

	}

	/**
	 * returns true if element equaled to pattern exists in array
	 */
	public static <T> boolean contains(T[] array, T pattern) {
		int index = 0;
		while (index < array.length && !isEqual(array[index], pattern)) {
			index++;
		}
		return index < array.length;
	}

	private static <T> boolean isEqual(T element, T pattern) {
		return element == null ? element == pattern : element.equals(pattern);
	}

	public static <T> String join(T[] array, String delimiter) {
		String res = "";
		if (array.length > 0) {
			StringBuilder builder = new StringBuilder(array[0].toString()); // mutable
			for (int i = 1; i < array.length; i++) {
				builder.append(delimiter).append(array[i]);
			}
			res = builder.toString();
		}
		return res;
	}

}

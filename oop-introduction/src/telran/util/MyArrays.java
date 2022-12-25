package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class MyArrays {

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
		int counPredicate = getCountPredicate(array, predicate);
		T[] res = Arrays.copyOf(array, counPredicate);
		int index = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				res[index++] = element;
			}
		}
		return res;
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

	public static <T> T[] removeRepeated(T[] array) {
		T[] res = Arrays.copyOf(array, array.length);
		int i = 0;
		while (i<res.length) {
			T value = res[i];
			res = removeIf(res, x -> compare(x, value));			
			res = insertAtIndex(res, value, i);
			i++;
		}		
		return res;
	}

	private static <T> T[] insertAtIndex(T[] array, T value, int index) {
		T res[] =  Arrays.copyOf(array, array.length+1);
		res[index] = value;
		System.arraycopy(array, index, res, index + 1, array.length - index);
		return res;
	}

	/*
	 * private static <T> T[] removeEqualsToValue(T[] array, T value) { return
	 * removeIf(array, x -> x.equals(value)); }
	 */
	/**
	 * returns true if element equaled to pattern exists in array
	 */
	public static <T> boolean contains(T[] array, T pattern) {
		boolean res = false;
		int i = 0;
		while (!res && i < array.length) {
			res = compare(array[i], pattern);
			i++;
		}
		return res;
	}

	private static <T> boolean compare(T element, T pattern) {
		return element == null ? element == pattern : element.equals(pattern);
	}
}

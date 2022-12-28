package telran.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {

	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);

	}

	@Override
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		if (index > -1) {
			remove(index);
			size--;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		for (int i = 0; i < size; i++) {
			if (predicate.test((T) array[i])) {
				remove(i);
				res = true;
			}
		}

		return res;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		return indexOf(pattern) > -1;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public T[] toArray(T[] ar) {
		if (ar == null)
			throw new NullPointerException("The specified array is null");
		if (ar.length < size) {
			ar = (T[]) Arrays.copyOf(array, size, ar.getClass());
		} else {
			System.arraycopy(array, 0, ar, 0, size);
			if (ar.length > size)
				Arrays.fill(ar, size, ar.length, null);
		}
		return ar;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, false);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, array.length - index - 1);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, true);
		System.arraycopy(array, index + 1, array, index, array.length - index - 1);
		size--;
		return null;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i = 0;
		while (res < 0 && i < size) {
			if (isEqual(array[i], pattern)) {
				res = i;
			}
			i++;
		}
		return res;
	}

	private static <T> boolean isEqual(T element, T pattern) {
		return element == null ? element == pattern : element.equals(pattern);
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int i = size - 1;
		while (res < 0 && i > -1) {
			if (isEqual(array[i], pattern)) {
				res = i;
			}
			i--;
		}
		return res;
	}

	@Override
	public T get(int index) {
		checkIndex(index, true);
		return (T) array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, true);
		array[index] = element;
	}

	private void checkIndex(int index, boolean isLastIndexIncluded) {
		if (index >= (isLastIndexIncluded ? size : size + 1) || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

}

package telran.util;

import java.util.function.*;
public interface Collection <T> {
	public boolean add(T element);
	boolean remove(T pattern);
	boolean removeIf(Predicate<T> predicate);
	boolean isEmpty();
	int size();
	boolean contains(T pattern);
	
	/**
	 * 
	 * @param ar
	 * @return array containing Collection's elements
	 * if ar refers to memory that is enough for all elements of Collection then new array is not created
	 * otherwise  there'll be created new array.
	 * if array refers to memory that is greater then required for all elements of Collection
	 * then all elements of the Collection are put in the array and rest of memory are filled with nulls
	 */
	T[] toArray(T[] ar);
}

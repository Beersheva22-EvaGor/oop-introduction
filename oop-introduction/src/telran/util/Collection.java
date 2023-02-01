package telran.util;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<T> extends Iterable<T>{
boolean add (T element);
boolean remove(T pattern);
default boolean removeIf(Predicate<T> predicate) {
	Iterator<T> it = iterator();
	int oldSize = size();
	while(it.hasNext()) {
		T obj = it.next();
		if (predicate.test(obj)) {
			it.remove();
		}
	}
	return oldSize > size();
}
boolean isEmpty();
int size();
boolean contains(T pattern);

/*******************************/
/**
 * 
 * @param ar
 * @return array containing elements of a Collection
 * if ar refers to memory that is enough for all elements of Collection then new array is not created,
 * otherwise there will be created new array.
 * if ar refers to memory that is greater than required for all elements of Collection then all elements of the 
 * Collection will be put in the array and rest of memory will be filled by null's 
 */
 
default T[] toArray(T[] ar) {
	int size = size();
	ar = adjustArray(ar, size);
	int index = 0;
	for(T obj: this) {
		ar[index++] = obj;
	}
	return ar;
}
default T[] adjustArray(T[] ar, int size) {
	if (ar.length < size) {
		ar = Arrays.copyOf(ar, size);
	}
	Arrays.fill(ar, size, ar.length, null);
	return ar;
}
default Stream<T> stream() {
	return StreamSupport.stream(this.spliterator(), false);
}
default Stream<T> parallelStream() {
	return StreamSupport.stream(this.spliterator(), true);
}
default T[] toArrayShuffling(T[] array) {
	int size = size();
	T[] ar= this.adjustArray(array, size);		
	var iter = this.iterator();
	
	new Random().ints(0, size).distinct().limit(size()).forEach(n->ar[n]=iter.next());
	
	return ar;
}

}
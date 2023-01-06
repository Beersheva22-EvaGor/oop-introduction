package telran.util;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.*;

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

default T[] toArray(T[] ar) {
	if (ar.length < size()) {
		ar = Arrays.copyOf(ar, size());
	}
	Iterator<T> it = iterator();
	for (int i = 0; i < size(); i++) {
		ar[i] = it.next();
	}
	Arrays.fill(ar, size(), ar.length, null);
	return ar;
}

boolean isEmpty();
int size();
boolean contains(T pattern);

}
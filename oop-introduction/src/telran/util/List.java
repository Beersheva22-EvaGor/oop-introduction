package telran.util;

public interface List<T> extends Collection<T>{
	void add(int index, T element);
	T remove (int index);
	int indexOf(T pattern);	// -1 if there's no such element
	int lastIndexOf(T pattern);
	T get(int index);
	void set(int index, T element);
}

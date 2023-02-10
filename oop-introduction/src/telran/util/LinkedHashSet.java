package telran.util;

import java.util.Iterator;


public class LinkedHashSet<T> extends ArrayList<T> implements Set<T> {
	HashMap<T, Integer> hashTable;	
	
	@Override
	public boolean add(T element) {
		boolean res = false;
		if (size == 0) {
			hashTable = new HashMap<>();
		}
		
		if (hashTable.putIfAbsent(element, size) == null ) {
			res = super.add(element);
		};	
		return res;
	}

	@Override
	public T remove(int index) {
		T res = super.get(index);
		if (hashTable.remove(res) != null)	{
			super.remove(index);
		}
		return res;
	}
	
	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Integer index = hashTable.remove(pattern);
		if (index !=null) {
			super.remove(index);
			res = true;
		}
		return res;
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		return hashTable.containsKey(pattern);
	}


	@Override
	public T get(T pattern) {	
		return contains(pattern) ? null : pattern;
	}

	@Override
	public Iterator<T> iterator() {
		return super.iterator();
	}

}

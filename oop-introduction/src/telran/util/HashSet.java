package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {

	private static final int DEFAULT_TABLESIZE = 16;
	private static final float DEEFAULT_FACTOR = 0.75f;
	private List<T> [] hashTable;
	private float factor;
	
	private class HashSetIterator implements Iterator<T>{
		private int currentIndex=0;
		private int currentNodeIndex = 0;
		List<T> currentList;
		Iterator<T> iteratorList;
		
		public HashSetIterator() {
			if (size>0) {
				nextHashTableElement();
			}
		}
		
		/**
		 * looking for a next element in hashTable that not null
		 */
		private void nextHashTableElement() {
			while (hashTable[currentNodeIndex] == null) {
				currentNodeIndex++;				
			}
			currentList = hashTable[currentNodeIndex];
			iteratorList = currentList.iterator();
		}
		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (!iteratorList.hasNext()) {
				currentNodeIndex++;
				nextHashTableElement();
			}
			currentIndex++;
			return iteratorList.next();
		}
		

		@Override
		public void remove() {
			iteratorList.remove();
			if (currentList.size() == 0) {				
				hashTable[currentNodeIndex] = null;
			} 
			currentIndex--;
			size--;
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashSet(int tableSize, float factor) {
		if (tableSize < 1) {
			throw new IllegalArgumentException("Wrong initial size of Hash Table");
		}
		if (factor < 0.3 || factor>1) {
			throw new IllegalArgumentException("Wrong factor");
		}
		hashTable = new List[tableSize];
		this.factor = factor;
	}
	
	public HashSet() {
		this(DEFAULT_TABLESIZE, DEEFAULT_FACTOR);
	}
	@Override
	public boolean add(T element) {
		if (size >= hashTable.length * factor) {
			// restructuring table
			tableRecreation();
		}
		int index = getHashIndex(element);
		List<T>  list = hashTable[index];
		boolean res = false;
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		if(!list.contains(element)) {	
			res = true;
			list.add(element);
			size++;
		}		
		return res;
	}

	private void tableRecreation() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length*2, factor);
		for (List<T> list: hashTable) {
			if (list != null) {
				for (T obj : list) {
					hashSet.add(obj);
				}
			}
		}
		hashTable = hashSet.hashTable;
	}

	private int getHashIndex(T element) {		
		return Math.abs(element.hashCode()) % hashTable.length;
	}

	@Override
	public boolean remove(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null){
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
				if (hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
			}
		}
		return res;
	}
	
	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = getHashIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

}

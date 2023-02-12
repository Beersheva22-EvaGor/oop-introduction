package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	private class Entry<T>{
		public Entry<T> prev= null;
		public Entry<T> next= null;
		
		public T obj= null;
		
	}
	
	private class LinkedHashSetIterator implements Iterator<T> {
		Entry<T> current = head;
		boolean flNext = false;
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			flNext = true;
			return res;
		}
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			Entry<T> removedNode = current == null ? tail : current.prev;
			removeEntry(removedNode);
			flNext = false;
		}

	}
	
	Entry<T> newEntry;
	Map<T, Entry<T>> hashMap;
	Entry<T> head;
	Entry<T> tail;
	
	private void removeEntry(Entry<T> current) {
		if (current == head) {
			removeHead();
		} else if (current == tail) {
			removeTail();
		} else {
			removeMiddle(current);
		}
		hashMap.remove(current.obj);
		
		size--;
		
	}
	
	@Override
	public boolean add(T element) {
		boolean res = false;
		
		newEntry = new Entry<T>();
		if (size == 0) {
			hashMap = new HashMap<>();
			head = tail = newEntry;
		} 
		newEntry.prev = tail;
		newEntry.obj = element;
		
		res = hashMap.putIfAbsent(element, newEntry)== null ? true : false;
		if (res) {
			tail.next = newEntry;
			tail = newEntry;
			size++;
		}		
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		Entry<T> entry = hashMap.get(pattern);
		if (entry == null) {
			return false;
		}
		if (entry == head) {
			removeHead();
		} else if (entry == tail) {
			removeTail();
		} else {
			removeMiddle(entry);
		}
		hashMap.remove(pattern);
		size--;
		return true;
	}

	private void removeHead() {
		if (head.next == null) {
			head = tail = null;
		} else {
			Entry<T> next = head.next;
			next.prev = null;
			head = next;
		}
	}

	private void removeMiddle(Entry<T> current) {
		Entry<T> prev = current.prev;
		Entry<T> next = current.next;
		prev.next = next;
		next.prev = prev;
		
	}



	private void removeTail() {
		Entry<T> prev = tail.prev;
		prev.next = null;
		tail = prev;
		
	}
	@Override
	public boolean contains(T pattern) {
		return hashMap.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {		
		return hashMap.get(pattern).obj;
	}

}

package telran.util;

import java.util.Iterator;

import telran.util.LinkedList.Node;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	private LinkedList<T> list = new LinkedList<>();
	private HashMap<T, Node<T>> map = new HashMap<>();
	
	private class LinkedHashSetIterator implements Iterator<T> {
		Iterator<T> listIterator = list.iterator();
		T currentObj = null;
		@Override
		public boolean hasNext() {
			
			return listIterator.hasNext();
		}

		@Override
		public T next() {
			currentObj = listIterator.next();
			return currentObj;
		}
		@Override
		public void remove() {
			listIterator.remove();
			map.remove(currentObj);
			size--;
		}
	}
	
	@Override
	public boolean add(T element) {
		boolean res = false;
		if (!map.containsKey(element)) {
			res = true;
			list.add(element);
			map.put(element, list.tail);
			size++;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = map.get(pattern);
		if (node != null) {
			res = true;
			list.removeNode(node);
			map.remove(pattern);
			size--;
			
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		
		return map.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		Node<T> node = map.get(pattern);
		return node == null ? null : node.obj;
	}

}
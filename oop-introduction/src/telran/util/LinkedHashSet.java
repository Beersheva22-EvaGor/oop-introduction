package telran.util;

import java.util.Iterator;
import telran.util.LinkedList.Node;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {

	private class LinkedList4Hash<T> extends LinkedList<T> {
		@Override
		void removeNode(Node<T> current) {
			super.removeNode(current);
			removeObj(current.obj);

		}
	}

	@SuppressWarnings("unchecked")
	private void removeObj(Object obj) {
		hashMap.remove((T) obj);
		size--;
	}

	LinkedList4Hash<T> list;
	Map<T, Node<T>> hashMap;

	@Override
	public boolean add(T element) {
		boolean res = false;
		if (size == 0) {
			list = new LinkedList4Hash<>();
			hashMap = new HashMap<T, LinkedList.Node<T>>();
		}

		if (!hashMap.containsKey(element)) {
			list.add(element);
			hashMap.put(element, list.tail);
			size++;
			res = true;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		if (hashMap.containsKey(pattern)) {
			Node<T> node = hashMap.remove(pattern);
			list.removeNode(node);
			res = true;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		return hashMap.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public T get(T pattern) {
		return hashMap.get(pattern).obj;
	}

}
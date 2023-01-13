package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Set<T> {

	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private class TreeSetIterator<T> implements Iterator<T> {
		Node<T> current;
		int current_iter=0;
		
		public TreeSetIterator(){
			current = findMin((Node<T>) root);
		}
		
		@Override
		public boolean hasNext() {
			return current_iter < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}			
			T res = current.obj;
			if (current.right == null) {
				current = current.parent;
			} else {			
				current = findMin(current.right);
			}
			current_iter++;
			return res;
		}
		
		private Node<T> findMin(Node<T> root) {
			Node<T> current = root;
			if (root != null) {
				while (current.left != null) {
					current = current.left;
				}
			}
			return current;
		}
	}

	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public boolean add(T element) {
		boolean res = true;
		if (root == null) {
			root = new Node<T>(element);
			size++;
		} else {
			Node<T> current = root, parent;
			int compareResult;
			do {
				compareResult = comp.compare(element, current.obj);
				parent = current;
				if (compareResult == 0)
					res = false;
				else
					current = choiceOfMoving(compareResult, current);
			} while (res && current != null);

			if (res) {
				size++;
				current = new Node<T>(element);
				current.parent = parent;
				if (compareResult < 0)
					parent.left = current;
				else
					parent.right = current;
			}
		}
		return res;
	}

	private Node<T> choiceOfMoving(int comareResult, Node<T> current) {
		if (comareResult < 0) {
			current = current.left;
		} else {
			current = current.right;
		}
		return current;
	}

	@Override
	public boolean remove(T pattern) {
		// Not implemented yet
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		Node<T> current = root;
		while (current != null && !res) {
			int compareResult = comp.compare(pattern, current.obj);
			if (compareResult == 0)
				res = true;
			else
				current = choiceOfMoving(compareResult, current);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {

		return new TreeSetIterator<>();
	}

	
}

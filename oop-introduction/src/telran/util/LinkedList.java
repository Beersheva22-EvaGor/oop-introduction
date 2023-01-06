package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flNext = false;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			removeNode(removedNode);
			flNext = false;
		}

	}

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}

		size++;
		return true;
	}

	private void removeNode(Node<T> current) {
		if (current == head) {
			removeHead();
		} else if (current == tail) {
			removeTail();
		} else {
			removeMiddle(current);
		}
		size--;

	}

	private void removeMiddle(Node<T> current) {
		Node<T> prev = current.prev;
		Node<T> next = current.next;
		prev.next = next;
		next.prev = prev;

	}

	private void removeTail() {
		Node<T> prev = tail.prev;
		prev.next = null;
		tail = prev;

	}

	private void removeHead() {
		if (head.next == null) {
			head = tail = null;
		} else {
			Node<T> next = head.next;
			next.prev = null;
			head = next;
		}

	}

	@Override
	public Iterator<T> iterator() {

		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}

	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;

	}

	/************************************************************************************/
	// Comments only for LinkedList task of loop existence
	public void setNext(int index1, int index2) {
		// sets next of element at index1 to element at index2
		compareIndexes(index1, index2);
		Node<T> node2 = getNode(index2);
		Node<T> node1 = getNode(index2, index2, size - 1);
		node1.next = node2;
	}

	private void compareIndexes(int index1, int index2) {
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
	}

	public boolean hasLoop() {
		boolean res = false;
		if (head != null) {
			Node<T> turtoise = head;
			Node<T> hare = head;
			while (!res && hare.next != null && hare != null) {
				turtoise = turtoise.next;
				hare = hare.next.next;
				if (turtoise.hashCode() == hare.hashCode()) {
					res = true;
				}
			}
		}
		return res;
	}

	/*********************************************************************************************/

	private Node<T> getNode(int index) {

		return getNode(index, 0, size - 1);
	}

	private Node<T> getNode(int index, int left, int right) {
		return index < (right + 1 - left) / 2 ? getNodeFromLeft(index, left) : getNodeFromRight(index, right);
	}

	private Node<T> getNodeFromRight(int index, int right) {
		Node<T> current = right == size - 1 ? tail : getNode(index);
		for (int i = right; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index, int left) {
		Node<T> current = left == 0 ? head : getNode(left);
		for (int i = left; i < index; i++) {
			current = current.next;
		}
		return current;
	}
//
//private Node<T> getNode(int index) {
//		
//		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
//	}
//
//	private Node<T> getNodeFromRight(int index) {
//		Node<T> current = tail;
//		for(int i = size - 1; i > index; i--) {
//			current = current.prev;
//		}
//		return current;
//	}
//
//	private Node<T> getNodeFromLeft(int index) {
//		Node<T> current = head;
//		for(int i = 0; i < index; i++) {
//			current = current.next;
//		}
//		return current;
//	}

	private void addHead(T element) {
		Node<T> node = new Node<>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;

	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node<T> removedNode = getNode(index);
		if (removedNode == null) {
			throw new IllegalStateException("removedNode in method remove is null");
		}
		removeNode(removedNode);
		return removedNode.obj;
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		int index = 0;
		while (current != null && !isEqual(current.obj, pattern)) {
			index++;
			current = current.next;
		}
		return current != null ? index : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
		int index = size - 1;
		while (current != null && !isEqual(current.obj, pattern)) {
			index--;
			current = current.prev;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		node.obj = element;

	}

}
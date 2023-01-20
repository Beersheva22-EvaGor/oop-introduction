package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
	static private class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = root;
		Node<T> prev = null;
		boolean flNext = false;

		TreeSetIterator() {
			if (current != null) {
				current = getLeastNode(current);
			}
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prev = current;
			current = getNextCurrent(current);
			flNext = true;
			return prev.obj;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = prev;
			current = removeNode(removedNode);
			flNext = false;
		}

	}

	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	private Node<T> getNextCurrent(Node<T> current) {

		return current.right == null ? getGreaterParent(current) : getLeastNode(current.right);
	}

	private Node<T> getGreaterParent(Node<T> current) {
		while (current.parent != null && current.parent.left != current) {
			current = current.parent;
		}
		return current.parent;
	}

	private Node<T> getLeastNode(Node<T> current) {
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public boolean add(T element) {
		boolean res = false;
		Node<T> parent = getLastNode(element);
		int compRes = 0;
		if (parent == null || (compRes = comp.compare(element, parent.obj)) != 0) {
			res = true;
			size++;
			Node<T> node = new Node<>(element);
			node.parent = parent;
			if (parent == null) {
				root = node;
			} else {
				if (compRes < 0) {
					parent.left = node;
				} else {
					parent.right = node;
				}
			}
		}

		return res;
	}

	private Node<T> getLastNode(T element) {
		return getNode(element, true);
	}

	private Node<T> getNodeOrNull(T element) {
		return getNode(element, false);
	}

	private Node<T> getNode(T element, boolean last) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while (current != null && (compRes = comp.compare(element, current.obj)) != 0) {
			parent = current;
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? (last ? parent : null) : current;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> removedNode = getNodeOrNull(pattern);
		boolean res = false;
		if (removedNode != null) {
			removeNode(removedNode);
			res = true;
		}
		return res;
	}

	private Node<T> removeNode(Node<T> removedNode) {
		Node<T> res = null;
		if (size == 1) {
			// 1 element in set
			root = null;
		} else if (removedNode.left == null && removedNode.right == null) {
			// remove end node
			res = removeEndNode(removedNode);
		} else {
			Node<T> next = getNextCurrent(removedNode);
			if (removedNode == root && next == null ) {
				// remove root with only left successor(s)
				res = removeRootWithLeftTree();
			} else if (next != null ) {
				// there is a successor
				res = swap(removedNode, next);
			} else {
				// next == null
				removeRootWithLeftTree();
			}
		}
		
		size--;
		return res;
	}
	
	private Node<T> removeRootWithLeftTree(){
		root = root.left;
		return root;
	}
	private Node<T> swap(Node<T> removedNode, Node<T> next) {
		Node<T> res = removedNode;
		while (removedNode.right != null) {
			removedNode.obj = next.obj;
			removedNode = next;
			next = getNextCurrent(removedNode);
		}
		// no more successors
		Node<T> temp = removeNodeWithLeftTree(removedNode);
		
		return temp == null? res: temp;
	}

	private Node<T> removeNodeWithLeftTree(Node<T> removedNode) {
		Node<T> res = null;
		if (removedNode.parent == null) {
			// root
			res = removeRootWithLeftTree();
		} else {
			removeEndNode(removedNode);
		}	
		return res;
	}

	private Node<T> removeEndNode(Node<T> removedNode){
		Node<T> parent = removedNode.parent;
		if (parent.left == removedNode) {
			parent.left = null;
		} else {
			parent.right = null;
		}
		return parent;
	}

	@Override
	public boolean contains(T pattern) {
		Node<T> node = getLastNode(pattern);
		return node != null && comp.compare(pattern, node.obj) == 0;
	}

	@Override
	public Iterator<T> iterator() {

		return new TreeSetIterator();
	}

	@Override
	public T floor(T element) {
		if (root == null)
			return null;

		Node<T> current = root;
		int compResult = 0;
		Node<T> result = null;
		while (current != null) {
			if ((compResult = comp.compare(element, current.obj)) > 0) {
				result = current;
				current = current.right;
			} else if (compResult < 0) {
				current = current.left;
			} else {
				result = current;
				current = null;
			}
		}
		return result == null ? null : result.obj;
	}

	@Override
	public T ceiling(T element) {
		if (root == null)
			return null;
		Node<T> current = root;
		int compResult = 0;
		Node<T> result = null;
		while (current != null) {
			if ((compResult = comp.compare(element, current.obj)) > 0) {
				current = current.right;
			} else if (compResult < 0) {
				result = current;
				current = current.left;
			} else {
				result = current;
				current = null;
			}
		}
		
		return result == null ? null : result.obj;
	}

	@Override
	public T first() {
		return getLeastNode(root).obj;
	}

	@Override
	public T last() {
		Node<T> current = root;
		while (current.right != null) {
			current = current.right;
		}
		return current.obj;
	}

}
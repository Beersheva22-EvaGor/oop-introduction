package telran.recursion;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
	private MdArray<T>[] array;
	private T value;
	public MdArray(Integer dimentions[], T value) {
		this(dimentions, 0, value);
	}
	@SuppressWarnings("unchecked")
	public MdArray(Integer[] dimentions, int firstDim, T value) {
		if (firstDim == dimentions.length) {
			this.value = value;
			array = null;
		} else {
			this.value = null;
			array = new MdArray[dimentions[firstDim]];
			for (int i = 0; i < array.length; i++) {
				array[i] = new MdArray<>(dimentions, firstDim + 1, value);
			}
		}
	}
	public void forEach(Consumer<T> cons) {
		forEach(this.array, cons);		
	}
	
	private void forEach(MdArray<T>[] array, Consumer<T> cons) {
		for (var el: array) {
			if (el.array != null) {
				forEach(el.array, cons);
			} else if (el.value != null){
				cons.accept(el.value);
			}
		}
	}
	
	 public T[] toArray(T[] ar) {
		 ArrayList<T> list = new ArrayList<>();
		 forEach(this.array, el -> list.add(el));
		 return list.toArray(ar);
	}
	public T getValue(Integer[] pos) {
		MdArray<T> mdArr = searchElement(pos);
		T res = mdArr.value;
		if (res == null) {
			throw new IllegalStateException();
		}
		return res;
	}
	private MdArray<T> searchElement(Integer[] pos) {
		MdArray<T> mdArr = this;
		for (var p: pos) {
			if (mdArr.array == null) {
				throw new NoSuchElementException();
			}
			mdArr = mdArr.array[p];
		}
		return mdArr;
	}
	public void setValue(Integer[] pos, T value) {
		MdArray<T> mdArr = searchElement(pos);
		if (mdArr.array != null) {
			throw new IllegalStateException();
		}
		mdArr.value = value;
	}
}

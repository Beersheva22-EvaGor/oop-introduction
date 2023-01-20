package telran.recursion;

import java.util.Arrays;

public class LinearRecursion {

	public static long factorial(int n) {
		long res = 1;
		if (n < 0) {
			res = factorial(-n);
		} else if (n < 2) {
			res = 1;
		} else {
			res = n * factorial(n - 1);
		}
		return res;
	}

	public static int power(int base, int pow) {
		if (pow < 0) {
			throw new IllegalArgumentException();
		}
		int res = base>0? base : -base;
		if (pow == 0) {
			res = 1;
		} else {
			res = powIntinsic(base, res, pow);
		}
		return res;
	}

	private static int multiplication(int base, int multiple) {
		int res = 0;
		if (multiple-- > 0) {
			res += base + multiplication(base, multiple);
		}
		return res;
	}

	private static int powIntinsic(int base, int multiple, int pow) {
		if (pow-- > 1) {
			base = multiplication(base, multiple);
			return powIntinsic(base, multiple, pow);
		}
		return base;
	}

	public static long sum(int[] ar) {
		return sum(0, ar);
	}

	private static long sum(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex < ar.length) {
			res = ar[firstIndex] + sum(firstIndex + 1, ar);
		}
		return res;
	}

	public static long square(int x) {
		if (x < 0) {
			x = -x;
		}
		long res = x;
		if (x > 0) {
			res += --x + square(x);
		}
		return res;
	}

	public static int[] reverse(int[] ar) {
		int[] res = Arrays.copyOf(ar, ar.length);
		return swap(res, 0);
	}

	private static int[] swap(int[] ar, int i) {
		int temp = ar[i];
		ar[i] = ar[ar.length - i - 1];
		ar[ar.length - i - 1] = temp;
		if (i < (ar.length - 1) / 2) {
			swap(ar, ++i);
		}
		return ar;
	}

}

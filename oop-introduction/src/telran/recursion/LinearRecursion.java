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
		int res = base > 0 ? base : -base;
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

	public static boolean isSubstring(String string, String substr) {
		boolean res = false;
		char[] stringAr = new char[string.length()];
		toArray(string, stringAr, string.length());
		char[] substrAr = new char[substr.length()];
		toArray(substr, substrAr, substr.length());

		res = compare(stringAr, substrAr, 0);

		return res;

	}

	private static char[] toArray(String string, char[] ar, int counter) {
		if (counter > 0) {
			ar[counter - 1] = string.charAt(counter - 1);
			toArray(string, ar, --counter);
		}
		return ar;
	}

	public static boolean compare(char[] stringAr, char[] substrAr, int index) {
		boolean res = false;
		if (index < stringAr.length - substrAr.length +1 && !res) {
			res = compareChars(stringAr, substrAr, index++, 0);
			if (!res) {
				res = compare(stringAr, substrAr, index);
			}
		}
		return res;
	}

	private static boolean compareChars(char[] stringAr, char[] substrAr, int index, int indexChar) {
		boolean res = true;
		if (indexChar < substrAr.length) {
			if (substrAr[indexChar] != stringAr[index + indexChar]) {
				res = false;
			}
			if (res) {
				compareChars(stringAr, substrAr, index, ++indexChar);
			}
		}
		return res;
	}
}
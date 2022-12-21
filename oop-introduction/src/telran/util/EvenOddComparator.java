package telran.util;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int res = 0;
		if (o1 % 2 == 0 && o2 % 2 == 0) {
			res = o1 - o2;
		} else if (o1 % 2 != 0 && o2 % 2 != 0) {
			res = o2 - o1;
		} else if (o1%2 == 0){
			res = -1;
		} else {
			res = 1;
		}
		return res;
	}

}

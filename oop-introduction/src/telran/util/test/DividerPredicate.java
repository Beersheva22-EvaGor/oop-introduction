package telran.util.test;

import java.util.function.Predicate;

public class DividerPredicate implements Predicate<Integer> {
	int divider;
	@Override
	public boolean test(Integer t) {
		return t % divider ==0;
	}
	public DividerPredicate(int divider) {
		this.divider = divider;
	}

}

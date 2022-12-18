package telran.memory;

public class MemoryOperations {

	public static int getMaxAvailableMemory() {		
		int res = Integer.MAX_VALUE;
		byte ar[];
		int left = 0, right = res;
		int middle = res/2;
		while (left<=right) {
			ar = null;
			try {
				ar = new byte[middle];
				left = middle+1;
			} catch (Throwable e) {
				right = middle - 1;
			}
			middle = left/2 + right/ 2;
		}
		
		return middle;
	}
	
}

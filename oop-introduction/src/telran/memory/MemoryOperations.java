package telran.memory;

public class MemoryOperations {

	public static int getMaxAvailableMemory() {		
		int minMemory = 0;
		int maxMemory = Integer.MAX_VALUE;
		int middle = maxMemory / 2;
		int maxAvailable = 0;
		
		while(minMemory <= maxMemory) {
			try {
				byte ar[] = new byte[middle];
				maxAvailable = middle;
				minMemory = middle + 1;
				
			} catch (Throwable e) {
				maxMemory = middle - 1;
				
			}
			middle = minMemory + (maxMemory - minMemory) / 2;
		}
		return maxAvailable;
	}
	
}

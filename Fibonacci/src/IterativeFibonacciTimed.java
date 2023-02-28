/*
 * Implement the Fibonacci function in an iterative fashion. What’s the runtime efficiency of each?
 * Please use nanosecond. long startTime = System.nanoTime();
 */
public class IterativeFibonacciTimed {
	
	/*
	 * test method to be called between start time and end time in main 
	 */
	
	public void test() {
		int n = 70;
		
		for (int i = 0; i <= n; i++) {
			System.out.println(fibonacci(i));
		}
	}
	private static long fibonacci(int n) {
		/* Base Case if statements to set the first three values to 0 1 1 */
		if (n == 0)
			
			return 0;
		
		if (n == 1 || n == 2)
			
			return 1;
	
		long previous = -1;
		long result = 1;
		
		for (int i = 0; i <= n; i++) {
			
			long sum = result + previous;
			previous = result;
			result = sum;
			
		}
		return result;
	}
	/*
	 * using a new variable of IterativeFibonacciTimed to call the test method between startTime and endTime.
	 * runtime = end - start. print out runtime 
	 */
	public static void main(String[] args) {
		IterativeFibonacciTimed obj = new IterativeFibonacciTimed();
		long startTime = System.nanoTime();
		obj.test();
		long endTime = System.nanoTime();
		long runTime = endTime - startTime;
		System.out.println("Elapsed Time in nano seconds: " + (runTime));
		
	}
}

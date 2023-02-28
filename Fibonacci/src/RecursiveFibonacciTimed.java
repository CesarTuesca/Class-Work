/*
 * Implement the Fibonacci function in a recursive fashion. What’s the runtime efficiency of each?
 * Please use nanosecond. long startTime = System.nanoTime();
 */
public class RecursiveFibonacciTimed {
	
	/* Array to act as cache */
	private static long[] fibonacciCache;
	
	/*
	 * test method to be called between start time and end time in main 
	 */
	public void test() {
	
		int n = 90;
		fibonacciCache = new long[n + 1];
		
		for (int i = 0; i<= n; i++) {
			
			System.out.println(fibonacci(i));
			
		}
		
	}

	private static long fibonacci(int n) {
		
		if (n <= 1) {
			
			return n;
			
		}
		
		if (fibonacciCache[n] != 0) {
			
			return fibonacciCache[n];
			
		}
		
		long nthFibonacciNumber = (fibonacci(n - 1) + fibonacci(n - 2));
		
		fibonacciCache[n] = nthFibonacciNumber;
		
		return nthFibonacciNumber;
	}
	
/*
 * using a new variable of RecursiveFibonacciTimed to call the test method between startTime and endTime.
 * runtime = end - start. print out runtime 
 */
	public static void main(String args[]) {
		RecursiveFibonacciTimed obj = new RecursiveFibonacciTimed();
		long startTime = System.nanoTime();
		obj.test();
		long endTime = System.nanoTime();
		long runTime = endTime - startTime;
		System.out.println("Elapsed Time in nano seconds: " + (runTime));
	}
}
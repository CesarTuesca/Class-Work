
public class IterativeFibonacci {
	public static void main(String[] args) {
		int n = 90;
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
}

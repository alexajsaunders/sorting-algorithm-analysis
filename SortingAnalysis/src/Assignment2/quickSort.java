package Assignment2;

public class quickSort {

/***
 *  Function to implement Quicksort of the subarray from
 * a[low] to a[high]
 * @param a, array to be sorted
 * @param low, starting index	 
 * @param high, ending index
*/
	public static void sort(Comparable[] a, int low, int high) {
		if (low >= high)
			return;
		int j = partition(a, low, high);
		sort(a, low, j-1);
		sort(a, j+1, high);
	}
	
	/* Quicksort the array in ascending order  */
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
	/* partition the subarray a[low...high] by returning an index j
	 * so that a[lo...j-1] < = a[j] <= a[j+1...high] */
	private static int partition(Comparable[] a, int low, int high) {
		int i = low;
		int j = high + 1;
		Comparable v = a[low];
		while (true) {
			
			// fine item on low to swap
			while (less(a[++i], v))
				if (i == high) 
					break;
			
			// find item on high to swap
			while (less(v, a[--j]))
				if (j == low)
					break;
			
			// check if pointers cross
			if (i >= j) break;
			
			exch(a, i, j);
		}
		exch(a, low, j);
		return j;
	}
/***************************************************************
 * Helper sorting functions
 **************************************************************/
	
	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean less(Comparable a, Comparable b) {
		if (a == b) return false;
			return a.compareTo(b) < 0;
	}

	/*****************************************************************
	 * Rearranges so that a[k] holds the kth smallest element in array 
	 * a[0] - a[k-1] are less than a[k] 
	 * a[k+1] - a[n-1] are greater than a[k]
	 * @param a the array
	 * @param k the rank of the key
	 * @return key of the rank k 
	 *****************************************************************/
	public static Comparable select(Comparable[] a, int k) {
		if (k < 0 || k >= a.length) {
			throw new IllegalArgumentException("Element is not within bounds");
		}
		int low = 0;
		int high = a.length - 1;
		while (high > low) {
			int i = partition(a, low, high);
			if (i > k) 
				high = i - 1;
			else if (i < k) 
				low = i + 1;
			else 
				return a[i];
		}
		return a[low];
	}
}

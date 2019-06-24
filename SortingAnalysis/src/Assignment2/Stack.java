package Assignment2;

public class Stack {

	private int[] a; 			
	private int N;				
	
	public Stack(int capacity) {
		a = new int[capacity];
		N = 0; 
	}
	public boolean isEmpty() 	{	return N == 0; }
	public void push(int item) 	{	a[N++] = item; }
	public int pop() 			{	return a[--N]; }
	public int peek()			{	return a[N - 1]; }
	public int[] getArray() 	{	return a;	   }
	public int size() 			{	return N;		}		

} 
		
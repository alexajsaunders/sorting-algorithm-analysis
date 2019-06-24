package Assignment2;

/**
 *  sortStack in the customSort class is given a stack of 
 *  integers, and sorts in ascending order using all stack 
 *  functions by utilizing another temporary stack. 
 *  
 * @param Stack inputStack > the Stack to be sorted
 * @param int size > size of the Stack to be sorted
 *
 */

public class customSort {
	
	public static Stack sort(Stack inputStack, int size){
		
		if (size <= 1) {	
			return inputStack;			
		}
		
		Stack helper = new Stack(size);			
		int minimum = Integer.MAX_VALUE;		
		int count = 0;			
		
		while (count < size) {			
			int temp = inputStack.pop();		
			if(temp < minimum){			
				minimum = temp;		
			}
			helper.push(temp);
			count++;	
		}
		
		inputStack.push(minimum);
		boolean minFind = false;
		
		while (count > 0) {			
			int temp = helper.pop();			
			if ( temp != minimum || minFind ) { 
				inputStack.push(temp);	
			} else {
				minFind = true;
			}
			count--;	
		}
		
		sort(inputStack, size - 1);	
		
		return inputStack;
	}
}



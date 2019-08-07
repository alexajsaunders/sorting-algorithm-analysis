package Assignment2;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Client {

	/***************************************************************
	 *      Methods to read and store contexts of text files       *
	 ***************************************************************/
	private String path; 
	public Client(String file_path) {
		path = file_path;
	}
	
	public Integer[] readToArray() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader reader = new BufferedReader(fr);
		
		int count = countLines();
		Integer[] inputData = new Integer[count];
		
		for (int i = 0; i < count; i++) {
			inputData[i] = Integer.parseInt( reader.readLine() );
		}
		
		reader.close();
		return inputData;
	}
	
	public Stack readToStack() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader reader = new BufferedReader(fr);
		
		int count = countLines();
		Stack inputData = new Stack(count);
		
		for (int i = 0; i < count; i++) {
			 inputData.push( Integer.parseInt( reader.readLine() ));
		}
		reader.close();
		return inputData;
	}
	
	public int countLines() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader reader = new BufferedReader(fr);
		
		String element;
		int count = 0;
		
		while (( element = reader.readLine() ) != null) {
			count++;
		}
		
		reader.close();
		return count;
	}
	/***************************************************************
	 *        Methods to write sorted data into text files         *
	 ***************************************************************/
	public static void arrayToFile( Comparable[] sortedArray, String fileName ) throws IOException {
		BufferedWriter writer = new BufferedWriter( new FileWriter (fileName) );
		
		for (int i = 0; i < sortedArray.length; i++) {
			writer.write(Integer.toString( (int) sortedArray[i]) );
			writer.newLine();
		}
		
		writer.close();
	}
	
	public static void stackToFile( Stack sortedStack, String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter (fileName) );
		
		for (int x : sortedStack.getArray()) {
			Integer xt = (Integer) x;
			writer.write(xt+"");
			writer.newLine();
		}
		
		writer.close();
	}
	/***************************************************************
	 *     Method to compare the contents of two text files        *
	 ***************************************************************/
	public static boolean compareFiles( String fileName1, String fileName2 ) throws IOException {
	
		BufferedReader br1 = new BufferedReader(new FileReader (fileName1) );
		BufferedReader br2 = new BufferedReader(new FileReader (fileName2) );
		
		boolean match = true;
		int lineTrack = 0;
		
		String element1 = br1.readLine();
		String element2 = br2.readLine();
		
		while ( element1 != null  || element2 != null ) {
			if ( element1 == null || element2 == null ) {
				match = false;
				break;
			} 	else if ( !element1.equalsIgnoreCase(element2) ) {
					match = false;
					break; } 
			
			element1 = br1.readLine();
			element2 = br2.readLine();
			lineTrack++;
		}
		
		br1.close();
		br2.close();
		
		if(match) {
			return true; }
		else {
			return false; }
	}
	/***************************************************************
	 *    Methods to compute time taken by sorting algorithm       *
	 ***************************************************************/
	public static long runTime( Comparable[] inputArray ) {
		long startTime = System.nanoTime();
		quickSort.sort(inputArray);
		long endTime = System.nanoTime();
		long runTime = endTime - startTime;
		
		return runTime;
	}
	public static long runTimeCustom(Stack inputArray, int size) {
		long startTime = System.nanoTime();
		customSort.sort(inputArray, size);
		long endTime = System.nanoTime();
		long runTime = endTime - startTime;
		
		return runTime;
	}
	
	public static void main(String args[]) throws IOException {
		/**********************************************************************
		 *                             Quicksort                              *
		 **********************************************************************/
		System.out.println("---------Quicksort Implementation:---------\n");
		System.out.print("Unsorted array:     " );
		Comparable A[] = {9, 9, 10, 3, 6, 2, 1, 1};
		for (Object q : A) {
			Integer qt = (Integer) q;
			System.out.print( qt.toString() +" ");
		}
		System.out.println(" ");
		quickSort.sort(A);
		System.out.print("Quicksorted Array:  ");
		for (Object x : A) {
			Integer xt = (Integer) x;
			System.out.print( xt.toString() +" ");
		}
		System.out.println(" ");
		System.out.println(" ");
		
		/*******************
		 *  array size 10  *
		 ******************/
		long rt1 = 0;
		String file_name = "arrayLength_10.txt";
		
		try {
			Client file = new Client(file_name);
			Comparable [] aryLines = file.readToArray();
			rt1 = Client.runTime(aryLines);
		} catch (IOException e) {
				System.out.println( e.getMessage() );	
			}

		/********************
		 *  array size 100  *
		 *******************/
		long rt2 = 0;
		Comparable [] qs2 = null;
		String file_name2 = "arrayLength_100.txt";
	
		try { 
			Client file = new Client(file_name2);
			Comparable [] aryLines = file.readToArray();
			rt2 = Client.runTime(aryLines);
			quickSort.sort(aryLines);
			qs2 = aryLines;
		} catch (IOException b)	{
				System.out.println( b.getMessage() );	
			}
		
		String file_name2Out = "output_100.txt";
		Client.arrayToFile(qs2, file_name2Out); 
		
		String expectedOutput2 = "expected_output_100.txt";
		
		if ( Client.compareFiles (file_name2Out, expectedOutput2) == true ) {
			System.out.println("output_100.text and expected_output_100.text are exactly the same");
			} else { 
				System.out.println("These files have differing content");
				}

		/*********************
		 *  array size 1000  *
		 *********************/
		long rt3 = 0;
		Comparable [] qs3 = null;
		String file_name3 = "arrayLength_1000.txt";
		
		try { 
			Client file = new Client(file_name3);
			Comparable [] aryLines = file.readToArray();
			rt3 = Client.runTime(aryLines);
			qs3 = aryLines;
		} catch (IOException c) { 	
			System.out.println( c.getMessage() ); 
			}
		
		String file_name3Out = "output_1000.txt";
		Client.arrayToFile(qs3, file_name3Out);
		 
		String expectedOutput3 = "expected_output_1000.txt";
		
		if (Client.compareFiles (file_name3Out, expectedOutput3) == true) {
			System.out.println("output_1000.text and expected_output_1000.text are exactly the same\n");
			} else { 
				System.out.println("These files have differing content");
				}	
		
		/**********************
		 *  array size 10000  *
		 *********************/
		long rt4 = 0;
		String file_name4 = "arrayLength_10000.txt";
		
		try {
			Client file = new Client(file_name4);
			Comparable [] aryLines = file.readToArray();
			rt4 = Client.runTime(aryLines); 					
		} catch (IOException d) { 	
				System.out.println( d.getMessage() ); 
			}
		
		/***********************
		 *  array size 100000  *
		 ***********************/
		long rt5 = 0;
		String file_name5 = "arrayLength_100000.txt";
		
		try {
			Client file = new Client(file_name5);
			Comparable [] aryLines = file.readToArray();
			rt5 = Client.runTime(aryLines);
		} catch (IOException f) {
				System.out.println( f.getMessage() ); 
			}
		
		
		
		/**********************************************************************
		 *                             Customsort                             *
		 **********************************************************************/
		System.out.println("---------Customsort Implementation:---------\n");
		System.out.print("Unsorted array:      ");
		int B[] = {9, 9, 10, 3, 6, 2, 1, 1};
		for (int a : B) {
			Integer ax = (Integer) a;
			System.out.print( ax.toString()+"  ");
		}
		System.out.println(" ");
		Stack s = new Stack(B.length);
		for (int i = 0; i < B.length; i++) {
			s.push(B[i]);
		}
		Stack sortedStack;
		sortedStack = customSort.sort(s, B.length);
		System.out.print("Custom sorted stack: ");
		for (int x : sortedStack.getArray()) {
			Integer xt = (Integer) x;
			System.out.print( xt.toString()+"  ");
		}
		
		System.out.println(" ");
		System.out.println(" ");
	
		/*******************
		 *  array size 10  *
		 ******************/
		long csrt1 = 0;
		String csfile_name = "arrayLength_10.txt";
	
		try {
			Client file = new Client(csfile_name);
			Stack aryLines = file.readToStack();
			csrt1 = Client.runTimeCustom( aryLines, aryLines.size() );
		}catch (IOException a) {
			System.out.println( a.getMessage() ); 
			}
	
		/********************
		 *  array size 100  *
		 *******************/
		long csrt2 = 0;
		Stack cs2 = null;
		String cs_file_name2 = "arrayLength_100.txt";
	
		try {
			Client file = new Client(cs_file_name2);
			Stack aryLines = file.readToStack();
			csrt2 = Client.runTimeCustom(aryLines, aryLines.size());  
			cs2 = aryLines;
		} catch (IOException g) {
			System.out.println( g.getMessage() );
			}
	
		String cs_file_name2Out = "cs_output_100.txt";
		Client.stackToFile(cs2, cs_file_name2Out);
	
		String cs_expectedOutput2 = "expected_output_100.txt";
		if (Client.compareFiles (cs_file_name2Out, cs_expectedOutput2) == true) {	
			System.out.println("cs_output_100.txt and expected_output_100.txt are exactly the same");
			} else { 
				System.out.println("These files have different contents\n");
				}
	
		/*********************
		 *  array size 1000  *
		 *********************/
		long csrt3 = 0;
		Stack cs3 = null;
		String cs_file_name3 = "arrayLength_1000.txt";
	
		try {
			Client file = new Client(cs_file_name3);
			Stack aryLines = file.readToStack();
			csrt3 = Client.runTimeCustom(aryLines, aryLines.size());
			cs3 = aryLines;
		} catch (IOException g) {
			System.out.println( g.getMessage() );
			}
	
		String cs_file_name3Out = "cs_output_1000.txt";
		Client.stackToFile(cs3, cs_file_name3Out);
	
		String cs_expectedOutput3 = "expected_output_1000.txt";
		if (Client.compareFiles (cs_file_name3Out, cs_expectedOutput3) == true) {
			System.out.println("cs_output_1000.txt and expected_output_1000.txt are exactly the same\n");
			} else { 
				System.out.println("These files have different contents\n");
			}
	
		/*********************
		 *  array size 10000 *
		 *********************/
		long csrt4 = 0;
		String cs_file_name4 = "arrayLength_10000.txt";
	
		try {
			Client file = new Client(cs_file_name4);
			Stack aryLines = file.readToStack();
			csrt4 = Client.runTimeCustom( aryLines, aryLines.size() ); 
		} catch (IOException a) {
			System.out.println( a.getMessage() );
			}

		/***********************
		 *  array size 100000  *
		 ***********************/
		long csrt5 = 0;
		Stack cs5 = null;
		String cs_file_name5 = "arrayLength_10000.txt";
	
		try {
			Client file = new Client(cs_file_name5);
			Stack aryLines = file.readToStack();
			csrt5 = Client.runTimeCustom( aryLines, aryLines.size() );
		} catch (IOException u) {
			System.out.println( u.getMessage() );
			} 

		/**********************************
		 *            runtimes            *
		 **********************************/
		
		System.out.println("The table below depicts run time in nano second of various sized arrays in both sorting algorithms");
		System.out.println(String.format(
				 "Algorithm:     | Array length: \n" +
				 "====================================================================\n" +
				 "               | 10       | 100      | 1000     | 10000     | 100000\n" +
				 "               ------------------------------------------------------\n" +
				 "Quicksort      | %s     | %s   | %s  | %s   | %s\n" +
				 "Customsort     | %s     | %s  | %s | %s | %s ", 
				rt1, rt2, rt3, rt4, rt5, csrt1, csrt2, csrt3, csrt4, csrt5)); 
	}
}
 
 

/**
 * Written by Adama Camara
 * Graph.java is a one file system that get input from a text file containing 
 data of a graph.
 It will proivde an output.txt that will show:
   _Adjency matrix
   _Adjency list representation
   _Vertices visited
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.*;
public class Graph {
	public static void main(String[]args) throws FileNotFoundException {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter filename to read from: ");
		String filename=sc.nextLine();
		sc.close();
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);
		int d=inputFile.nextInt();
		ArrayList<String> l=new ArrayList<String>();
		
		//Redirecting the output to a file (inspired from: https://www.tutorialspoint.com/redirecting-system-out-println-output-to-a-file-in-java)
	    File output = new File("output.txt");
	    PrintStream stream = new PrintStream(output);
	    System.out.println("File saved to "+output.getAbsolutePath());
	    System.setOut(stream);
		
		//getting the data from the text file
		while(inputFile.hasNext()) {
			l.add(inputFile.next());
		}
		inputFile.close();//closing first scanner
		Collections.sort(l);
		ArrayList<String> head=heading(l);//custom method to get the headings
		
		//retrieving data from the text file
		Scanner in=new Scanner(file);
		in.nextInt();
		int[][] adj = new int[d][d];//our matrix
		while(in.hasNext()) {
			int rowpos=head.indexOf(in.next());//v1
			int colpos=head.indexOf(in.next());//v2
			adj[rowpos][colpos]=adj[colpos][rowpos]=1;
		}
		in.close();//closing second scanner
		System.out.println("Adjency Matrix:\n");
		printHeading(head);
		for(int i=0; i<d;i++) {
			System.out.print(head.get(i)+" ");
			for(int u=0; u<d; u++) { 
				System.out.print(" "+adj[i][u]+" ");//printing the adjency matrix 
			} 
			System.out.println();
		}
		ArrayList<String> queue=new ArrayList<String>(); //visited list
		queue.add(head.get(0));//starting from A

		System.out.println("\nAdjency list representation:\n");
		for(int row=0; row<d; row++) {//row
			ArrayList<String> tmp=new ArrayList<String>();
			System.out.print("|"+head.get(row)+"|-> ");
			for(int y=0; y<d; y++) {//column
				if(adj[row][y]==1) {
					tmp.add(head.get(y));
					System.out.print(head.get(y)+" ");
					if(!queue.contains(head.get(y)))
						queue.add(head.get(y));
				}
			}
			System.out.println();
		}
		System.out.println("\nVertices visited:\n");
		for(int i=0; i<queue.size(); i++) {//showing the visited 
			System.out.println(queue.get(i)+" visited");
		}
		
	}
	
	//method that will get the heading for our matrix
	public static ArrayList<String> heading(ArrayList<String> list) {
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0;i<list.size();i++) {
			if(!result.contains(list.get(i)))
				result.add(list.get(i));
		}
		return result;
	}
	//method that will print the headings
	public static void printHeading(ArrayList<String> list) {
		System.out.print("  ");
		for(int i=0; i<list.size(); i++) {
			System.out.print(" "+list.get(i)+" ");
		 }
		System.out.println();
	}
}

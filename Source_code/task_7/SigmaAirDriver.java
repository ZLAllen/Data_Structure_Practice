package hw_7;

/*
 * imported files
 */

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A simple class to implement an airline driver.<p> 
 * 109051837<p>
 * Assignment 7 task 3<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 * @author Zhaoqi Li<p>
 *
 */
public class SigmaAirDriver {
	final static String path_in = "Sigma_air.obj";
	final static String path_out = "Sigma_air.obj";
	public static void main(String[] args){
		SigmaAir myAir;
		String selection = "";
		String city, source, dest, filename;
		Scanner input = new Scanner(System.in);
		try{
			FileInputStream file_in = new FileInputStream(path_in);
			ObjectInputStream fin = new ObjectInputStream(file_in);
			myAir = (SigmaAir)fin.readObject();
			fin.close();
			file_in.close();
			System.out.println("Database loaded from " + path_in);
		}catch(Exception e){
			System.out.println("sigma_air.obj is not found. New SigmaAir object will be created.");
			myAir = new SigmaAir();
		}
		do{
			try{
				System.out.println();
				System.out.println("(A) Add City\n" +
						"(B) Add Connection\n" +
						"(C) Load all Cities\n" +
						"(D) Load all Connections\n" +
						"(E) Print all Cities\n" +
						"(F) Print all Connections\n" +
						"(G) Remove Connection\n" +
						"(H) Find Shortest Path\n" +
						"(Q) Quit\n");
				System.out.print("Enter a selection: ");
				selection = input.nextLine().toUpperCase();
				System.out.println();
				switch(selection){
					case "A" : 
						System.out.print("Enter the name of the city: ");
						city = input.nextLine();
						myAir.addCity(city);
						break;
						
					case "B" :
						System.out.print("Enter source city: ");
						source = input.nextLine();
						System.out.print("Enter destination city: ");
						dest = input.nextLine();
						myAir.addConnection(source, dest);
						break;
						
					case "C" :
						System.out.print("Enter the file name: ");
						filename = input.nextLine();
						System.out.println();
						myAir.loadAllCities(filename);
						break;
					case "D" :
						System.out.print("Enter the file name: ");
						filename = input.nextLine();
						System.out.println();
						myAir.loadAllConnections(filename);
						break;
					case "E":
						String selection_2 = "";
						do{
							System.out.println();
							System.out.println("(EA) Sort Cities by Name\n" +
							"(EB) Sort Cities by Latitude\n" +
							"(EC) Sort Cities by Longitude\n" + 
							"(Q) Quit\n");
							System.out.print("Enter a selection: ");
							selection_2 = input.nextLine().toUpperCase();
							System.out.println();
							switch(selection_2){
								case "EA" :
									myAir.printAllCities(new NameComparator());
									break;
								case "EB" : 
									myAir.printAllCities(new LatComparator());
									break;
								case "EC" :
									myAir.printAllCities(new LngComparator());
									break;
								case "Q" :
									break;
								default :
									System.out.println(selection_2 + "is not a valid selection...");
							}
						}while(!selection_2.equals("Q"));
						break;
						
					case "F" :
						myAir.printAllConnections();
						break;
						
					case "G" :
						System.out.print("Enter source city: ");
						source = input.nextLine();
						System.out.print("Enter destination city: ");
						dest = input.nextLine();
						myAir.removeConnection(source, dest);
						break;
						
					case "H" :
						System.out.print("Enter source city: ");
						source = input.nextLine();
						System.out.print("Enter destination city: ");
						dest = input.nextLine();
						System.out.println(myAir.shortestPath(source, dest));
						break;
					
					case "Q" :
						try {
							FileOutputStream file_out = new FileOutputStream(path_out);
							ObjectOutputStream fout = new ObjectOutputStream(file_out);
							fout.writeObject(myAir); 
							fout.close();
							file_out.close();
							System.out.println("SigmaAir object saved into file "+ path_out);
						} catch (IOException e){
							System.out.print("Unable to write the library into the database, please confirm your data");
							System.out.print("Or enter q again to force quit...");
							e.printStackTrace();
							if(!(input.nextLine().toUpperCase().equals("Q")))
								selection = "";
						}
						break;
					default :
						System.out.println(selection + " is not a valid selection...");
				}
			}catch (Exception e){
				System.out.println("Some errors occur, read the stack trace then enter B to reach the menu...");
				e.printStackTrace();
				while(!(input.nextLine().toUpperCase().equals("B")));
			}
		}while(!selection.equals("Q"));
		
		System.out.println("Program terminating...");
		input.close();
	}
	
	
}

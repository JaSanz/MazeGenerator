package main;

import java.util.Scanner;

import maze.DepthFirstSearch;
import maze.SquareGenerator;
import view.CreateView;

public class Main {
	
	private static int mode;
	private static int xTam, yTam;

	
	private static void readMode(Scanner sc) {
		do {
		System.out.print("Introduce el modo: ");
		mode = sc.nextInt();
		} while(mode != 0);
	}
	
	private static void readDimensions(Scanner sc) {
		do {
			System.out.print("Introduce el número de filas: ");
			xTam = sc.nextInt();
		} while(xTam < 0);
		do {
			System.out.print("Introduce el número de columnas: ");
			yTam = sc.nextInt();
		} while(yTam < 0);
	}
	
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		readMode(sc);
		readDimensions(sc);
		
		DepthFirstSearch a = new SquareGenerator(mode, xTam, yTam);
		while (a.checkGeneration() == false) {
			System.out.print("Error. Vuelve a introducir el modo: ");
			a = null;
			mode = sc.nextInt();
			a = new SquareGenerator(mode, xTam, yTam);
		}
		
		a.mazeGeneration();
		System.out.println(a);
		sc.close();
		
		CreateView view = new CreateView((SquareGenerator) a);
	}

}

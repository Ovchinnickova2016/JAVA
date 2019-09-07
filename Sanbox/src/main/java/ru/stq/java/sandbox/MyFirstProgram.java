package ru.stq.java.sandbox;

public class MyFirstProgram{
	public static void main (String [] args){
    System.out.println("Hello world!");
 		Point p1 = new Point(-2.3,4);
		Point p2 = new Point(8.5,0.7);
		System.out.println("Расстояние между двуям точками: "+ Point.distance(p1, p2));
	}
}
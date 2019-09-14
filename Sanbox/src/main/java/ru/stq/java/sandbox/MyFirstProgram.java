package ru.stq.java.sandbox;

public class MyFirstProgram{
	public static void main (String [] args){
    System.out.println("Hello world!");
    Point p1 = new Point(1, 2);
    Point p2 = new Point(9, 4);
    System.out.println("Расстояние между двумя точками: "+ p1.distance(p2));
	}
}
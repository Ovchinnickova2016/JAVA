package ru.stq.java.sandbox;

public class MyFirstProgram{
	public static void main (String [] args){
    System.out.println("Hello world!");
    Point points = new Point(1.1, 2.1, 4.1, 3.1);
    double dist = points.distance();
    System.out.println("Расстояние между двумя точками: "+ points.distance());
	}
}
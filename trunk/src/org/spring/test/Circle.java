package org.spring.test;

public class Circle implements Shape {
	private Point center;
	private int radius;
	
	
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	@Override
	public void draw() {
		System.out.println("Circle has drawn!");
		System.out.println("This Circle's Center Point is (" + center.getX() + "," + center.getY() + ")");
	}
	
	
}

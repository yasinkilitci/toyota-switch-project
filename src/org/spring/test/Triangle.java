package org.spring.test;

public class Triangle implements Shape {
	
	private Point pointA;
	private Point pointB;
	private Point pointC;

	
	
	public Point getPointA() {
		return pointA;
	}



	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}



	public Point getPointB() {
		return pointB;
	}



	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}



	public Point getPointC() {
		return pointC;
	}



	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}


	@Override
	public void draw(){
		
		System.out.println("Triangle has drawn!");
		System.out.println("Point A = (" + pointA.getX() + "," + pointA.getY() + ")");
		System.out.println("Point B = (" + pointB.getX() + "," + pointB.getY() + ")");
		System.out.println("Point C = (" + pointC.getX() + "," + pointC.getY() + ")");
		
	}

public void myInitializer(){
	System.out.println("Bean hazýrlandý!");
}

public void myDisposer(){
	System.out.println("Yok Edildi!");
}
	
/*
	@Override
	public void destroy() throws Exception {
		System.out.println("Yok Edildi!");
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Bean hazýrlandý!");
	}
*/
	
}

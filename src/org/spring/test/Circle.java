package org.spring.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

@Component
public class Circle implements Shape {
	private Point center;
	private int radius;
	
	
	
	public Point getCenter() {
		return center;
	}
	
	@Required
	@Resource
	@Qualifier("circleRelated")
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
	
	@PostConstruct
	public void initializer(){
		
		System.out.println("Circle has ben created!");
	}
	
	@PreDestroy
	public void destroyer(){
		
		System.out.println("Circle has ben destroyed!");
	}
	
	
}

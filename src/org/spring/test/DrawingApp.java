package org.spring.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		AbstractApplicationContext context= new ClassPathXmlApplicationContext("/main/resources/spring.cfg.xml");
		Shape shape = (Shape)context.getBean("circle");
		context.registerShutdownHook();
		shape.draw();
		((AbstractApplicationContext) context).close();
	}

}

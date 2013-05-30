package org.spring.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context= new ClassPathXmlApplicationContext("/main/resources/spring.cfg.xml");
		BeanFactory factory = context;
		Triangle triangle = (Triangle)factory.getBean("triangle");
		triangle.draw();
	}

}

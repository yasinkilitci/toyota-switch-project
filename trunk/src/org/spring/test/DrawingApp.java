package org.spring.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.da.KulDAO;

public class DrawingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		AbstractApplicationContext context= new ClassPathXmlApplicationContext("/main/resources/spring.test.cfg.xml");
		Shape shape = (Shape)context.getBean("circle");
		context.registerShutdownHook();
		shape.draw();
		System.out.println(context.getMessage("tebrik",null,"Basardiniz!",null));
		((AbstractApplicationContext) context).close();
	}

}

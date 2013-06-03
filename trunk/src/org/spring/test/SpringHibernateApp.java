package org.spring.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.da.KulDAO;

public class SpringHibernateApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractApplicationContext context= new ClassPathXmlApplicationContext("/main/resources/spring.cfg.xml");
		KulDAO kuldao = (KulDAO)context.getBean("KulDAO_h"); 
		kuldao.KulEkle("mankafa", "ilker", "...", 344, "mankafa@hotmail.com", "12345");
		((AbstractApplicationContext) context).close();
	}

}

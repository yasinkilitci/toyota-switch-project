package org.hibernate.util;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;

import com.dao.DeviceDAO;
import com.dao.ResponsibilityDAO;
import com.entity.Device;
import com.entity.User;
import com.entity.Port;
import com.exceptions.MyException;

public class HibernateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		
		((ResponsibilityDAO)context.getBean("SorumlulukDAO",ResponsibilityDAO.class)).tekSorumlulukVer(35, 1);
		((ResponsibilityDAO)context.getBean("SorumlulukDAO",ResponsibilityDAO.class)).tekSorumlulukVer(35, 2);
		
		/*
		((SorumlulukDAO)context.getBean("SorumlulukDAO",SorumlulukDAO.class)).tekSorumlulukVer(36, 1);
		((SorumlulukDAO)context.getBean("SorumlulukDAO",SorumlulukDAO.class)).tekSorumlulukVer(36, 2);
		*/
		/*
		ArrayList<Kul> kullar = ((SorumlulukDAO)context.getBean("SorumlulukDAO",SorumlulukDAO.class)).sorumluKullariGetir(1);
		System.out.println(kullar.size());
		for (Kul kul : kullar)
		{
			System.out.println(kul.getKuladi());
		}*/
		
		/*
		((CihazDAO)context.getBean("CihazDAO",CihazDAO.class)).tumCihazlariTara();
		*/
	}
}

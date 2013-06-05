package org.hibernate.util;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;

import com.da.CihazDAO;
import com.da.SorumlulukDAO;
import com.entity.Cihaz;
import com.entity.Kul;
import com.entity.Port;
import com.exceptions.MyException;

public class HibernateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Kul kul = new Kul();
		 kul.setKuladi("mangaka41");
		 kul.setAdres("Camlitepe");
		 kul.setSifre("1234");
		 kul.setTel(1234);
		 kul.setAdsoyad("Yunus Yildiz");
		 kul.setYetki(0);
		 
		
		Cihaz c = new Cihaz();
		c.setAd("Switch1");
		c.setIp("192.168.1.1");
		c.setTur_id(1);
		c.setUretici_id(1);
		c.setResim_yolu("falanca.jpeg");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Port port;
		for(int i=0;i<24;i++)
		{
			port = new Port();
			port.setName("Fa0/"+ i);
			port.setStatus(0);
			port.setDuplex(0);
			port.setSpeedtype(0);
			port.setVlan(0);
			c.getPortlar().add(port);
			port.setCihaz(c);
			session.save(port);
		}
		
		c.getKullar().add(kul);
		kul.getCihazlar().add(c);
		session.save(kul);
		session.save(c);
		session.getTransaction().commit();
		session.close();*/
		
		/*
		try {
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			ArrayList<Kul> kullar = new ArrayList<Kul>();
			kullar = ((SorumlulukDAO)context.getBean("SorumlulukDAO",SorumlulukDAO.class)).sorumluKullariGetir(1);
			for ( Kul kul : kullar)
			{
				System.out.println(kul.getId());
			}
			
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		ArrayList<Cihaz> liste = ((CihazDAO)context.getBean("CihazDAO",CihazDAO.class)).benzeyenCihazlariListele("Cis");
		
		for(Cihaz cihaz : liste)
		{
			System.out.println(cihaz.getAd());
		}
		
		
		
		
	}
}

package org.hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Kul;

public class HibernateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Kul kul = new Kul();
		 /*kul.setId(1);*/
		
		 kul.setKuladi("mangaka41");
		 kul.setAdres("Camlitepe");
		 kul.setSifre("1234");
		 kul.setTel(1234);
		 kul.setAdsoyad("Yunus Yildiz");
		 kul.setYetki(0);
		 try{
			 SessionFactory sf = HibernateUtil.getSessionFactory();
			 Session session = sf.openSession();
			 session.beginTransaction();
			 session.save(kul);
			 session.getTransaction().commit();
		 }
		 catch(HibernateException h)
		 {
			 h.printStackTrace();
		 }
		
	}
}

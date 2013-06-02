package com.da;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.util.HibernateUtil;

import com.entity.Kul;

public class KulDAO_h {

	public void KulEkle(String kuladi, String adsoyad, String adres, int tel, String eposta, String sifre){
		
		Kul kul = new Kul();
		 
		 kul.setKuladi(kuladi);
		 kul.setAdsoyad(adsoyad);
		 kul.setAdres(adres);
		 kul.setTel(tel);
		 kul.setEposta(eposta);
		 kul.setYetki(0);
		 kul.setSifre(sifre);
			 try
			 {
				 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				 Session session = sessionFactory.openSession();
				 session.beginTransaction();
				 session.save(kul);
				 session.getTransaction().commit();
				 session.close();
			 }
			 catch(ConstraintViolationException cve)
			 {
				 throw cve;
			 }
			 catch(HibernateException h)
			 {
				 h.printStackTrace();
				 return;
			 }
			
			return;
		}
}

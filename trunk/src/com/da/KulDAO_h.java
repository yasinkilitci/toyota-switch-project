package com.da;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.util.HibernateUtil;

import com.entity.Kul;

public class KulDAO_h {

	public Kul KulEkle(String kuladi, String adsoyad, String adres, int tel, String sifre){
		
		Kul kul = new Kul();
	
			try {
				 
				 kul.setKuladi(kuladi);
				 kul.setAdsoyad(adsoyad);
				 kul.setAdres(adres);
				 kul.setTel(tel);
				 kul.setYetki(0);
				 kul.setSifre(sifre);
				 try{
				 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				 Session session = sessionFactory.openSession();
				 session.beginTransaction();
				 session.save(kul);
				 session.getTransaction().commit();
				 session.close();
				 }
				 catch(HibernateException h)
				 {
					 h.printStackTrace();
					 return null;
				 }
				
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return kul;
		}
	
	
}

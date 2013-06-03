package com.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Kul;

/* Sýnýfýn bir DAO olduðunu spring'e bildiriyoruz */
@Repository
public class KulDAO_h {

	/* Autowired annotation'ý ile spring'deki bean'e baðladýk. Setter ve Getter olmalý
	 * Çünkü setter injection yapýyoruz
	 */
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
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
				 /* Spring harici sýnýf oluþturulduðunda program çökmesin */
				if(sessionFactory==null)
					 return;
				 Session session = getSessionFactory().openSession();
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
	
public void KulGuncelle(int kulid, String adsoyad, String adres, int tel, String sifre){
		
		String hql = "UPDATE user SET adsoyad=:adsoyad,adres=:adres,tel=:tel,sifre=:sifre WHERE id=:id";
		try 
		{
			/* Spring harici sýnýf oluþturulduðunda program çökmesin */
			if(sessionFactory==null)
				 return;
			 Session session = getSessionFactory().openSession();
			 Query query = session.createQuery(hql);
			 query.setString("adsoyad", adsoyad);
			 query.setString("adres", adres);
			 query.setString("sifre", sifre);
			 query.setInteger("tel", tel);
			 query.setInteger("id", kulid);
			 int rowcount = query.executeUpdate();
			 System.out.println(rowcount + " Satir Etkilendi!");
			 session.beginTransaction();
			 session.getTransaction().commit();
			 session.close();
			
		} catch (HibernateException h) {
			// TODO Hata olduðunda Yapýlacaklar
			h.printStackTrace();
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

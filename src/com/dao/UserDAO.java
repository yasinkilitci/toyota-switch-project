/****** HIBERNATE'LEND�!! ******/
package com.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Repository;

import com.entity.User;

/* S�n�f�n bir DAO oldu�unu spring'e bildiriyoruz */
@Repository
public class UserDAO {

	/* Autowired annotation'� ile spring'deki bean'e ba�lad�k. Setter ve Getter olmal�
	 * ��nk� setter injection yap�yoruz
	 */
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	public void KulEkle(String kuladi, String adsoyad, String adres, int tel, String eposta, String sifre){
		
		User kul = new User();
		 
		 kul.setKuladi(kuladi);
		 kul.setAdsoyad(adsoyad);
		 kul.setAdres(adres);
		 kul.setTel(tel);
		 kul.setEposta(eposta);
		 kul.setYetki(0);
		 kul.setSifre(sifre);
			 try
			 {
				 /* Spring harici s�n�f olu�turuldu�unda program ��kmesin */
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
	
	
	public void KulSil(int kulid){
		
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		context.getBean("SorumlulukDAO",ResponsibilityDAO.class).tumSorumluluklariSil(kulid);
		
		String hql = "delete from user where id = :id";
		try 
		{
			/* Spring harici s�n�f olu�turuldu�unda program ��kmesin */
			if(sessionFactory==null)
				 return;
			 Session session = getSessionFactory().openSession();
			 Query query = session.createQuery(hql);
		
			 query.setInteger("id", kulid);
			 int rowcount = query.executeUpdate();
			 System.out.println(rowcount + " Satir Etkilendi!");
			 session.beginTransaction();
			 session.getTransaction().commit();
			 session.close();
			
		} catch (HibernateException h) {
			// TODO Hata oldu�unda Yap�lacaklar
			h.printStackTrace();
		}
	}
	
	
	
	
	public void KulGuncelle(int kulid, String adsoyad, String adres, int tel, String sifre){
		
		String hql = "UPDATE user SET adsoyad=:adsoyad,adres=:adres,tel=:tel,sifre=:sifre WHERE id=:id";
		try 
		{
			/* Spring harici s�n�f olu�turuldu�unda program ��kmesin */
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
			// TODO Hata oldu�unda Yap�lacaklar
			h.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> tumKullanicilariGetir()
	{
		String hql = "from user WHERE yetki=0";
		ArrayList<User> users = new ArrayList<User>();
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			users = (ArrayList<User>)query.list();
			session.close();
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
		
		return users;
	}
	
	public User LoginYap(String kuladi, String sifre)
	{
		/* unique result se�ene�i tek sonu� d�nece�i kesin oldu�u zaman yap�l�r. */
		String hql = "FROM user WHERE kuladi=:kuladi and sifre=:sifre";
		try {
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setString("kuladi", kuladi);
			query.setString("sifre", sifre);
			User kul = (User)query.uniqueResult();
			session.close();
			return kul;
		}
		catch(HibernateException e){
			
			e.printStackTrace();
			return null;
		}
	}
	
	public User kullaniciDetayiniGetir(int kul_id)
	{
		String hql = "FROM user WHERE id=:id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("id", kul_id);
			User kul = (User)query.uniqueResult();
			session.close();
			return kul;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

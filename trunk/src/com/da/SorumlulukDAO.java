package com.da;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Cihaz;
import com.entity.Kul;
import com.exceptions.MyException;

@Repository
public class SorumlulukDAO {
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly=false)
	public void tekSorumlulukVer(int kul_id, int cihaz_id)
	{
		try
		{
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			Session session = getSessionFactory().openSession();
			Kul kul = ((KulDAO)context.getBean("KulDAO",KulDAO.class)).kullaniciDetayiniGetir(kul_id);
			Cihaz cihaz = ((CihazDAO)context.getBean("CihazDAO",CihazDAO.class)).CihazDetayiniGetir(cihaz_id);
			
			session.beginTransaction();
			kul.getCihazlar().add(cihaz);
			session.update(kul);
			session.getTransaction().commit();
			session.close();
		}
		/* Ayný sorumluluk atamasý birden fazla kez yapýlýrsa bu exception oluþur 
		 * Exception'ý tekrar fýrlatarak SorumlulukDAO'yý çaðýran kýsýmda ayný Exception'In
		 * yakalanabilme imkanýný saðladýk.
		 * */
		
		catch(NonUniqueObjectException n)
		{
			throw n;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
	}
	
	/* Liste halinde sorumluluk atamasý yapar çakýþma varsa exception oluþturup çaðrýldýðý yere tekrar
	 * fýrlatýr.
	 */
	@Transactional(readOnly=false)
	public void cokSorumlulukVer(int kul_id, ArrayList<Cihaz> cihazlar) throws NonUniqueObjectException
	{
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		Session session = getSessionFactory().openSession();
		try
		{
			Kul kul = ((KulDAO)context.getBean("KulDAO",KulDAO.class)).kullaniciDetayiniGetir(kul_id);
			session.beginTransaction();
			for(Cihaz cihaz : cihazlar)
			{
				kul.getCihazlar().add(cihaz);
			}
			session.update(kul);
			session.getTransaction().commit();
		}
		/* Ayný sorumluluk atamasý birden fazla kez yapýlýrsa bu exception oluþur 
		 * Exception'ý tekrar fýrlatarak SorumlulukDAO'yý çaðýran kýsýmda ayný Exception'In
		 * yakalanabilme imkanýný saðladýk.
		 * */
		catch(NonUniqueObjectException n)
		{
			session.getTransaction().rollback();
			throw n;
		}
		catch(HibernateException e)
		{
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@Transactional(readOnly=false)
	public void tekSorumlulukKaldir(int kul_id, int cihaz_id) throws MyException
	{
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		Session session = getSessionFactory().openSession();
		try
		{
			
			Kul kul = ((KulDAO)context.getBean("KulDAO",KulDAO.class)).kullaniciDetayiniGetir(kul_id);
			session.beginTransaction();
			Collection<Cihaz> cihazlar = kul.getCihazlar();
			for(Cihaz cihaz : cihazlar)
			{
				if (cihaz.getId()==cihaz_id)
				{
					kul.getCihazlar().remove(cihaz);
					session.saveOrUpdate(kul);
					session.getTransaction().commit();
					session.close();
					return;
				}
			}
			throw new MyException("Silinecek Oge Bulunamadi");
		}
		/* Ayný sorumluluk atamasý birden fazla kez yapýlýrsa bu exception oluþur 
		 * Exception'ý tekrar fýrlatarak SorumlulukDAO'yý çaðýran kýsýmda ayný Exception'In
		 * yakalanabilme imkanýný saðladýk.
		 * */
		catch(MyException mye)
		{
			session.getTransaction().rollback();
			throw mye;
		}
		catch(HibernateException e)
		{
			session.getTransaction().rollback();
			e.printStackTrace();
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public ArrayList<Cihaz> sorumluCihazlariGetir(int kul_id)
	{
		String hql = "Select s.cihaz_id,s.kul_id FROM cihaz c, sorumluluk s WHERE s.cihaz_id=c.id AND s.kul_id=:kul_id";
		try
		{
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			Session session = getSessionFactory().openSession();
			Query query = session.createSQLQuery(hql);
			query.setInteger("kul_id", kul_id);
			List<Object> sonuc = query.list();
			ArrayList<Cihaz> cihazlar = new ArrayList<Cihaz>();
			Iterator iterator = sonuc.iterator();
			while(iterator.hasNext())
			{
				Object []obj = (Object[])iterator.next();
				cihazlar.add(((CihazDAO)context.getBean("CihazDAO",CihazDAO.class)).CihazDetayiniGetir(Integer.valueOf(obj[0].toString())));
			}
			session.close();
			return cihazlar;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	@Transactional(readOnly=true)
	public ArrayList<Kul> sorumluKullariGetir(int cihaz_id)
	{
		String hql = "Select s.kul_id,s.cihaz_id FROM user u, sorumluluk s WHERE s.kul_id=u.id AND s.cihaz_id=:cihaz_id";
		try
		{
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			Session session = getSessionFactory().openSession();
			Query query = session.createSQLQuery(hql);
			query.setInteger("cihaz_id", cihaz_id);
			List<Object> sonuc = query.list();
			ArrayList<Kul> kullar = new ArrayList<Kul>();
			Iterator iterator = sonuc.iterator();
			while(iterator.hasNext())
			{
				Object []obj = (Object[])iterator.next();
				int kul_id = Integer.valueOf(obj[0].toString());
				kullar.add(((KulDAO)context.getBean("KulDAO",KulDAO.class)).kullaniciDetayiniGetir(kul_id));
			}
			session.close();
			return kullar;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	public void tumSorumluluklariSil(int kul_id)
	{
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		Session session = getSessionFactory().openSession();
		try
		{
			
			Kul kul = ((KulDAO)context.getBean("KulDAO",KulDAO.class)).kullaniciDetayiniGetir(kul_id);
			session.beginTransaction();
			kul.setCihazlar(new HashSet<Cihaz>(0));
			session.saveOrUpdate(kul);
			session.getTransaction().commit();
			session.close();
			return;
		}
		catch(HibernateException e)
		{
			session.getTransaction().rollback();
			e.printStackTrace();
			session.close();
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}

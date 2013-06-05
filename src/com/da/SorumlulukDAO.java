package com.da;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Repository;

import com.entity.Cihaz;
import com.entity.Kul;

@Repository
public class SorumlulukDAO {
	@Autowired(required=true)
	private SessionFactory sessionFactory;

	public void tekSorumlulukVer(int kul_id, int cihaz_id)
	{
		try
		{
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			Session session = getSessionFactory().openSession();
			Kul kul = ((KulDAO)context.getBean("KulDAO",KulDAO.class)).kullaniciDetayiniGetir(kul_id);
			Cihaz cihaz = ((CihazDAO)context.getBean("CihazDAO",CihazDAO.class)).CihazDetayiniGetir(cihaz_id);
			
			session.beginTransaction();
			cihaz.getKullar().add(kul);
			kul.getCihazlar().add(cihaz);
			session.saveOrUpdate(kul);
			session.saveOrUpdate(cihaz);
			session.getTransaction().commit();
			session.close();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}

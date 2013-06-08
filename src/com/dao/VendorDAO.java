package com.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.DeviceType;
import com.entity.Vendor;

@Repository
public class VendorDAO {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public ArrayList<Vendor> butunureticileriGetir(){
		
		String hql = "from uretici";
		ArrayList<Vendor> ureticiler = new ArrayList<Vendor>();
		try
		{
			/* Spring harici nesne oluþturulmasýn */
			if(sessionFactory==null)
				return null;
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			ureticiler = (ArrayList<Vendor>)query.list();
			session.close();
			return ureticiler;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	public Vendor ureticiDetayiniGetir(int uretici_id)
	{
		String hql = "FROM uretici WHERE id=:id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("id", uretici_id);
			Vendor uretici = (Vendor)query.uniqueResult();
			session.close();
			return uretici;
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
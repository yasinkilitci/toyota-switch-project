package com.da;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Tur;
import com.entity.Uretici;

@Repository
public class UreticiDAO {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public ArrayList<Uretici> butunureticileriGetir(){
		
		String hql = "from uretici";
		ArrayList<Uretici> ureticiler = new ArrayList<Uretici>();
		try
		{
			/* Spring harici nesne oluþturulmasýn */
			if(sessionFactory==null)
				return null;
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			ureticiler = (ArrayList<Uretici>)query.list();
			session.close();
			return ureticiler;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	public Uretici ureticiDetayiniGetir(int uretici_id)
	{
		String hql = "FROM uretici WHERE id=:id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("id", uretici_id);
			Uretici uretici = (Uretici)query.uniqueResult();
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
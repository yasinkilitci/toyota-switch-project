/****** HIBERNATE'LEND�!! ******/
package com.da;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Tur;

@Repository
public class TurDAO {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	/* Ko�ulsuz t�m t�rleri liste �eklinde d�nd�ren metot */
	@SuppressWarnings("unchecked")
	public ArrayList<Tur> butunTurleriGetir(){
		
		String hql = "from tur";
		ArrayList<Tur> turler = new ArrayList<Tur>();
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			turler = (ArrayList<Tur>)query.list();
			session.close();
			return turler;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	/* id'ye ait Tur objesi geri d�nd�r�r */
	public Tur turDetayiniGetir(int tur_id)
	{
		String hql = "FROM tur WHERE id=:id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("id", tur_id);
			Tur tur = (Tur)query.uniqueResult();
			session.close();
			return tur;
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

package com.da;


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

import com.entity.Cihaz;
import com.entity.Tur;
import com.entity.Uretici;

@Repository
public class CihazDAO {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;

	public Cihaz CihazEkle(String ad, int fiyat, int tur_id, int uretici_id) throws ConstraintViolationException{
		
		Cihaz chz = new Cihaz();
		Tur tur = new Tur();
		Uretici uretici = new Uretici();
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		
		try {
				chz.setAd(ad);
				chz.setFiyat(fiyat);
				chz.setTur_id(tur_id);
				chz.setUretici_id(uretici_id);
				
				/* T�r ve �retici Ayarlamalar� */
				tur = ((TurDAO)context.getBean("TurDAO",TurDAO.class)).turDetayiniGetir(tur_id);
				uretici = ((UreticiDAO)context.getBean("UreticiDAO",UreticiDAO.class)).ureticiDetayiniGetir(uretici_id);
				chz.setTur(tur);
				chz.setUretici(uretici);
				/* T�r ve �retici Ayarlamalar� */
				
				Session session = getSessionFactory().openSession();
				session.beginTransaction();
				session.save(chz);
				session.getTransaction().commit();
				session.close();
				return chz;
		}
		/* Constraint Violation Exception'� burada HibernateException yakalamadan evvel yakalad�k
		 * ve d�� kapsama f�rlatt�k. D�� kapsamda ConstraintViolationException oldu�u i�in oradaki
		 * Catch blo�una y�nlendi ve orada ele al�nd�. "Ayn� isimde ba�ka ayg�t var" Yazd�r�ld�.
		 */
		catch(ConstraintViolationException cve)
		{
			cve.printStackTrace();
			throw cve;
		}
		catch (HibernateException h) {
			h.printStackTrace();
			return null;
		}
	}
	
	public Cihaz CihazDetayiniGetir(int cihazid){
		
		String hql = "FROM cihaz WHERE id=:id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("id", cihazid);
			Cihaz cihaz = (Cihaz)query.uniqueResult();
			session.close();
			
			/* T�r ve �retici Bilgileri Dolduruluyor */
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			Tur tur = ((TurDAO)context.getBean("TurDAO",TurDAO.class)).turDetayiniGetir(cihaz.getTur_id());
			Uretici uretici = ((UreticiDAO)context.getBean("UreticiDAO",UreticiDAO.class)).ureticiDetayiniGetir(cihaz.getUretici_id());
			cihaz.setTur(tur);
			cihaz.setUretici(uretici);
			/* T�r ve �retici Bilgileri Dolduruluyor */
			
			return cihaz;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	/* T�m cihazlar� ko�ulsuz olarak listeler */
	@SuppressWarnings("unchecked")
	public ArrayList<Cihaz> butunCihazlariGetir(){
		
		ArrayList<Cihaz> cihazlar;
		String hql = "FROM cihaz";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			cihazlar = (ArrayList<Cihaz>)query.list();
			session.close();
			return cihazlar;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	/* T�re ait cihazlar�n listesini d�nd�r�r */
	@SuppressWarnings("unchecked")
	public ArrayList<Cihaz> TureAitcihazlariGetir(int tur_id){
		ArrayList<Cihaz> cihazlar;
		String hql = "FROM cihaz WHERE tur_id=:tur_id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("tur_id", tur_id);
			cihazlar = (ArrayList<Cihaz>)query.list();
			session.close();
			return cihazlar;
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

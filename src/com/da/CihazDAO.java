
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
				
				/* Tür ve Üretici Ayarlamalarý */
				tur = ((TurDAO)context.getBean("TurDAO",TurDAO.class)).turDetayiniGetir(tur_id);
				uretici = ((UreticiDAO)context.getBean("UreticiDAO",UreticiDAO.class)).ureticiDetayiniGetir(uretici_id);
				chz.setTur(tur);
				chz.setUretici(uretici);
				/* Tür ve Üretici Ayarlamalarý */
				
				Session session = getSessionFactory().openSession();
				session.beginTransaction();
				session.save(chz);
				session.getTransaction().commit();
				session.close();
				return chz;
		}
		/* Constraint Violation Exception'ý burada HibernateException yakalamadan evvel yakaladýk
		 * ve dýþ kapsama fýrlattýk. Dýþ kapsamda ConstraintViolationException olduðu için oradaki
		 * Catch bloðuna yönlendi ve orada ele alýndý. "Ayný isimde baþka aygýt var" Yazdýrýldý.
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
			
			/* Tür ve Üretici Bilgileri Dolduruluyor */
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			Tur tur = ((TurDAO)context.getBean("TurDAO",TurDAO.class)).turDetayiniGetir(cihaz.getTur_id());
			Uretici uretici = ((UreticiDAO)context.getBean("UreticiDAO",UreticiDAO.class)).ureticiDetayiniGetir(cihaz.getUretici_id());
			cihaz.setTur(tur);
			cihaz.setUretici(uretici);
			/* Tür ve Üretici Bilgileri Dolduruluyor */
			
			return cihaz;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	/* Tüm cihazlarý koþulsuz olarak listeler */
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
	
	/* Türe ait cihazlarýn listesini döndürür */
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

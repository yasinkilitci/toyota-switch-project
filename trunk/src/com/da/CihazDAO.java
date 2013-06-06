
package com.da;


import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Cihaz;

import com.entity.Port;
import com.entity.Tur;
import com.entity.Uretici;
import com.mailing.PostOffice;

@Repository
public class CihazDAO {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;

	

	
public void CihazSil(int cihazid){
		
		String hql = "delete from cihaz where id = :id";
		try 
		{
			/* Spring harici s�n�f olu�turuldu�unda program ��kmesin */
			if(sessionFactory==null)
				 return;
			 Session session = getSessionFactory().openSession();
			 Query query = session.createQuery(hql);
		
			 query.setInteger("id", cihazid);
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
	
	
	
	
	public Cihaz CihazEkle(String ad, String ip, int tur_id, int uretici_id) throws ConstraintViolationException{
		
		Cihaz chz = new Cihaz();
		Tur tur = new Tur();
		Uretici uretici = new Uretici();
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		
		try {
				chz.setAd(ad);
				chz.setIp(ip);
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
				
				/* Port Ayarlamalar� */ 
				
				Port port;
				for(int i=0;i<24;i++)
				{
					port = new Port();
					port.setName("Fa0/"+ i);
					port.setStatus(0);
					port.setDuplex(0);
					port.setSpeedtype(0);
					port.setVlan(0);
					port.setSonerisim(new Date());
					chz.getPortlar().add(port);
					port.setCihaz(chz);
					session.save(port);
				}
				session.save(chz);
				
				/* Port Ayarlamalar� */
				
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
	@Transactional(readOnly=true)
	public ArrayList<Cihaz> tureAitcihazlariGetir(int tur_id){
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
	
	/* Girilen String'i i�eren addaki cihazlar� listeler */
	@Transactional(readOnly=true)
	public ArrayList<Cihaz> benzeyenCihazlariListele(String keyword, String keyword_ip){
		String hql = "From cihaz WHERE ad like :keyword and ip like :keyword_ip";
		Session session = getSessionFactory().openSession();
		try
		{
			Query query = session.createQuery(hql);
			keyword = "%" + keyword + "%";
			keyword_ip = "%" + keyword_ip + "%";
			query.setString("keyword", keyword);
			query.setString("keyword_ip", keyword_ip);
			ArrayList<Cihaz> cihazlar = new ArrayList<Cihaz>();
			cihazlar = (ArrayList<Cihaz>)query.list();
			session.close();
			return cihazlar;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			session.close();
			return null;
		}
	}
	
	/* Tek bir cihaza ait t�m portlar� liste olarak d�nd�r�r */
	@Transactional(readOnly=true)
	public ArrayList<Port> portlariGetir(int cihaz_id)
	{
		ArrayList<Port> portlar;
		String hql = "FROM port WHERE cihaz_id=:cihaz_id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("cihaz_id", cihaz_id);
			portlar = (ArrayList<Port>)query.list();
			session.close();
			return portlar;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			return null;
		}
	}
	
	/* Tek bir cihaza ait kullan�lmayan portlar� liste olarak d�nd�r�r */
	@Transactional(readOnly=true)
	public ArrayList<Port> kullanilmayanPortlariGetir(int cihaz_id)
	{
		Date bugun = new Date();
		ArrayList<Port> portlar = portlariGetir(cihaz_id);
		/* Kullan�lmayan Portlar Listesi */
		ArrayList<Port> kportlar = new ArrayList<Port>();
		for (Port port : portlar)
		{
			Date sonerisim = port.getSonerisim();
			/* milisaniye cinsinden */
			long milifark = bugun.getTime() - sonerisim.getTime();
			long gunfark = milifark / (24*60*60*1000);
			if(gunfark>30)
			{
				kportlar.add(port);
			}
		}
		return kportlar;
	}
	
	/* T�m Cihazlar� Tarayan ve Sorumlu Ki�ilere E-Mail G�nderen Zincirleme Reaksiyon!! */
	public void tumCihazlariTara()
	{
		ArrayList<Cihaz> cihazlar = new ArrayList<Cihaz>();
		ArrayList<Port> kportlar = new ArrayList<Port>();
		PostOffice postOffice = new PostOffice();
		
		try
		{
		cihazlar = butunCihazlariGetir();
		
			 for(Cihaz cihaz : cihazlar)
			 {
				 kportlar = kullanilmayanPortlariGetir(cihaz.getId());
				 if(kportlar.size()>0)
				 postOffice.sorumlularaMailGonder(kportlar);
			 }
			 
		}
		catch(Exception e)
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

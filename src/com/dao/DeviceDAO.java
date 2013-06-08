
package com.dao;


import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Device;

import com.entity.Port;
import com.entity.DeviceType;
import com.entity.Vendor;
import com.mailing.PostOffice;

@Repository
public class DeviceDAO {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	private int expiration;
	
	public void CihazSil(int cihazid){
		
		String hql = "delete from cihaz where id = :id";
		try 
		{
			/* Spring harici sinif olusturulursa program cokmesin */
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
			// TODO Hata oldugunda Yapilacaklar
			h.printStackTrace();
		}
	}
	
	
	
	
	public Device CihazEkle(String ad, String ip, int tur_id, int uretici_id) throws ConstraintViolationException{
		
		Device chz = new Device();
		DeviceType tur = new DeviceType();
		Vendor uretici = new Vendor();
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		
		try {
				chz.setAd(ad);
				chz.setIp(ip);
				chz.setTur_id(tur_id);
				chz.setUretici_id(uretici_id);
				
				/* T�r ve �retici Ayarlamalar� */
				tur = ((DeviceTypeDAO)context.getBean("TurDAO",DeviceTypeDAO.class)).turDetayiniGetir(tur_id);
				uretici = ((VendorDAO)context.getBean("UreticiDAO",VendorDAO.class)).ureticiDetayiniGetir(uretici_id);
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
					Date date = new Date(24*60*60*1000);
					port.setSonerisim(date);
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
	
	public Device CihazDetayiniGetir(int cihazid){
		
		String hql = "FROM cihaz WHERE id=:id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("id", cihazid);
			Device cihaz = (Device)query.uniqueResult();
			session.close();
			
			/* T�r ve �retici Bilgileri Dolduruluyor */
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			DeviceType tur = ((DeviceTypeDAO)context.getBean("TurDAO",DeviceTypeDAO.class)).turDetayiniGetir(cihaz.getTur_id());
			Vendor uretici = ((VendorDAO)context.getBean("UreticiDAO",VendorDAO.class)).ureticiDetayiniGetir(cihaz.getUretici_id());
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
	public ArrayList<Device> butunCihazlariGetir(){
		
		ArrayList<Device> cihazlar;
		String hql = "FROM cihaz";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			cihazlar = (ArrayList<Device>)query.list();
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
	public ArrayList<Device> tureAitcihazlariGetir(int tur_id){
		ArrayList<Device> cihazlar;
		String hql = "FROM cihaz WHERE tur_id=:tur_id";
		try
		{
			Session session = getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			query.setInteger("tur_id", tur_id);
			cihazlar = (ArrayList<Device>)query.list();
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
	public ArrayList<Device> benzeyenCihazlariListele(String keyword, String keyword_ip){
		String hql = "From cihaz WHERE ad like :keyword and ip like :keyword_ip";
		Session session = getSessionFactory().openSession();
		try
		{
			Query query = session.createQuery(hql);
			keyword = "%" + keyword + "%";
			keyword_ip = "%" + keyword_ip + "%";
			query.setString("keyword", keyword);
			query.setString("keyword_ip", keyword_ip);
			ArrayList<Device> cihazlar = new ArrayList<Device>();
			cihazlar = (ArrayList<Device>)query.list();
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
	
	/* Returns the list of expired ports of a single device */
	@Transactional(readOnly=true)
	public ArrayList<Port> kullanilmayanPortlariGetir(int cihaz_id)
	{
		Date bugun = new Date();
		ArrayList<Port> portlar = portlariGetir(cihaz_id);
		/* List of expired ports */
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
				System.err.println("The port: " + port.getName() + " in " + port.getCihaz().getAd() + " is expired!");
			} 
		}
		return kportlar;
	}
	
	/* Tum cihazlari tarayan ve sorumlu kisilere 30 gun kullanilmayan portlari 
	 * email ile bildiren ana metot */
	public void tumCihazlariTara()
	{
		ArrayList<Device> cihazlar = new ArrayList<Device>();
		ArrayList<Port> kportlar = new ArrayList<Port>();
		PostOffice postOffice = new PostOffice();
		
		try
		{
		cihazlar = butunCihazlariGetir();
		
			 for(Device cihaz : cihazlar)
			 {
				 System.out.println("Out of use ports are scanning for the device:" + cihaz.getAd() + "...");
				 kportlar = kullanilmayanPortlariGetir(cihaz.getId());
				 if(kportlar.size()>0)
				 {	
					 try{
					 postOffice.sorumlularaMailGonder(kportlar);
				 		}
					 catch(RuntimeException re){
						 /* If no internet connection is available, print error message and go to next
						  * device. If we don't catch the error, program halts the execution! */
						 System.err.println("No Internet Connection Available!");
						 continue;
				 		}
				 }
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/* Ilgili porta ait son erisim zamanini gunceller */
	public void portaEris(int port_id)
	{
		String hql = "FROM port WHERE id=:port_id";
		Session session = getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setInteger("port_id", port_id);
			Port port = (Port)query.uniqueResult();
			port.setSonerisim(new Date());
			session.saveOrUpdate(port);
			session.getTransaction().commit();
			session.close();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			session.close();
			throw e;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}
	
	
	
}

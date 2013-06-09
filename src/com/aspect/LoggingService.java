package com.aspect;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoggingService {

	@Transactional(readOnly=false)
	public void insertLogMessage(String message)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		try
		{
			LoggingEntity log = new LoggingEntity();
			log.setMessage(message);
			log.setDate(new Date());
			session.save(log);
			session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	public String prepareLogMessageForLogin(String username, String password)
	{
		return "A client has attempted to login with the username: " + username + " and password: " + password;
	}
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
}

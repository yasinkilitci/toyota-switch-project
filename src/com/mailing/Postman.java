package com.mailing;

import java.util.ArrayList;
import java.util.Properties;
import com.entity.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.entity.User;
import com.sun.mail.util.MailConnectException;
 
public class Postman {
	
	private String username;
	private String password;
	private String from;
	private String subject;
	private String mesajMetni;
	
	public void epostaGonder(ArrayList<User> kulListesi, String mesajMetni, String subject) throws MailConnectException
	{
		for (User user : kulListesi)
		{
			try{
			epostaGonder(user.getEposta(),mesajMetni,subject);
			}
			catch(MailConnectException m)
			{
				throw m;
			}
			System.out.println(user.getKuladi() + " bilgilendirildi!");
		}
		
	}
	
	public void epostaGonder(String alici, String mesajMetni, String konu) throws MailConnectException
	{
		System.err.println("Postman is started!");
		final String username = "ilkercruiser@gmail.com";
		final String password = "Xbox%720Rulez";
		final String to = alici;
		final String from = "ilkercruiser@gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(konu);
			message.setText(mesajMetni);
 
			Transport.send(message);
 
			System.err.println(alici + "'ya bir e-posta gönderildi!");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
package com.servlets;

import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;

import com.dao.UserDAO;
import com.encryption.PasswordCodec;


@WebServlet("/kulekle")
@Controller
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try
		{
			/* ************** SPRING ****************** */
			/* Spring altyap�s� haz�rlan�yor */
			AbstractApplicationContext context= SpringFactoryProvider.getApplicationContext();
			/* Spring kullanarak olu�turulan DAO ile veri ekleniyor */
			UserDAO kuldao = (UserDAO)context.getBean("KulDAO");
			/* ************** SPRING ****************** */
			
			/* �ncelikle Kullan�c� ad�n� objeye ta��yal�m. 
		 * Null de�ilse bir 'kaydolma', null ise bir 'g�ncelleme' iste�idir.*/
		Object o_kuladi = request.getParameter("kuladi");
		/* KulID geldiyse bu bir g�ncelleme iste�idir.*/
		Object o_kulid = request.getParameter("kulid");
		
		/* Kaydolma ve G�ncelleme i�lemlerindeki ortak alanlar sorunsuzca atanabilir.*/
		String adsoyad = request.getParameter("adsoyad");
		String adres = request.getParameter("adres");
		int tel = Integer.valueOf(request.getParameter("tel"));
		String sifre = request.getParameter("sifre");
		String eposta = request.getParameter("eposta");
		
				/* Kullan�c� kaydolma iste�i yerine getiriliyor */
				if(o_kuladi!=null)
				{
				
					/* Kullan�c� ad� yaln�zca bu bir kay�t iste�i ise atans�n */
					String kuladi = o_kuladi.toString();
					
						try
						{
							/* ************** SHA-256 ENCRYPTION ****************** */
							/* SHA-256 �ifreleyici s�n�f� olu�turuldu ve encrypt ile �ifrelendi */
							PasswordCodec s256 = new PasswordCodec();
							sifre = s256.encrypt(sifre);
							/* ************** SHA-256 ENCRYPTION ****************** */
						}
						catch(Exception e)
						{
							e.printStackTrace();
							response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							response.getWriter().write("Sifreleyici Hatasi!");
							return;
						}
					
						 boolean eresult = true;
						try
						{
							   InternetAddress emailAddr = new InternetAddress(eposta);
							      emailAddr.validate();
						}
						catch (AddressException ex) {
						      eresult = false;
						   
							response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							response.getWriter().write("E-Posta Hatali!");
							return;
						}
					
						
						
						
						
						/* ************** HIBERNATE - SPRING ****************** */
						/* Spring kullanarak dao olu�turuluyor ve veri ekleniyor */
						kuldao.KulEkle(kuladi, adsoyad, adres, tel, eposta, sifre);
						/* ************** HIBERNATE - SPRING ****************** */
						
					}
				else if(o_kulid!=null)/* G�ncelleme iste�i yerine getiriliyor */
				{
					try
					{
						/* ************** SHA-256 ENCRYPTION ****************** */
						/* SHA-256 �ifreleyici s�n�f� olu�turuldu ve encrypt ile �ifrelendi */
						PasswordCodec s256 = new PasswordCodec();
						sifre = s256.encrypt(sifre);
						/* ************** SHA-256 ENCRYPTION ****************** */
					}
					catch(Exception e)
					{
						e.printStackTrace();
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.getWriter().write("Sifreleyici Hatasi!");
						return;
					}
					int kulid = Integer.valueOf(o_kulid.toString());
					/* ************** HIBERNATE - SPRING ****************** */
					/* Spring kullanarak olu�turulan DAO ile kullan�c� g�ncelleniyor */
					kuldao.KulGuncelle(kulid, adsoyad, adres, tel, sifre);
					/* ************** HIBERNATE - SPRING ****************** */
				}
				
		}
		catch(NumberFormatException n)
		{
			n.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Telefon Bicimi Dogru Degil!");
			return;
		}
		catch(ConstraintViolationException cve)
		{
			cve.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Ayni Kuladi veya Eposta zaten var!");
			return;
		}
		
		}
	
}

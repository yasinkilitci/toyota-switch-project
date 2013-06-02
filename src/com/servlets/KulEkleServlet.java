package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.da.KulDAO;
import com.da.KulDAO_h;
import com.hashing.PasswordCodec;


@WebServlet("/kulekle")
@Controller
public class KulEkleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try
		{
			
		/* Öncelikle Kullanýcý adýný objeye taþýyalým. 
		 * Null deðilse bir 'kaydolma', null ise bir 'güncelleme' isteðidir.*/
		Object o_kuladi = request.getParameter("kuladi");
		/* KulID geldiyse bu bir güncelleme isteðidir.*/
		Object o_kulid = request.getParameter("kulid");
		
		/* Kaydolma ve Güncelleme iþlemlerindeki ortak alanlar sorunsuzca atanabilir.*/
		String adsoyad = request.getParameter("adsoyad");
		String adres = request.getParameter("adres");
		int tel = Integer.valueOf(request.getParameter("tel"));
		String sifre = request.getParameter("sifre");
		String eposta = request.getParameter("eposta");
		
				/* Kullanýcý kaydolma isteði yerine getiriliyor */
				if(o_kuladi!=null)
				{
				
					/* Kullanýcý adý yalnýzca bu bir kayýt isteði ise atansýn */
					String kuladi = o_kuladi.toString();
					
						try
						{
							/* Base64 þifreleyici sýnýfý oluþturuldu ve encrypt ile þifrelendi */
							PasswordCodec b64 = new PasswordCodec();
							sifre = b64.encrypt(sifre);
						}
						catch(Exception e)
						{
							e.printStackTrace();
							response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
							response.getWriter().write("Sifreleyici Hatasi!");
							return;
						}
					
						new KulDAO_h().KulEkle(kuladi, adsoyad, adres, tel, eposta, sifre);
						AbstractApplicationContext context= new ClassPathXmlApplicationContext("/main/resources/spring.cfg.xml");
						KulDAO_h kuldao = (KulDAO_h)context.getBean("KulDAO_h");
						kuldao.KulEkle(kuladi, adsoyad, adres, tel, eposta, sifre);
						
					}
				else if(o_kulid!=null)/* Güncelleme isteði yerine getiriliyor */
				{
					try
					{
						/* Base64 þifreleyici sýnýfý oluþturuldu ve encrypt ile þifrelendi */
						PasswordCodec codec = new PasswordCodec();
						sifre = codec.encrypt(sifre);
					}
					catch(Exception e)
					{
						e.printStackTrace();
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.getWriter().write("Sifreleyici Hatasi!");
						return;
					}
					int kulid = Integer.valueOf(o_kulid.toString());
					new KulDAO().KulGuncelle(kulid, adsoyad, adres, tel, sifre);
					
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
			response.getWriter().write("Ayný Kuladi veya Eposta zaten var!");
			return;
		}
		
		}
	
}

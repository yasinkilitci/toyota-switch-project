package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;

import com.da.KulDAO;
import com.hashing.PasswordCodec;


@WebServlet("/kulekle")
@Controller
public class KulEkleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try
		{
			/* ************** SPRING ****************** */
			/* Spring altyapýsý hazýrlanýyor */
			AbstractApplicationContext context= SpringFactoryProvider.getApplicationContext();
			/* Spring kullanarak oluþturulan DAO ile veri ekleniyor */
			KulDAO kuldao = (KulDAO)context.getBean("KulDAO");
			/* ************** SPRING ****************** */
			
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
							/* ************** SHA-256 ENCRYPTION ****************** */
							/* SHA-256 þifreleyici sýnýfý oluþturuldu ve encrypt ile þifrelendi */
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
					
						/* ************** HIBERNATE - SPRING ****************** */
						/* Spring kullanarak dao oluþturuluyor ve veri ekleniyor */
						kuldao.KulEkle(kuladi, adsoyad, adres, tel, eposta, sifre);
						/* ************** HIBERNATE - SPRING ****************** */
						
					}
				else if(o_kulid!=null)/* Güncelleme isteði yerine getiriliyor */
				{
					try
					{
						/* ************** SHA-256 ENCRYPTION ****************** */
						/* SHA-256 þifreleyici sýnýfý oluþturuldu ve encrypt ile þifrelendi */
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
					/* Spring kullanarak oluþturulan DAO ile kullanýcý güncelleniyor */
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
			response.getWriter().write("Ayný Kuladi veya Eposta zaten var!");
			return;
		}
		
		}
	
}

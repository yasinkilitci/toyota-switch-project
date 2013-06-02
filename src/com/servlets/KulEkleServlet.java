package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;

import com.da.KulDAO;
import com.da.KulDAO_h;
import com.hashing.PasswordCodec;


@WebServlet("/kulekle")
public class KulEkleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
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
							/* Base64 �ifreleyici s�n�f� olu�turuldu ve encrypt ile �ifrelendi */
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
					}
				else if(o_kulid!=null)/* G�ncelleme iste�i yerine getiriliyor */
				{
					try
					{
						/* Base64 �ifreleyici s�n�f� olu�turuldu ve encrypt ile �ifrelendi */
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
			response.getWriter().write("Ayn� Kuladi veya Eposta zaten var!");
			return;
		}
		
		}
	
}

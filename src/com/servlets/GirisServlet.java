//kullan�c� ad� ve �ifreyi onaylat�p index e ya da yanl��sa mesajla birlikte ayn� sayfaya
//y�nlendirdik
package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.KulDAO;
import com.entity.Kul;
import com.hashing.PasswordCodec;


@WebServlet("/giris")
public class GirisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* �nce �ifre teslim al�n�p �ifrelenir ve �yle kontrol edilmek �zere DAO'ya g�nderilir.*/
		String sifre = request.getParameter("sifre");
		try
		{
			/* SHA-256 �ifreleyici s�n�f� olu�turuldu ve encrypt ile �ifrelendi */
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
		
		Kul girisDeneyenKullanici = new KulDAO().LoginYap(request.getParameter("kullaniciadi"), sifre);
		/* DAO NULL d�nd�rd�yse demek ki b�yle bir kullan�c� yok! */
		if (girisDeneyenKullanici==null){
			String mesaj = "Kullan�c� ad� veya �ifre hatal�";
			System.out.print("hata");
			request.setAttribute("mesaj", mesaj);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		/* DAO Kul d�nd�rd�yse o kullan�c�n�n yetkisine g�re session olu�tur */
		int session_yetki = girisDeneyenKullanici.getYetki();
		/* ID tutan session'a 1453 taban uygulad�k. Keyfii bir de�er */
		int session_id = 1453 + girisDeneyenKullanici.getId();
		String session_ad = girisDeneyenKullanici.getAdsoyad();
				
				request.getSession().setAttribute("session_id", session_id);
				request.getSession().setAttribute("session_yetki", session_yetki);
				/* yetki = 1 -> ADMIN  /// yetki = 0 -> KULLANICI */
				request.getSession().setAttribute("session_ad", session_ad);	
				response.sendRedirect("index.jsp");
	}
	
	

}

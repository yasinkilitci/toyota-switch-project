//kullanýcý adý ve þifreyi onaylatýp index e ya da yanlýþsa mesajla birlikte ayný sayfaya
//yönlendirdik
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
		
		/* Önce þifre teslim alýnýp þifrelenir ve öyle kontrol edilmek üzere DAO'ya gönderilir.*/
		String sifre = request.getParameter("sifre");
		try
		{
			/* SHA-256 þifreleyici sýnýfý oluþturuldu ve encrypt ile þifrelendi */
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
		/* DAO NULL döndürdüyse demek ki böyle bir kullanýcý yok! */
		if (girisDeneyenKullanici==null){
			String mesaj = "Kullanýcý adý veya Þifre hatalý";
			System.out.print("hata");
			request.setAttribute("mesaj", mesaj);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		/* DAO Kul döndürdüyse o kullanýcýnýn yetkisine göre session oluþtur */
		int session_yetki = girisDeneyenKullanici.getYetki();
		/* ID tutan session'a 1453 taban uyguladýk. Keyfii bir deðer */
		int session_id = 1453 + girisDeneyenKullanici.getId();
		String session_ad = girisDeneyenKullanici.getAdsoyad();
				
				request.getSession().setAttribute("session_id", session_id);
				request.getSession().setAttribute("session_yetki", session_yetki);
				/* yetki = 1 -> ADMIN  /// yetki = 0 -> KULLANICI */
				request.getSession().setAttribute("session_ad", session_ad);	
				response.sendRedirect("index.jsp");
	}
	
	

}

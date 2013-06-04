//kullanýcý adý ve þifreyi onaylatýp index e ya da yanlýþsa mesajla birlikte ayný sayfaya
//yönlendirdik
package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.da.KulDAO;
import com.encryption.PasswordCodec;
import com.entity.Kul;


@WebServlet("/giris")
public class GirisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Önce þifre teslim alýnýp þifrelenir ve öyle kontrol edilmek üzere DAO'ya gönderilir.*/
		String sifre = request.getParameter("sifre");
		String kuladi = request.getParameter("kullaniciadi");
		
		/* *************** SHA-256 ***************** */
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
		/* *************** SHA-256 ***************** */
		
		
		/* *************** HIBERNATE - SPRING ***************** */
		/* Spring'den bir context oluþtuduk ve getBean ile bir KulDAO getirttik. Ve Login metodunu uyguladýk */
		
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		Kul girisDeneyenKullanici = ((KulDAO) context.getBean("KulDAO")).LoginYap(kuladi, sifre);
		
		/* *************** HIBERNATE - SPRING ***************** */
		
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
		/* ID tutan session'a 1453 taban uyguladýk. Keyfi bir deðer */
		int session_id = 1453 + girisDeneyenKullanici.getId();
		String session_ad = girisDeneyenKullanici.getAdsoyad();
				
				request.getSession().setAttribute("session_id", session_id);
				request.getSession().setAttribute("session_yetki", session_yetki);
				/* yetki = 1 -> ADMIN  /// yetki = 0 -> KULLANICI */
				request.getSession().setAttribute("session_ad", session_ad);	
				response.sendRedirect("index.jsp");
	}
	
	

}

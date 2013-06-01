package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.KulDAO;
import com.da.KulDAO_h;
import com.entity.Kul;


@WebServlet("/kulekle")
public class KulEkleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


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
		
				/* Kullan�c� kaydolma iste�i yerine getiriliyor */
				if(o_kuladi!=null)
				{
				
					/* Kullan�c� ad� yaln�zca null olmad��� zaman atans�n */
					String kuladi = o_kuladi.toString();
					
					/* ESK� T�P DAO
					/*Kul kul = new KulDAO().KulEkle(kuladi,adsoyad,adres,tel,sifre);*/
					Kul kul = new Kul(); 
					/*kul = new KulDAO().KulEkle(kuladi, adsoyad, adres, tel, sifre);**/
					kul = new KulDAO_h().KulEkle(kuladi, adsoyad, adres, tel, sifre);
					
						if (kul!=null )
						{
							/* kullan�c� null de�ilse i�lem ba�ar�yla bitmi� demektir. Return ile d�n�l�r.*/
							return;
						}
						else 
						{
							String mesaj = "Kayit basarisiz";
							
							request.setAttribute("mesaj", mesaj);
							request.getRequestDispatcher("a_kulekle.jsp").forward(request, response);
						}
					}
				else if(o_kulid!=null)/* G�ncelleme iste�i yerine getiriliyor */
				{
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
		
		}
	
}

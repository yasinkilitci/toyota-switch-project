package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.CihazDAO;
import com.da.CihazDAO_h;
import com.entity.Cihaz;

/**
 * Servlet implementation class cihazEkleServlet
 */
@WebServlet("/cihazekle")
public class CihazEkleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ad = request.getParameter("cihazad");
		int fiyat = Integer.valueOf(request.getParameter("cihazfiyat"));
		int tur_id = Integer.valueOf(request.getParameter("cihaztur"));
		int uretici_id = Integer.valueOf(request.getParameter("cihazuretici"));
		
		
		/*Cihaz cihaz = new CihazDAO().CihazEkle(cihazad, cihazfiyat, cihaztur, cihazuretici);*/
		Cihaz cihaz = new CihazDAO_h().CihazEkle(ad, fiyat, tur_id, uretici_id);
		
		if (cihaz==null )
		{
			String mesaj = "Kayit basarisiz";	
			request.setAttribute("mesaj", mesaj);
			request.getRequestDispatcher("a_cihazekle.jsp").forward(request, response);
		}
	}
}

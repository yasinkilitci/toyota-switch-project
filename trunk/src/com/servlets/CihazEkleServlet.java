package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.da.CihazDAO;
import com.da.CihazDAO_h;
import com.entity.Cihaz;
import com.exceptions.MyException;

/**
 * Servlet implementation class cihazEkleServlet
 */
@WebServlet("/cihazekle")
public class CihazEkleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Try bloðunda oluþabilecek hatalar ayrý ayrý catch bloklarýnda ele alýndý 
		 * MyException - Fiyat istediðimiz aralýkta deðilse
		 * NumberFormatException - Fiyat alanýna sayý dýþýnda bir þey girildiyse
		 * HibernateException - Veri eklerken bir hata oluþtuysa
		 */
		try{
			/* Post ile gelen parametreler */
			int fiyat = Integer.valueOf(request.getParameter("cihazfiyat"));
			int tur_id = Integer.valueOf(request.getParameter("cihaztur"));
			int uretici_id = Integer.valueOf(request.getParameter("cihazuretici"));
			String ad = request.getParameter("cihazad");
			/* Fiyat istediðimiz aralýkta deðilse hata fýrlat */
			if((fiyat<50)||(fiyat>10000))
			throw new MyException("Fiyat Istenilen Aralikta Degil!");
			new CihazDAO_h().CihazEkle(ad, fiyat, tur_id, uretici_id);
		}
		catch(NumberFormatException n){
			n.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Fiyat Bicimi Dogru Degil!");
			return;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Cihaz Eklenirken Hata Olustu!");
			return;
		}
		catch(MyException e)
		{
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Fiyat Istenilen Aralikta Degil!");
			return;
		}
	}
}

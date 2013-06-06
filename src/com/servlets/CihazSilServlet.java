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

import com.da.CihazDAO;


/**
 * Servlet implementation class cihazEkleServlet
 */
@WebServlet("/cihazsil")
public class CihazSilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConstraintViolationException {
		
		/* Try blo�unda olu�abilecek hatalar ayr� ayr� catch bloklar�nda ele al�nd� 
		 * MyException - Fiyat istedi�imiz aral�kta de�ilse
		 * NumberFormatException - Fiyat alan�na say� d���nda bir �ey girildiyse
		 * HibernateException - Veri eklerken bir hata olu�tuysa
		 */
		
			/* Post ile gelen parametreler */
			
			int uretici_id = Integer.valueOf(request.getParameter("cihazuretici"));
		
			
			/* IP �LE �LG�L� KONTROLLER BURADA */
			
			
			   
			    	
			   
		
			/* *************** SPRING *************** */
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			((CihazDAO)context.getBean("CihazDAO",CihazDAO.class)).CihazSil(uretici_id);
			/* *************** SPRING *************** */
	
		
		
		
		
		

	
		  
		
		
		
		
		
		
		/*
		catch(MyException e)
		{
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Fiyat Istenilen Aralikta Degil!");
			return;
		}*/
		
	}
}

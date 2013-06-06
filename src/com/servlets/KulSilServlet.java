package com.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;

import com.da.KulDAO;



@WebServlet("/kulsil")
@Controller
public class KulSilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
			/* ************** SPRING ****************** */
			/* Spring altyap�s� haz�rlan�yor */
			AbstractApplicationContext context= SpringFactoryProvider.getApplicationContext();
			/* Spring kullanarak olu�turulan DAO ile veri ekleniyor */
			KulDAO kuldao = (KulDAO)context.getBean("KulDAO");
			/* ************** SPRING ****************** */
			
			/* �ncelikle Kullan�c� ad�n� objeye ta��yal�m. 
		 * Null de�ilse bir 'kaydolma', null ise bir 'g�ncelleme' iste�idir.*/
		Object o_kuladi = request.getParameter("kuladi");
		/* KulID geldiyse bu bir g�ncelleme iste�idir.*/
		Object o_kulid = request.getParameter("kulid");
		
		
		
				/* Kullan�c� kaydolma iste�i yerine getiriliyor */
				
				 if(o_kulid!=null)/* G�ncelleme iste�i yerine getiriliyor */
				{
					
					int kulid = Integer.valueOf(o_kulid.toString());
					/* ************** HIBERNATE - SPRING ****************** */
					/* Spring kullanarak olu�turulan DAO ile kullan�c� g�ncelleniyor */
					kuldao.KulSil(kulid);
					/* ************** HIBERNATE - SPRING ****************** */
					response.setStatus(HttpServletResponse.SC_OK);
					return;
				}
				
		
	
	
		
		}
	
}

package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.dao.DeviceDAO;

/**
 * Servlet implementation class PortServlet
 */
@WebServlet("/portislem")
public class PortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PortServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object o_portid = request.getParameter("portid");
		if(o_portid!=null)
		{
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			try{
			context.getBean("CihazDAO",DeviceDAO.class).portaEris(Integer.valueOf(o_portid.toString()));
			}
			catch(HibernateException e)
			{
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("Port Guncelleme Hatasi!");
				return;
			}
		}
		
		Object o_hepsinitara = request.getParameter("hepsinitara");
		if(o_hepsinitara!=null)
		{
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			try{
			context.getBean("CihazDAO",DeviceDAO.class).tumCihazlariTara();
			}
			catch(HibernateException e)
			{
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("İslem Basarisiz!");
				return;
			}
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}

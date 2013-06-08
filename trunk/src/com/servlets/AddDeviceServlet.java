package com.servlets;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.InetAddressValidator;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.dao.DeviceDAO;


/**
 * Servlet implementation class cihazEkleServlet
 */
@WebServlet("/cihazekle")
public class AddDeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConstraintViolationException {
		
		/* Try blo�unda olu�abilecek hatalar ayr� ayr� catch bloklar�nda ele al�nd� 
		 * MyException - Fiyat istedi�imiz aral�kta de�ilse
		 * NumberFormatException - Fiyat alan�na say� d���nda bir �ey girildiyse
		 * HibernateException - Veri eklerken bir hata olu�tuysa
		 */
		try{
			/* Post ile gelen parametreler */
			String ip = request.getParameter("cihazip");
			int tur_id = Integer.valueOf(request.getParameter("cihaztur"));
			int uretici_id = Integer.valueOf(request.getParameter("cihazuretici"));
			String ad = request.getParameter("cihazad");
			
			/* IP �LE �LG�L� KONTROLLER BURADA */
			
			boolean ipValid;
			
			 if(ip== null || ip.trim().isEmpty()) {
			// if (StringUtils.isEmpty(ip)) {
				 ipValid=false;
			    }
			    if (InetAddressValidator.getInstance().isValid(ip)) {
			    	ipValid= true;
			    }

			    //not an IPV4 address, could be IPV6?
			    try {
			    	ipValid= InetAddress.getByName(ip) instanceof Inet6Address;
			    } catch (final UnknownHostException ex) {
			    	ipValid= false;
			    	
			    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.getWriter().write("IP Adresi Hatali!");
					return;
			    	
			    	
			    }
			
			
			/* Fiyat istedi�imiz aral�kta de�ilse hata f�rlat */
			/*
			if((fiyat<50)||(fiyat>10000))
			throw new MyException("Fiyat Istenilen Aralikta Degil!");
			/* IP �LE �LG�L� KONTROLLER BURADA */
			
			
			/*new CihazDAO().CihazEkle(ad, fiyat, tur_id, uretici_id);*/
			/* *************** SPRING *************** */
			AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
			((DeviceDAO)context.getBean("CihazDAO",DeviceDAO.class)).CihazEkle(ad, ip, tur_id, uretici_id);
			/* *************** SPRING *************** */
		}
		catch(NumberFormatException n){
			n.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Fiyat Bicimi Dogru Degil!");
			return;
		}
		catch(ConstraintViolationException c)
		{
			c.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Ayni Isimde Baska Bir Aygit Var!");
			return;
		}
		catch(HibernateException h)
		{
			h.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Cihaz Eklenirken Hata Olustu!");
			return;
		}
		
		

	
		  
		
		
		
		
		
		
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

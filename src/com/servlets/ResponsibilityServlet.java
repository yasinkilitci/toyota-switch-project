package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;

import com.dao.ResponsibilityDAO;
import com.entity.Device;
import com.exceptions.MyException;

/**
 * Servlet implementation class SiparisEkleServlet
 */
@WebServlet("/sorumluluk")
public class ResponsibilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ResponsibilityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* Get ile gelen istekler sorumluluk silme ile ilgili */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 2 iste�i objelere ta��yoruz. Hemen integer'a �evirirsek null olanlarda program patlar! */
		
		Object o_iptalno = request.getParameter("iptalno");
		Object o_kulid = request.getParameter("kulid");
		
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		
		/* Yaln�zca null olmayan obje integer'a �evrilip ilgili DAO i�lemi ger�ekle�tiriliyor */
		if(o_iptalno!=null)
		{
			int iptalno = Integer.valueOf(o_iptalno.toString());
			int kul_id = Integer.valueOf(o_kulid.toString());
			try {
				context.getBean("SorumlulukDAO",ResponsibilityDAO.class).tekSorumlulukKaldir(kul_id, iptalno);
			} catch (BeansException | MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("Sorumluluk Hatasi!");
				return;
			}
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Device> sepet;
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		if (request.getSession().getAttribute("sepet")!=null)
		{
			boolean istek = Boolean.valueOf(request.getParameter("istek"));
			
			
			if (istek==false){
				return;
					}
			
				sepet = (ArrayList<Device>) request.getSession().getAttribute("sepet");
				/* session_id kullan�c�id'sinin 1453 fazlas�yd� yani kullan�c�id'ye �evirmi� olduk*/
				int kul_id = Integer.valueOf(request.getSession().getAttribute("session_id").toString())-1453;
				context.getBean("SorumlulukDAO",ResponsibilityDAO.class).cokSorumlulukVer(kul_id, sepet);
				request.getSession().setAttribute("sepet",null);
			}
		
				
				
	}
	
	

}

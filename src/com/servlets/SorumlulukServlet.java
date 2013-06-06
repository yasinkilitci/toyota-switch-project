package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.util.SpringFactoryProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;

import com.da.SiparisDAO;
import com.da.SorumlulukDAO;
import com.exceptions.MyException;

/**
 * Servlet implementation class SiparisEkleServlet
 */
@WebServlet("/sorumluluk")
public class SorumlulukServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SorumlulukServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* Get ile gelen istek Post'a y�nlendiriliyor */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
				/* Olas� 3 iste�i objelere ta��yoruz. Hemen integer'a �evirirsek null olanlarda program patlar! */
				Object o_onayno = request.getParameter("onayno");
				Object o_onayiptalno = request.getParameter("onayiptalno");
				Object o_iptalno = request.getParameter("iptalno");
				Object o_kulid = request.getParameter("kulid");
				
				AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
				
				/* Yaln�zca null olmayan obje integer'a �evrilip ilgili DAO i�lemi ger�ekle�tiriliyor */
				if(o_onayno!=null)
				{
					int onayno = Integer.valueOf(o_onayno.toString());
					new SiparisDAO().siparisOnayla(onayno);
				}
				else if(o_onayiptalno!=null)
				{
					int onayiptalno = Integer.valueOf(o_onayiptalno.toString());
					new SiparisDAO().siparisOnayKaldir(onayiptalno);
				}
				else if(o_iptalno!=null)
				{
					int iptalno = Integer.valueOf(o_iptalno.toString());
					int kul_id = Integer.valueOf(o_kulid.toString());
					try {
						context.getBean("SorumlulukDAO",SorumlulukDAO.class).tekSorumlulukKaldir(kul_id, iptalno);
					} catch (BeansException | MyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.getWriter().write("Sorumluluk Hatasi!");
						return;
					}
				}
				
	}
	
	

}

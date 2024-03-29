package com.servlets;
//Bu servlet favoriekle mapping ini i�eren linklerde ne yap�lmas� gerekti�ini g�stermek
//i�in yaz�ld�. sa� t�klay�p new servlet olarak olu�turulabilir.
//doget bizden al�nacak veriler k�sm�n�
//do post bize g�nderilecek de�erler k�sm�n� tan�mlar
//�rne�in doget k�sm�nda html ile form tasarlay�p ad id si ile bi tesx yaparsak
//dopost ta request.getparameter("ad") ile onu al�r ve
//PrintWriter yazici = response.getWriter(); ile bir yazici olu�turarak
//yazici.write("<html>�uan beni "+ad+" tikladi</html>"); ile ekrana yazd�rabiliriz.
//biz genelde bizden al�nan verileri direk olarak doposta y�nlendirdik
//dopostta burada integer.valueof ile bir integer ald�k.
//request.getSession().getAttribute("favoriler")!=null oturumdaki favoriler de�i�keni 
//bo� de�ilse bunu bir diziye �evir. bo� ise zaten dizi olarka olu�turduk.
//request.getSession().setAttribute("favoriler",favoriler) burada bir de�i�ken olu�tur
//ad� "favoriler" olsun ve buradaki favoriler i i�ersin
//request.getRequestDispatcher("cihazdetay?cihazid="+cihazid).forward(request,response);
//bu i�lemleri yapt�ktan sonra cihazdetay servleetine cihazid=bu sayfadaki cihazid ile git
//Dispatcher y�nlendirme yapmaya yarar. burada i�lemi yapt�ktan sonra herhangi bir
//sayfaya gitmek istemedik ve bulunulan sayfan�n linkini tekrar verdik.


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.dao.DeviceDAO;
import com.entity.Device;

/**
 * Servlet implementation class favoriEkleServlet
 */
@WebServlet("/sepetekle")
public class AddToListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object o_cihaz_id = request.getParameter("cihazid");
		Object o_sifirla = request.getParameter("sifirla");
		if(o_cihaz_id!=null){
			int cihazid = Integer.valueOf(o_cihaz_id.toString());
		/* Spring */ 
		AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		Device cihaz = ((DeviceDAO)context.getBean("CihazDAO",DeviceDAO.class)).CihazDetayiniGetir(cihazid);
		/* Spring */ 
		
		ArrayList<Device> sepet = new ArrayList<Device>();
		if (request.getSession().getAttribute("sepet")!=null)
		{
			sepet = (ArrayList<Device>) request.getSession().getAttribute("sepet");
		}
		sepet.add(cihaz);
		request.getSession().setAttribute("sepet",sepet);
		}else if(o_sifirla!=null)
		{
			request.getSession().setAttribute("sepet", new ArrayList<Device>());
		}
	}

}

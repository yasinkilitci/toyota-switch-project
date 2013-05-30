package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.SiparisDAO;
import com.entity.Cihaz;
import com.entity.Siparis;


@WebServlet("/siparisekle")
public class SiparisEkleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SiparisEkleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ArrayList<Cihaz> sepet;
		
		if (request.getSession().getAttribute("sepet")!=null)
		{
			boolean istek = Boolean.valueOf(request.getParameter("istek"));
			String adetListesi = request.getParameter("adetlistem");
			
			int[] i_adetListesi = diziyeDonustur(adetListesi);
			
			if (istek==false){
				return;
					}
			
				sepet = (ArrayList<Cihaz>) request.getSession().getAttribute("sepet");
				/* session_id kullanýcýid'sinin 1453 fazlasýydý yani kullanýcýid'ye çevirmiþ olduk*/
				int kullanici = Integer.valueOf(request.getSession().getAttribute("session_id").toString()) - 1453;
				
				Siparis yenisip;
				yenisip = new SiparisDAO().SiparisEkle(kullanici, i_adetListesi, sepet);		
				request.getSession().setAttribute("sepet",null);
			}
			else
			{
				String mesaj = "Sepet Boþ";
				request.setAttribute("mesaj", mesaj);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		
	}
	
	/* String olarak teslim alýnan diziyi sayýsal diziye dönüþtürüyor */
	private int[] diziyeDonustur(String sdizi)
	{
		char okunankarakter;
		int[] adetListesi = new int[20];
		int sayac = 0;
		int sayi = 0;
			for(int i=0;i<sdizi.length();i++){
						sayi = 0;
						okunankarakter = sdizi.charAt(i);
						if((okunankarakter!='[')&&(okunankarakter!='"')&&(okunankarakter!=',')&&(okunankarakter!=']'))
						{
							sayi = Integer.valueOf(String.valueOf(okunankarakter));
							adetListesi[sayac]=sayi;
							sayac++;
						}
						else
							continue;
							
				}
		
		return adetListesi;
	}

}

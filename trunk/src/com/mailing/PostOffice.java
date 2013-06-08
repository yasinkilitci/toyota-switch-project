package com.mailing;

import java.util.ArrayList;

import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.entity.User;
import com.entity.Port;
import com.sun.mail.util.MailConnectException;
import com.dao.ResponsibilityDAO;

public class PostOffice {
		
	public PostOffice(){}
	
	public void sorumlularaMailGonder(ArrayList<Port> kportlar) throws MailConnectException
	{
		 ArrayList<User> sorumlular = new ArrayList<User>();
		 AbstractApplicationContext context = SpringFactoryProvider.getApplicationContext();
		 String s_portlar = "";
		 String s_cihaz = "";
		 int i_kportlar = kportlar.size();
		 for (int i=0;i<i_kportlar;i++)
		 {
			 /* son elemana gelmedi�in s�rece port ad�n� ve yan�na virg�l koy */
			if(i!=i_kportlar-1)	{
			s_portlar += kportlar.get(i).getName() + ", ";
			}
			else
			{
			/* Son eleman�n kontrol�nde art�k virg�l koyma ve cihaz_id'sine g�re sorumlular� getir
			 * cihaz ad�n� ileride kullanmak �zere string de�i�kene al */
			s_portlar += kportlar.get(i).getName() + " ";
			s_cihaz = kportlar.get(i).getCihaz().getAd();
			sorumlular = context.getBean("SorumlulukDAO",ResponsibilityDAO.class).sorumluKullariGetir(kportlar.get(i).getCihaz().getId());
			}
		 }
		
		 /* E-Posta hazirlaniyor */
		 String mesaj = "";
		 String konu = s_cihaz + " icin 30 Gundur Kullanilmayan Portlar";
		 String karsilama = "Merhaba\n";
		 String icerik = s_cihaz + " switch'inde " + s_portlar + " portlar 30 gundur kullanilmamaktadir.\n";
		 icerik += "Bilgilerinize.";
		 
		 Postman postaci = new Postman();
		 /* kar��laman�n pe�ine kullan�c�n�n ad� soyad� ekleniyor ve i�erik de ard�na
		  * konularak mail g�nderiliyor.
		  */
		 for(User kul : sorumlular)
		 {
			 mesaj = karsilama + kul.getAdsoyad() + "\n" + icerik;
			 postaci.epostaGonder(kul.getEposta(), mesaj, konu);
		 }
		 
	}
	
}

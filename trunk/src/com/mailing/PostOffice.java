package com.mailing;

import java.util.ArrayList;

import org.spring.util.SpringFactoryProvider;
import org.springframework.context.support.AbstractApplicationContext;

import com.entity.Kul;
import com.entity.Port;
import com.da.SorumlulukDAO;

public class PostOffice {
		
	public PostOffice(){}
	
	public void sorumlularaMailGonder(ArrayList<Port> kportlar)
	{
		 ArrayList<Kul> sorumlular = new ArrayList<Kul>();
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
			sorumlular = ((SorumlulukDAO)context.getBean("SorumlulukDAO",SorumlulukDAO.class)).sorumluKullariGetir(kportlar.get(i).getCihaz().getId());
			}
		 }
		
		 /* E-Posta ��eri�i Haz�rlan�yor */
		 String mesaj = "";
		 String konu = s_cihaz + " i�in 30 G�nd�r Kullan�lmayan Portlar";
		 String karsilama = "Merhaba\n";
		 String icerik = s_cihaz + " switch'inde " + s_portlar + " portlar� 30 g�nd�r kullan�lmamaktad�r.\n";
		 icerik += "Bilgilerinize.";
		 
		 Postman postaci = new Postman();
		 /* kar��laman�n pe�ine kullan�c�n�n ad� soyad� ekleniyor ve i�erik de ard�na
		  * konularak mail g�nderiliyor.
		  */
		 for(Kul kul : sorumlular)
		 {
			 mesaj = karsilama + kul.getAdsoyad() + "\n" + icerik;
			 postaci.epostaGonder(kul.getEposta(), mesaj, konu);
		 }
		 
	}
	
}
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
			 /* son elemana gelmediðin sürece port adýný ve yanýna virgül koy */
			if(i!=i_kportlar-1)	{
			s_portlar += kportlar.get(i).getName() + ", ";
			}
			else
			{
			/* Son elemanýn kontrolünde artýk virgül koyma ve cihaz_id'sine göre sorumlularý getir
			 * cihaz adýný ileride kullanmak üzere string deðiþkene al */
			s_portlar += kportlar.get(i).getName() + " ";
			s_cihaz = kportlar.get(i).getCihaz().getAd();
			sorumlular = ((SorumlulukDAO)context.getBean("SorumlulukDAO",SorumlulukDAO.class)).sorumluKullariGetir(kportlar.get(i).getCihaz().getId());
			}
		 }
		
		 /* E-Posta Ýçeriði Hazýrlanýyor */
		 String mesaj = "";
		 String konu = s_cihaz + " için 30 Gündür Kullanýlmayan Portlar";
		 String karsilama = "Merhaba\n";
		 String icerik = s_cihaz + " switch'inde " + s_portlar + " portlarý 30 gündür kullanýlmamaktadýr.\n";
		 icerik += "Bilgilerinize.";
		 
		 Postman postaci = new Postman();
		 /* karþýlamanýn peþine kullanýcýnýn adý soyadý ekleniyor ve içerik de ardýna
		  * konularak mail gönderiliyor.
		  */
		 for(Kul kul : sorumlular)
		 {
			 mesaj = karsilama + kul.getAdsoyad() + "\n" + icerik;
			 postaci.epostaGonder(kul.getEposta(), mesaj, konu);
		 }
		 
	}
	
}

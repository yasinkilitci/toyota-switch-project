package com.entity;

import java.sql.Date;
import java.util.ArrayList;

import com.entity.Cihaz;


public class Siparis {

	private int id;
	private int kul;
	private int sipno;
	private int onay;
	private Date tarih;
	/* Veritabanýnda olmayanlar */
	private int toplam;
	private String kuladi;
	private ArrayList<Cihaz> listeCihaz;
	private int[] listeAdet;
	
	public Siparis(int id, int kul, int sipno, ArrayList<Cihaz> listeCihaz, int[] listeAdet,
			int onay, Date tarih) {
		super();
		this.id = id;
		this.kul = kul;
		this.sipno = sipno;
		this.listeCihaz = listeCihaz;
		this.listeAdet = listeAdet;
		this.onay = onay;
		this.tarih = tarih;
	}
	
	public Siparis(String kuladi, int sipno, int toplam, Date tarih, int onay)
	{
			this.kuladi = kuladi;
			this.sipno = sipno;
			this.toplam = toplam;
			this.tarih = tarih;
			this.onay = onay;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKul() {
		return kul;
	}

	public void setKul(int kul) {
		this.kul = kul;
	}

	public int getSipno() {
		return sipno;
	}

	public void setSipno(int sipno) {
		this.sipno = sipno;
	}

	public ArrayList<Cihaz> getListeCihaz() {
		return listeCihaz;
	}

	public void setListeCihaz(ArrayList<Cihaz> listeCihaz) {
		this.listeCihaz = listeCihaz;
	}

	public int getOnay() {
		return onay;
	}

	public void setOnay(int onay) {
		this.onay = onay;
	}

	public Siparis() {
		this.id = 0;
		this.kul = 0;
		this.sipno = 0;
		this.listeCihaz = null;
		this.listeAdet = null;
		this.onay = 0;
		this.tarih = Date.valueOf("25.05.2013");
	}
	
	

	public int[] getListeAdet() {
		return listeAdet;
	}

	public void setListeAdet(int[] listeAdet) {
		this.listeAdet = listeAdet;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public int getToplam() {
		return toplam;
	}

	public void setToplam(int toplam) {
		this.toplam = toplam;
	}

	public String getKuladi() {
		return kuladi;
	}

	public void setKuladi(String kuladi) {
		this.kuladi = kuladi;
	}
	
}

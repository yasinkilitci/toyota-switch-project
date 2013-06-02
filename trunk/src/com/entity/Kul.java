package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="user")
public class Kul {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String kuladi;
	@Column
	private String adsoyad;
	@Column
	private String adres;
	@Column
	private int tel;
	@Column
	private int yetki;
	@Column
	private String sifre;
	@Column
	private String eposta;

	public Kul(int id, String kuladi, String adsoyad, String adres, int tel, int yetki, String sifre)
	{
		this.id = id;
		this.kuladi = kuladi;
		this.adsoyad = adsoyad;
		this.adres = adres;
		this.tel = tel;
		this.yetki = yetki;
		this.sifre = sifre;
	}


public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getKuladi() {
		return kuladi;
	}


	public void setKuladi(String kuladi) {
		this.kuladi = kuladi;
	}


	public String getAdsoyad() {
		return adsoyad;
	}


	public void setAdsoyad(String adsoyad) {
		this.adsoyad = adsoyad;
	}


	public String getAdres() {
		return adres;
	}


	public void setAdres(String adres) {
		this.adres = adres;
	}


	public int getTel() {
		return tel;
	}


	public void setTel(int tel) {
		this.tel = tel;
	}


	public int getYetki() {
		return yetki;
	}


	public void setYetki(int yetki) {
		this.yetki = yetki;
	}


	public String getSifre() {
		return sifre;
	}


	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	public String getEposta() {
		return eposta;
	}


	public void setEposta(String eposta) {
		this.eposta = eposta;
	}


public Kul() {
		// TODO Auto-generated constructor stub
	}

@Override
	public String toString(){
		
		return this.id + " - " + this.kuladi;
		
	}

	
}

package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="user")
public class User {

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
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="sorumluluk", joinColumns=@JoinColumn(name="kul_id",referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="cihaz_id",referencedColumnName="id"))
	private Set<Device> cihazlar = new HashSet<Device>(0);

	public User(int id, String kuladi, String adsoyad, String adres, int tel, int yetki, String sifre)
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

	
	
	public Set<Device> getCihazlar() {
		return cihazlar;
	}


	public void setCihazlar(Set<Device> cihazlar) {
		this.cihazlar = cihazlar;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}

@Override
	public String toString(){
		
		return this.id + " - " + this.kuladi;
		
	}

	
}

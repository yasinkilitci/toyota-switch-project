package com.entity;

//tostring metodunu ekrana ya da texte yazarken kolayl�k sa�lamas� i�in override ettik.
public class Tur {

	private int id;
	private String ad;

	public Tur() {

	}

	public Tur(int id, String ad) {
		super();
		this.id = id;
		this.ad = ad;
	}

	public int getId() {
		return id;
	}
	
	public String getIDString() {
		
		return Integer.toString(id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}
	@Override
	public String toString() {
		
		return ad;
	}
}

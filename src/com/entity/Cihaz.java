package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="cihaz")
public class Cihaz {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String ad;
	@Column
	private int fiyat;
	@Column
	private int tur_id;
	@Column
	private int uretici_id;
	@Column
	private String resim_yolu;
	
	/* Veritabanýnda Olmayanlar */
	@Transient
	private Uretici uretici;
	@Transient
	private Tur tur;
	
	

	public Cihaz(int id, String ad, int fiyat, Uretici uretici,
			Tur tur) {
		super();
		this.id = id;
		this.ad = ad;
		this.fiyat = fiyat;
		this.uretici = uretici;
		this.tur = tur;
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



	public int getFiyat() {
		return fiyat;
	}



	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}



	public Uretici getUretici() {
		return uretici;
	}



	public void setUretici(Uretici uretici) {
		this.uretici = uretici;
	}



	public Tur getTur() {
		return tur;
	}



	public void setTur(Tur tur) {
		this.tur = tur;
	}

	public int getTur_id() {
		return tur_id;
	}



	public void setTur_id(int tur_id) {
		this.tur_id = tur_id;
	}



	public int getUretici_id() {
		return uretici_id;
	}



	public void setUretici_id(int uretici_id) {
		this.uretici_id = uretici_id;
	}



	public String getResim_yolu() {
		return resim_yolu;
	}



	public void setResim_yolu(String resim_yolu) {
		this.resim_yolu = resim_yolu;
	}


public Cihaz() {
		// TODO Auto-generated constructor stub
	}

@Override
	public String toString(){
		
		return this.id + " - " + this.ad + " - " + this.fiyat;
		
	}
}

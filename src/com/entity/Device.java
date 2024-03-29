package com.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="cihaz")
@Table(name="cihaz")
public class Device {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String ad;
	@Column
	private String ip;
	@Column
	private int tur_id;
	@Column
	private int uretici_id;
	@Column
	private String resim_yolu;
	@OneToMany(mappedBy="cihaz",targetEntity=Port.class,fetch=FetchType.EAGER)
	private Collection<Port> portlar = new ArrayList<Port>();
	/*
	@ManyToMany(mappedBy="cihazlar",fetch=FetchType.EAGER)
	private Collection<Kul> kullar = new ArrayList<Kul>();
	*/
	/* Veritabanưnda Olmayanlar */
	@Transient
	private Vendor uretici;
	@Transient
	private DeviceType tur;
	
	

	public Device(int id, String ad, String ip, Vendor uretici,
			DeviceType tur) {
		super();
		this.id = id;
		this.ad = ad;
		this.ip = ip;
		this.uretici = uretici;
		this.tur = tur;
	}
	
	public Device(int id, String ad, String ip, int uretici_id,
			int tur_id) {
		super();
		this.id = id;
		this.ad = ad;
		this.ip = ip;
		this.uretici_id = uretici_id;
		this.tur_id = tur_id;
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



	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public Vendor getUretici() {
		return uretici;
	}



	public void setUretici(Vendor uretici) {
		this.uretici = uretici;
	}



	public DeviceType getTur() {
		return tur;
	}



	public void setTur(DeviceType tur) {
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

	

	public Collection<Port> getPortlar() {
		return portlar;
	}



	public void setPortlar(Collection<Port> portlar) {
		this.portlar = portlar;
	}

	public Device() {
			// TODO Auto-generated constructor stub
		}

@Override
	public String toString(){
		
		return this.id + " - " + this.ad + " - " + this.ip;
		
	}
}

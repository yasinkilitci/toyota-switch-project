package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tur")
public class DeviceType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String ad;

	public DeviceType(int id, String ad) {
		this.id = id;
		this.ad = ad;
	}

	public int getId() {
		return id;
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
	
	public DeviceType()
	{
		// TODO Yapýlacak iþler
	}
}

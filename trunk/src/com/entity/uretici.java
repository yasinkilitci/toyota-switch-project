package com.entity;

public class uretici {

	private int id;
	private String ad;

	public uretici() {

	}

	public uretici(int id, String ad) {
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
	
	
}

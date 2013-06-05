package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Port {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String name;
	@Column
	private int status;
	@Column
	private int vlan;
	@Column
	private int duplex;
	@Column
	private int speedtype;
	@Temporal(TemporalType.TIMESTAMP)
	private Date sonerisim;
	@ManyToOne
	private Cihaz cihaz;
	
	public Port()
	{
		//TODO Yapýlacak Ýþler
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVlan() {
		return vlan;
	}

	public void setVlan(int vlan) {
		this.vlan = vlan;
	}

	public int getDuplex() {
		return duplex;
	}

	public void setDuplex(int duplex) {
		this.duplex = duplex;
	}

	public int getSpeedtype() {
		return speedtype;
	}

	public void setSpeedtype(int speedtype) {
		this.speedtype = speedtype;
	}

	public Cihaz getCihaz() {
		return cihaz;
	}

	public void setCihaz(Cihaz cihaz) {
		this.cihaz = cihaz;
	}

	public Date getSonerisim() {
		return sonerisim;
	}

	public void setSonerisim(Date sonerisim) {
		this.sonerisim = sonerisim;
	}
	
	
	
}

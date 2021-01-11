package com.poller.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {
	
	
	
	public Service() {
		
	}
	
	public Service(Integer id, String name, String url, String status, Timestamp date) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.status = status;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;

	String name;
	
	String url;
	
	String status;
	
	Timestamp date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}

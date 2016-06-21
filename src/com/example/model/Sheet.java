package com.example.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sheets")
public class Sheet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private Timestamp creationDate;
	private Timestamp modificationDate;
	
	
	
	public Sheet(String name, Timestamp creationDate, Timestamp modificationDate) {
		super();
		this.name = name;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}

package com.wicare.wistormdemo.entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "CarInfo")
public class CarInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	@DatabaseField(canBeNull=false,unique=true,useGetSet = true,generatedId = true)  
	private int id ;
	@DatabaseField(canBeNull=true,useGetSet = true)
	private String Lon; //位置
	@DatabaseField(canBeNull=true,useGetSet = true)
	private String Lat; //位置
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLon() {
		return Lon;
	}
	public void setLon(String lon) {
		Lon = lon;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	
	
	
}
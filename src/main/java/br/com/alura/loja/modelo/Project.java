package br.com.alura.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Project {
	
	private long id;
	private String name;
	private int startYear;
	
	public Project(long id, String name, int startYear) {
		this.id = id;
		this.name = name;
		this.startYear = startYear;
	}
	
	public Project() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	
	public String toXML() {

		return new XStream().toXML(this);
	}

	public String toJson() {
		
		return new Gson().toJson(this);
		
	}
	

}
